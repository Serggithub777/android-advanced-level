package com.example.room;

import android.app.Application;

import androidx.room.Room;

import com.example.room.dao.EducationDao;
import com.example.room.database.EducationDatabase;

// Паттерн Singleton, наследуем класс Application, создаём базу данных
// в методе onCreate
public class App extends Application {
    private static App instance;
    // База данных
    private EducationDatabase db;
    // Получаем объект приложения
    public static App getInstance() {
        return instance;
    }@Override
    public void onCreate() {
        super. onCreate() ;
// Сохраняем объект приложения (для Singleton’а)
        instance = this;
// Строим базу
        db = Room. databaseBuilder(
                getApplicationContext() ,
                EducationDatabase. class,
                "education_database")
                . allowMainThreadQueries() //Только для примеров и тестирования.
                . build() ;
    }
    // Получаем EducationDao для составления запросов
    public EducationDao getEducationDao() {
        return db. getEducationDao() ;
    }
}