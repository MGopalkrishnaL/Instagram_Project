package com.Geekster.Instagram_Project.controller;

import com.Geekster.Instagram_Project.dao.UserRepo;
import com.Geekster.Instagram_Project.model.Post;
import com.Geekster.Instagram_Project.model.User;
import com.Geekster.Instagram_Project.service.PostService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
@RestController

public class PostController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostService postService;

//    create a new post with lazy loading

    @PostMapping(value = "/post")
    public ResponseEntity<String> savePost(@RequestBody String postRequest){

        Post post=setPost(postRequest);
        int postId=postService.savePost(post);
        return new ResponseEntity<String>("post created post id - "+postId, HttpStatus.CREATED);
    }

    //    set post by user lazy loading
    private Post setPost(String postRequest) {
        JSONObject jsonObject=new JSONObject(postRequest);
        User user=null;

        int userId=jsonObject.getInt("userId");
//        check if user is present or not if not present thn direct return null and if present then create a post
        if(userRepo.findById(userId).isPresent()){
            user=userRepo.findById(userId).get();
        }
        else {
            return null;
        }
        Post post=new Post();
        post.setUser(user);
        post.setPostData(jsonObject.getString("postData"));
        Timestamp createdTime=new Timestamp(System.currentTimeMillis());
        post.setCreatedDate(createdTime);
        return post;
    }


    //    get post by post id and user id if we not pass post id then all post get by user id
    @GetMapping(value = "/post")
    public ResponseEntity<String> getPost(@RequestParam String userId, @Nullable @RequestParam String postId){
        JSONArray postArray=postService.getPost(Integer.parseInt(userId),postId);
        return new ResponseEntity<>(postArray.toString(),HttpStatus.OK);
    }

    //    update post by post id
    @PutMapping(value = "/post/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable String postId,@RequestBody String requestPost){
        Post post = setPost(requestPost);
        postService.updatePost(postId,post);
        return new ResponseEntity<>("Post updates",HttpStatus.OK);
    }
}
