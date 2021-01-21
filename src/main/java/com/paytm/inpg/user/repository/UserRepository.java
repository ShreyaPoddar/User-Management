package com.paytm.inpg.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paytm.inpg.user.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
public List<User> findByEmailid(String emailid);
public List<User> findByUsername(String username);
public List<User> findByMobilenumber(String mobilenumber);
public List<User> findByUserid(int id);
}
