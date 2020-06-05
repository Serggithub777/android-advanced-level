package com.example.studentdatabase.dao;

import android.widget.LinearLayout;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentdatabase.data.Email;
import com.example.studentdatabase.data.Student;
import com.example.studentdatabase.data.StudentEmail;
import com.example.studentdatabase.data.StudentWithEmail;

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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   long insertStudent(Student student);

    // Метод для замены данных студента
    @Update
    void updateStudent(Student student);

    // Удаляем данные студента
    @Delete
    void deleteStudent(Student student);

    // Удаляем данные студента, зная ключ
    @Query("DELETE FROM student WHERE id=:id")
    void deleteStudentById(long id);

    // Забираем данные по всем студентам
    @Query("SELECT * FROM student")
    List<Student> getAllStudents();

    // Получаем данные одного студента по id
    @Query("SELECT * FROM student WHERE id = :id")
            Student getStudentById(long id);

    //Получаем количество записей в таблице
    @Query("SELECT COUNT() FROM student")
    long getCountStudents();

    // Получаем почту одного студента
    @Query("SELECT * FROM email WHERE student_id = :studentId")
    List<Email> getEmailByStudent(long studentId) ;
    // Добавляем запись в таблицу адресов
    @Insert
    long insertEmail(Email email) ;

    // Запрос сразу к двум таблицам
    @Query("SELECT first_name, last_name, email " +
            "FROM student " +
            "INNER JOIN email ON student.id = email.student_id")
    List<StudentWithEmail> getStudentWithEmail() ;

    // Запрос через Relation
    @Query("SELECT * FROM student")
    List<StudentEmail> getStudentEmails() ;
    // Получаем через Relation одного студента
    @Query("SELECT * FROM student WHERE id = :id")
    StudentEmail getOneStudentEmails(long id) ;
}
