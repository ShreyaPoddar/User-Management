package com.paytm.inpg.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USER_DATA")
public class User {
    @Id
    @GeneratedValue()
    private int userid;


    private String username;
    private String fname;
    private String lname;
    private String mobilenumber;
    private String emailid;
    private String address1;
    private String address2;
}
