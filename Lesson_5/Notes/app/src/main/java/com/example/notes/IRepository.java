package com.example.notes;

import com.example.notes.data.Note;

    public interface IRepository {
        Note add(Note note);
        void update(Note note);
        Note get(int position);
        void delete(Note note);
        void deleteAll();
        int getCount();
    }


