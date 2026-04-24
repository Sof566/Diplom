package com.example.vodocanalmobileapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vodocanalmobileapp.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserEntity user);

    @Query("SELECT * FROM users WHERE login = :login AND password = :password LIMIT 1")
    UserEntity login(String login, String password);

}