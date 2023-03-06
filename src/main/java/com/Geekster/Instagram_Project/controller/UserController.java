package com.Geekster.Instagram_Project.controller;

import com.Geekster.Instagram_Project.model.User;
import com.Geekster.Instagram_Project.service.UserService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    //    crete a new user
    @PostMapping(value = "/user")
    public ResponseEntity saveUser(@RequestBody String  userData){
        User user=setUser(userData);
        int userId=userService.saveUser(user);
        return new ResponseEntity("User save with id -"+userId, HttpStatus.CREATED);
    }

    //    get user by user id and if we not give the user id then all user will come
    @GetMapping(value = "/user")
    public ResponseEntity getUser(@Nullable @RequestParam String userId){
        JSONArray userDetails=userService.getUser(userId);
        return new ResponseEntity(userDetails.toString(),HttpStatus.OK);
    }


    //    convert String user data to json object and return user
    private User setUser(String userData) {
        JSONObject jsonObject=new JSONObject(userData);
        User user=new User();
        user.setAge(jsonObject.getInt("age"));
        user.setEmail(jsonObject.getString("email"));
        user.setFirstName(jsonObject.getString("firstName"));
        user.setLastName(jsonObject.getString("lastName"));
        user.setPhoneNumber(jsonObject.getString("phoneNumber"));
        return user;
    }

    //    update user by user id
    @PutMapping(value = "/user/{userId}")
    public ResponseEntity updateUser(@PathVariable String userId,@RequestBody String userData){
        User user=setUser(userData);
        return userService.updateUser(user,userId);
    }
    @GetMapping(value = "/getAll")
    public List<User> getall(){
        return userService.getAll();
    }
}
