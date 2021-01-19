package com.paytm.inpg.user.service;
import com.paytm.inpg.user.entity.User;
import com.paytm.inpg.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    //POST METHOD

    public List<User> findByEmailid(String emailid)
    {
        return repository.findByEmailid(emailid);
    }

    public List<User> findByUsername(String username)
    {
        return repository.findByUsername(username);
    }

    public List<User> findByMobilenumber(String mobilenumber)
    {
        return repository.findByMobilenumber(mobilenumber);
    }
    public void saveUser(User user)
    { repository.save(user);
    }
    //GET METHOD
    public List<User> getUser()
    {
        return repository.findAll();
    }
    public User getUserByID(int userid)
    {
        return repository.findById(userid).orElse(null);
    }

    //UPDATE METHOD
    public User updateUser(User user)
    {
        User existingUser=repository.findById(user.getUserid()).orElse(null);
        existingUser.setUsername(user.getUsername());
        existingUser.setFname(user.getFname());
        existingUser.setLname(user.getLname());
        existingUser.setMobilenumber(user.getMobilenumber());
        existingUser.setEmailid(user.getEmailid());
        existingUser.setAddress1(user.getAddress1());
        existingUser.setAddress2(user.getAddress2());
        return repository.save(existingUser);
    }
    //DELETE METHOD
    public String deleteUser(int userid)
    {
        repository.deleteById(userid);
        return "User's Data is deleted "+userid;

    }
}
