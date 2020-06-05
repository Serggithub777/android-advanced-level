package com.example.notes.dataSQLite;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.notes.data.Note;

import java.io.Closeable;
import java.io.IOException;

public class NoteDataSource implements Closeable {
        private DatabaseHelper dbHelper;
        private SQLiteDatabase database;
        private NoteDataReader noteDataReader;

        public NoteDataSource(Context context) {
            dbHelper = new DatabaseHelper(context);
        }

        // Открывает базу данных
        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
            // создать читателя и открыть его
            noteDataReader = new NoteDataReader(database);
            noteDataReader.open();
        }

        @Override
        public void close() throws IOException {
            if (noteDataReader != null) noteDataReader.close();
            if (dbHelper != null) dbHelper.close();
        }

        // Добавить новую запись
        public Note addNote(Note note) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_NOTE, note.getDescription());
            values.put(DatabaseHelper.COLUMN_NOTE_TITLE, note.getTitle());
            // Добавление записи
            long insertId = database.insert(DatabaseHelper.TABLE_NOTES, null,
                    values);
            return note.Copy(insertId);
        }

        // Изменить запись
        // update Notes set Title = :title, Note = :description where _id = :id
        public void editNote(Note note) {
            ContentValues editedNote = new ContentValues();
            editedNote.put(DatabaseHelper.COLUMN_ID, note.getId());
            editedNote.put(DatabaseHelper.COLUMN_NOTE, note.getDescription());
            editedNote.put(DatabaseHelper.COLUMN_NOTE_TITLE, note.getTitle());
            // изменение записи
            database.update(DatabaseHelper.TABLE_NOTES,
                    editedNote,
                    DatabaseHelper.COLUMN_ID + "=" + note.getId(),
                    null);
        }

        // Удалить запись
        public void deleteNote(Note note) {
            long id = note.getId();
            database.delete(DatabaseHelper.TABLE_NOTES, DatabaseHelper.COLUMN_ID
                    + " = " + id, null);
        }

        // Очистить таблицу
        public void deleteAll() {
            database.delete(DatabaseHelper.TABLE_NOTES, null, null);
        }

        // вернуть читателя (он потребуется в других местах)
        public NoteDataReader getNoteDataReader(){
            return noteDataReader;
        }
    }

