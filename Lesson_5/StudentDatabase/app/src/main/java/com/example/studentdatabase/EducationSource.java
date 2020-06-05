package com.example.studentdatabase;

import com.example.studentdatabase.dao.EducationDao;
import com.example.studentdatabase.data.Email;
import com.example.studentdatabase.data.Student;
import com.example.studentdatabase.data.StudentEmail;

import java.util.List;

public class EducationSource {

    private final EducationDao educationDao;

    // Буфер с данными: сюда будем подкачивать данные из БД
    private List<Student> students;

    public EducationSource(EducationDao educationDao) {
        this.educationDao = educationDao;
    }
     // Получить всех студентов

    public List<Student> getStudents() {
        // Если объекты еще не загружены, загружаем их.
        // Это сделано для того, чтобы не делать запросы к БД каждый раз
        if (students == null) {
            loadStudents();
        }
        return students;
    }

    // Загружаем студентов в буфер
    public void loadStudents() {
        students=educationDao.getAllStudents();
    }

    // Получаем количество записей
    public long getCountStudents() {
        return educationDao.getCountStudents();
    }

    // Добавляем студента
    public void addStudent(Student student) {
        long id = educationDao. insertStudent(student) ;
        Email email1 = new Email() ;
        email1. studentId = id;
        email1. email = "abc@mail. edu";
        educationDao. insertEmail(email1) ;
        Email email2 = new Email() ;
        email2. studentId = id;
        email2. email = "cde@another. mail";
        educationDao. insertEmail(email2) ;
// После изменения БД надо повторно прочесть данные из буфера
        loadStudents();
    }public StudentEmail getStudentEmail(long id) {
        return educationDao. getOneStudentEmails(id) ;
    }

    //Заменяем студента
    public void updateStudent(Student student) {
        educationDao.updateStudent(student);
        loadStudents();
    }

    // Удаляем студента из базы
    public void removeStudent(long id) {
        educationDao.deleteStudentById(id);
        loadStudents();
    }



}
