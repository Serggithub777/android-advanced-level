package com.example.notes;

import com.example.notes.data.Note;
import com.example.notes.ui.AdapterChangeable;

public class NoteLogic {
    private IRepository repository;
    private AdapterChangeable adapter;           // Отправим сигнал изменения данных

    public NoteLogic(IRepository repository){
        this.repository = repository;
    }

    public void setAdapter(AdapterChangeable adapter){
        this.adapter = adapter;
    }

    public void addNote(Note note){
        getRepository().add(note);
        updateNote();
    }

    public void editNote(Note note) {
        getRepository().update(note);
        updateNote();
    }

    public void deleteNote(Note note) {
        getRepository().delete(note);
        updateNote();
    }

    public void clearList() {
        getRepository().deleteAll();
        updateNote();
    }

    private void updateNote(){
        adapter.notifyDataChange();
    }

    public IRepository getRepository() {
        return repository;
    }
}
