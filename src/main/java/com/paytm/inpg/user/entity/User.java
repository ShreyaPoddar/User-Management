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
    @GeneratedValue
    private int userid;
    private String username;
    private String fName;
    private String lName;
    private String mobileNumber;
    private String emailID;
    private String address1;
    private String address2;
}
