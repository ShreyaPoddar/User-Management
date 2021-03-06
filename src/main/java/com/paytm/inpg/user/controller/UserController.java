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
    public String addUser(@RequestBody User user) {
        //Finding the user with same email id
        List<User> emails = service.findByEmailid(user.getEmailid());
        //Finding the user with same username
        List<User> usernames = service.findByUsername(user.getUsername());
        //Finding the user with same mobile number
        List<User> mobile_numbers = service.findByMobilenumber(user.getMobilenumber());
        if (!emails.isEmpty())
            return "User with same emailID already exists";
        else if (!usernames.isEmpty())
            return "User with same username already exists";
        else if (!mobile_numbers.isEmpty())
            return "User with same mobile number already exists";

        //If the user data doesn't exist in the Table then saving the data
        service.saveUser(user);
        return "User's data successfully saved";
    }

    //Get method
    @GetMapping("/users")
    public List<User> findAllUsers() {
        return service.getUser();
    }

    @GetMapping("/user/{userid}")
    public User findUserByid(@PathVariable int userid) {
        return service.getUserByID(userid);
    }

    //update method
    @PutMapping("/update/{userid}")
    public String updateUser(@RequestBody User user, @PathVariable(value = "userid") int id) {
        //Checking if particular user exists in the table or not
        if (service.getUserByID(id) != null) {
            service.updateUser(user);
            return "User data updated";
        } else {
            return "User doesn't exist";

        }
    }

    //delete method
    @DeleteMapping("/delete/{userid}")
    public String deleteUser(@PathVariable int userid) {
        if (service.getUserByID(userid) != null) {
            service.deleteUser(userid);
            return "User data deleted";
        } else {
            return "User doesn't exist";

        }


    }
}

