package com.webcamp5.foodhandongserver.service;

import com.webcamp5.foodhandongserver.model.Category;
import com.webcamp5.foodhandongserver.model.Menu;
import com.webcamp5.foodhandongserver.model.Restaurant;
import com.webcamp5.foodhandongserver.model.request.RestaurantCreationRequest;
import com.webcamp5.foodhandongserver.repository.CategoryRepository;
import com.webcamp5.foodhandongserver.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;


//    public Restaurant createRestaurant(RestaurantCreationRequest request){
//        Restaurant restaurant = Restaurant.createRestaurant(
//            request.getContact(), request.getDong(), request.getImageUrl()
//            ,request.getLatitude(), request.getLongitude(), request.getLocation()
//            ,request.getName(),request.getOfficialName(),request.getOpeningHours()
//        );
//        List<Menu> menus = request.getMenus();
//
//        for(Menu menudto : menus){
//            Menu menu = Menu.createMenu(menudto.getName(),menudto.getPrice() , menudto.getImageUrl() , restaurant);
//            restaurant.putMenu(menu);
//        }
//        return restaurantRepository.save(restaurant);
//    }



    public Restaurant readRestaurant(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        if (restaurant.isPresent()) {
            return restaurant.get();
        }

        throw new EntityNotFoundException("Cant find any restaurant under given ID");
    }
    public List<Restaurant> readRestaurants() {

        return restaurantRepository.findAll();
    }

    public Restaurant createRestaurant(RestaurantCreationRequest restaurant) {

        Optional<Category> category = categoryRepository.findById(restaurant.getCategoryId());
        if(!category.isPresent()){
            throw new EntityNotFoundException(
                    "Category Not Found");
        }



        Restaurant restaurantToCreate = new Restaurant();
        BeanUtils.copyProperties(restaurant, restaurantToCreate);
        restaurantToCreate.setCategory(category.get());

        List<Menu> menus = restaurant.getMenus();
        for(Menu menuDTO : menus){
//            Menu menu = new Menu(menuDTO.getName(),menuDTO.getPrice() , menuDTO.getImageUrl() , restaurantToCreate);
            menuDTO.setRestaurant(restaurantToCreate);
        }


        return restaurantRepository.save(restaurantToCreate);

    }


    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant updateRestaurant(Long id,RestaurantCreationRequest request){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(!optionalRestaurant.isPresent()){
            throw new EntityNotFoundException(
                    "restaurant not present in the database");
        }

        Restaurant restaurant = optionalRestaurant.get();

//        restaurant.setCategoryId(request.getCategoryId());
        restaurant.setContact(request.getContact());
        restaurant.setDong(request.getDong());
        restaurant.setImageUrl(request.getImageUrl());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());
        restaurant.setLocation(request.getLocation());
        restaurant.setName(request.getName());
        restaurant.setOfficialName(request.getOfficialName());
        restaurant.setOpeningHours(request.getOpeningHours());

        Optional<Category> category = categoryRepository.findById(request.getCategoryId());
        restaurant.setCategory(category.get());

        return restaurantRepository.save(restaurant);

    }
}
