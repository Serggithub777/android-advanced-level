package com.example.studentdatabase.data;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

// @Entity - это признак табличного объекта, то есть объект будет сохраняться
// в базе данных в виде строки
// indices указывает на индексы в таблице
//@Entity (indices = {@Index(value = {"first_name", "last_name"})})
@Entity(indices = { @Index(value = { Student. FIRST_NAME, Student. LAST_NAME} ) } )
public class Student {

    public final static String ID = "id";
    public final static String FIRST_NAME = "first_name";
    public final static String LAST_NAME = "last_name";

    // @PrimaryKey - указывает на ключевую запись,
    // autoGenerate = true - автоматическая генерация ключа
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    public long id;


    // Имя студента
    // @ColumnInfo позволяет задавать параметры колонки в БД
    // name = "first_name" - имя колонки
    //@ColumnInfo(name = "first_name")
    @ColumnInfo(name = FIRST_NAME)
    public String firstName;

    //Фамилия студента
    //@ColumnInfo (name = "last_name")
    @ColumnInfo(name = LAST_NAME)
    public String lastName;

    public Date dateBirth;
    // @Embeddeb позволяет хранить поля вложенного класса как поля таблицы
    @Embedded
    public Address address;

}


