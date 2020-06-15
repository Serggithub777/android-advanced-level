package com.example.room;

import com.example.room.dao.EducationDao;
import com.example.room.model.Student;

import java.util.List;

// Вспомогательный класс, развязывающий зависимость между Room и RecyclerView
public class EducationSource {
    private final EducationDao educationDao;
    // Буфер с данными: сюда будем подкачивать данные из БД
    private List<Student> students;
    public EducationSource(EducationDao educationDao) {
        this. educationDao = educationDao;
    }
    // Получить всех студентов
    public List<Student> getStudents() {
// Если объекты еще не загружены, загружаем их.
// Это сделано для того, чтобы не делать запросы к БД каждый раз
        if (students == null) {
            LoadStudents() ;
        }return students;
    }
    // Загружаем студентов в буфер
    public void LoadStudents() {
        students = educationDao. getAllStudents() ;
    }
    // Получаем количество записей
    public long getCountStudents() {
        return educationDao. getCountStudents() ;
    }
    // Добавляем студента
    public void addStudent(Student student) {
        educationDao. insertStudent(student) ;
// После изменения БД надо повторно прочесть данные из буфера
        LoadStudents() ;
    }
    // Заменяем студента
    public void updateStudent(Student student) {
        educationDao. updateStudent(student) ;
        LoadStudents() ;
    }
    // Удаляем студента из базы
    public void removeStudent(long id) {
        educationDao. deteleStudentById(id) ;
        LoadStudents() ;
    }
}
