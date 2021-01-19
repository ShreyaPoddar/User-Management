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
    public String addUser(@RequestBody User user)
    {
        List<User> emails=service.findByEmailid(user.getEmailid());
        List<User> usernames = service.findByUsername(user.getUsername());
        List<User> mobile_numbers = service.findByMobilenumber(user.getMobilenumber());
        if(!emails.isEmpty())
            return "User with same emailID already exists";
        else if(!usernames.isEmpty())
            return "User with same username already exists";
        else if(!mobile_numbers.isEmpty())
            return "User with same mobile number already exists";
        service.saveUser(user);
        return "User's data successfully saved";
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

