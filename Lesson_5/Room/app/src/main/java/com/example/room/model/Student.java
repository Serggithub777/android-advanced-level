package com.example.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

// @Entity - это признак табличного объекта, то есть объект будет сохраняться
// в базе данных в виде строки
// indices указывает на индексы в таблице
@Entity(indices = { @Index(value = { "first_name", "last_name"} ) } )
public class Student {
    // @PrimaryKey - указывает на ключевую запись,
    // autoGenerate = true - автоматическая генерация ключа
    @PrimaryKey(autoGenerate = true)
    public long id;
    // Имя студента
   // @ColumnInfo позволяет задавать параметры колонки в БД
   // name = "first_name" - имя колонки
    @ColumnInfo(name = "first_name")
    public String firstName;
    // Фамилия студента
    @ColumnInfo(name = "last_name")
    public String lastName;
}