package com.Geekster.Instagram_Project.service;


import com.Geekster.Instagram_Project.dao.UserRepo;
import com.Geekster.Instagram_Project.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    //    save the user detail which come from the front end
    public int saveUser(User user){
        User userObject=userRepo.save(user);
        return userObject.getUserId();
    }

    //    get user by user id and if not give user id then send all user
    public JSONArray getUser(String userId) {
        JSONArray userArray=new JSONArray();
//        check is user is present or not
        if (userId !=null && userRepo.findById(Integer.valueOf(userId)).isPresent()){
            User user=userRepo.findById(Integer.valueOf(userId)).get();
            JSONObject jsonObject = setUser(user);
            userArray.put(jsonObject);
        }
        else {
            List<User> userList=userRepo.findAll();
            for (User user:userList){
                JSONObject jsonObject=setUser(user);
                userArray.put(jsonObject);
            }
        }
        return userArray;
    }
    private JSONObject setUser(User user){
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("userId",user.getUserId());
        jsonObject.put("firstName",user.getFirstName());
        jsonObject.put("lastName",user.getLastName());
        jsonObject.put("age",user.getAge());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phoneNumber",user.getPhoneNumber());
        return jsonObject;
    }

    //    user update by user id and user send which data will update
    public ResponseEntity updateUser(User newUser, String userId) {
//        check user is present or not
        if (userRepo.findById(Integer.valueOf(userId)).isPresent()){
            User user=userRepo.findById(Integer.valueOf(userId)).get();
            newUser.setUserId(user.getUserId());
            userRepo.save(newUser);
//            user.setAge(newUser.getAge());
//            user.setEmail(newUser.getEmail());
//            user.setPhoneNumber(newUser.getPhoneNumber());
//            user.setFirstName(newUser.getFirstName());
//            user.setLastName(newUser.getLastName());
//            userRepo.save(user);
            return new ResponseEntity("user update successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("user not found user id- "+userId,HttpStatus.BAD_REQUEST);
        }
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }
}
