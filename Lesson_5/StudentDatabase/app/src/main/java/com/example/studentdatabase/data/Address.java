package com.example.studentdatabase.data;

import androidx.room.ColumnInfo;

// Класс, выделенный для обработки из класса Student. В таблице
// этот класс будет соответствовать полям таблицы student
public class Address {
    public String street;
    public String city;
    @ColumnInfo(name = "post_code")
    public String postCode;
}