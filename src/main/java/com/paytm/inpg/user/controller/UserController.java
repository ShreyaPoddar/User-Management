package com.paytm.inpg.user.controller;

import com.paytm.inpg.user.entity.User;
import com.paytm.inpg.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

  //Post method
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user)
    {
        return service.saveUser(user);
    }
    //Get method
    @GetMapping("/users")
    public List<User> findAllUsers()
    {
        return service.getUser();
    }
    @GetMapping("/user/{userid}")
    public User findUserByid(@PathVariable int userid)
    {
        return service.getUserByID(userid);
    }

    //update method
    @PutMapping("/update")
    public User updateUser(@RequestBody User user)
    {
        return service.updateUser(user);
    }

    //delete method
    @DeleteMapping("/delete/{userid}")
    public String deleteUser(@PathVariable int userid)
    {
        return service.deleteUser(userid);
    }
}

