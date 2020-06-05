package com.example.notes.dataRoom;

import com.example.notes.App;
import com.example.notes.IRepository;
import com.example.notes.data.Note;


    public class RoomRepository implements IRepository {

        private NoteDao noteDao;

        public RoomRepository(){
            noteDao = App.getInstance().getNoteDao();
        }

        @Override
        public Note add(Note note) {
            long id = noteDao.insertNote(note);
            note.setId(id);
            return note;
        }

        @Override
        public void update(Note note) {
            noteDao.update(note);
        }

        @Override
        public Note get(int position) {
            return noteDao.getNote(position);
        }


        @Override
        public void delete(Note note) {
            noteDao.delete(note);
        }

        @Override
        public void deleteAll() {
            noteDao.deleteAll();
        }


        @Override
        public int getCount() {
            return noteDao.getCount();
        }
    }
