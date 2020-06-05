package com.example.studentdatabase.data;

import androidx.room.Embedded;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

public class StudentEmail implements Serializable {
    @Embedded
    public Student student;
    @Relation(parentColumn = "id", entityColumn = "student_id")
    public List<Email> emails;
}
