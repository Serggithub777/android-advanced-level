package com.example.studentdatabase.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.studentdatabase.dao.EducationDao;
import com.example.studentdatabase.data.DateConverter;
import com.example.studentdatabase.data.Email;
import com.example.studentdatabase.data.Student;

@Database(entities = {Student.class, Email.class}, version = 2)
@TypeConverters(DateConverter.class)
public abstract class EducationDatabase extends RoomDatabase {
    public abstract EducationDao getEducationDao();
}
