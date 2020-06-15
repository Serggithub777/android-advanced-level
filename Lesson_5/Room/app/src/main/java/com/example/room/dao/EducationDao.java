package com.example.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room.model.Student;

import java.util.List;

// Описание объекта, обрабатывающего данные
// @Dao - доступ к данным
// В этом классе описывается, как будет происходить обработка данных
@Dao
public interface EducationDao {
    // Метод для добавления студента в базу данных
    // @Insert - признак добавления
    // onConflict - что делать, если такая запись уже есть
    // В данном случае просто заменим её
    @Insert(onConflict = OnConflictStrategy. REPLACE)
    void insertStudent(Student student) ;

    // Метод для замены данных студента
    @Update
    void updateStudent(Student student) ;

    // Удаляем данные студента
    @Delete
    void deleteStudent(Student student) ;

    // Удаляем данные студента, зная ключ
    @Query("DELETE FROM student WHERE id = :id")
    void deteleStudentById(long id) ;

    // Забираем данные по всем студентам
    @Query("SELECT * FROM student")
    List<Student> getAllStudents() ;

    // Получаем данные одного студента по id
    @Query("SELECT * FROM student WHERE id = :id")
    Student getStudentById(long id) ;

    //Получаем количество записей в таблице
    @Query("SELECT COUNT() FROM student")
    long getCountStudents() ;
    }