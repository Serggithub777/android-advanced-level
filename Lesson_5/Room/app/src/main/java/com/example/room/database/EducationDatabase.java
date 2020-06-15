package com.example.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.room.dao.EducationDao;
import com.example.room.model.Student;

@Database(entities = { Student. class} , version = 1)
public abstract class EducationDatabase extends RoomDatabase {
    public abstract EducationDao getEducationDao() ;
}