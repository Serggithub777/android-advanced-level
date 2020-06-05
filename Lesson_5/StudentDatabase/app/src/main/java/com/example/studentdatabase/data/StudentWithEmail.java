package com.example.studentdatabase.data;

import androidx.room.ColumnInfo;

// Результат запроса к двум таблицам одновременно
public class StudentWithEmail {
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    public String email;
}