package com.example.notes.dataRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notes.data.Note;

    @Database(entities = {Note.class}, version = 1)
    public abstract class DatabaseNote extends RoomDatabase {
        public abstract NoteDao getNoteDao();
    }

