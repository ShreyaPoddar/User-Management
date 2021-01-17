package com.paytm.inpg.user.service;
import com.paytm.inpg.user.entity.User;
import com.paytm.inpg.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    //POST METHOD
    public User saveUser(User user)
    {
       return repository.save(user);
    }
    //GET METHOD
    public List<User> getUser()
    {
        return repository.findAll();
    }
    public User getUserByID(int userid)
    {
        repository.findById(userid).orElse(null);
    }

    //UPDATE METHOD
    public User updateProduct(User user)
    {
        User existingUser=repository.findById(user.getUserid()).orElse(null);
        existingUser.setUsername(user.getUsername());
        existingUser.setFName(user.getFName());
        existingUser.setLName(user.getLName());
        existingUser.setMobileNumber(user.getMobileNumber());
        existingUser.setEmailID(user.getEmailID());
        existingUser.setAddress1(user.getAddress1());
        existingUser.setAddress2(user.getAddress2());
        return repository.save(existingUser);
    }
    //DELETE METHOD
    public String deleteUser(int userid)
    {
        repository.delete(userid);
        return "User's Data is deleted "+userid;

    }
}
