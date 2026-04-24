package com.example.vodocanalmobileapp.api.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.vodocanalmobileapp.dao.UserDao;
import com.example.vodocanalmobileapp.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}