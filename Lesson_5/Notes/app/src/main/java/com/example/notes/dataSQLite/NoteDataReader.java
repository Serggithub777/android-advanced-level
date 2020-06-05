package com.example.notes.dataSQLite;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.notes.data.Note;

import java.io.Closeable;
import java.io.IOException;

public class NoteDataReader implements Closeable {

        private Cursor cursor;              // Курсор, фактически это подготовенный запрос,
        // но сами данные подчитываются только по необходимости

        private SQLiteDatabase database;
        private String[] notesAllColumn = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_NOTE,
                DatabaseHelper.COLUMN_NOTE_TITLE
        };

        public NoteDataReader(SQLiteDatabase database){
            this.database = database;
        }

        // Подготовить к чтению таблицу
        public void open(){
            query();
            cursor.moveToFirst();
        }

        // создание запроса на курсор
        private void query(){
            cursor = database.query(DatabaseHelper.TABLE_NOTES,
                    notesAllColumn, null, null, null, null, null);
        }

        // Перечитать таблицу (если точно, то здесь саму таблицу мы не перечитываем,
        // а просто обновляем курсор.
        public void Refresh(){
            int position = cursor.getPosition();
            query();
            cursor.moveToPosition(position);
        }

        // прочитать данные по определернной позиции
        public Note getPosition(int position){
            cursor.moveToPosition(position);
            return cursorToNote();
        }

        // преобразователь данных курсора в объект
        private Note cursorToNote() {
            Note note = new Note();
            note.setId(cursor.getLong(0));
            note.setDescription(cursor.getString(1));
            note.setTitle(cursor.getString(2));
            return note;
        }

        // получить количество строк в таблице
        public int getCount(){
            return cursor.getCount();
        }

        @Override
        public void close() throws IOException {
            cursor.close();
        }
    }

