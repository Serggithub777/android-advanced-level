package com.example.notes.dataSQLite;


import android.content.Context;

import com.example.notes.IRepository;
import com.example.notes.data.Note;

    public class SQLiteRepository implements IRepository {

        private NoteDataSource notesDataSource;     // Источник данных
        private NoteDataReader noteDataReader;      // Читатель данных

        public SQLiteRepository(Context context){
            notesDataSource = new NoteDataSource(context);
            notesDataSource.open();
            noteDataReader = notesDataSource.getNoteDataReader();
        }

        @Override
        public Note add(Note note) {
            Note newNote = notesDataSource.addNote(note);
            updatedNote();
            return newNote;
        }

        @Override
        public void update(Note note) {
            notesDataSource.editNote(note);
            updatedNote();
        }

        @Override
        public Note get(int position) {
            return noteDataReader.getPosition(position);
        }

        @Override
        public void delete(Note note) {
            notesDataSource.deleteNote(note);
            updatedNote();
        }

        @Override
        public void deleteAll() {
            notesDataSource.deleteAll();
            updatedNote();
        }

        @Override
        public int getCount() {
            return noteDataReader.getCount();
        }

        private void updatedNote() {
            noteDataReader.Refresh();
        }
    }

