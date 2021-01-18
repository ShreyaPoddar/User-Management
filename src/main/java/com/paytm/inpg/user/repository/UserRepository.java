package com.paytm.inpg.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paytm.inpg.user.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {


}
