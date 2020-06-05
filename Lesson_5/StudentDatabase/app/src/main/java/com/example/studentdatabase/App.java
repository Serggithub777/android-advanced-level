package com.example.studentdatabase;

import android.app.Application;

import androidx.room.Room;

import com.example.studentdatabase.dao.EducationDao;
import com.example.studentdatabase.database.EducationDatabase;
import com.example.studentdatabase.database.Migration_1_2;

// Паттерн Singleton, наследуем класс Application, создаём базу данных
// в методе onCreate
public class App extends Application {
      private static  App instance;

      //База данных
    private EducationDatabase db;

    //получаем объект приложения
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //сохраняем объект приложения для сингл тона
        instance = this;
        //строим базу
        db= Room.databaseBuilder(
                getApplicationContext(),
                EducationDatabase.class,
                "education_database")
                .allowMainThreadQueries() //Только для примеров и тестирования
                . addMigrations(new Migration_1_2(1,2))
                .build();
        }


        // Получаем EducationDao для составления запросов
       public EducationDao getEducationDao() {
        return db.getEducationDao();
    }
}
