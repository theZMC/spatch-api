package app.spatch.service;

import java.util.List;

import app.spatch.dao.DAO;
import app.spatch.model.Note;

public class NoteService {
  private DAO<Note> dao = new DAO<Note>(Note.class);

  public Note getNoteById(Integer id){
    return dao.selectById(id);
  }

  public List<Note> getNotesByDispatchId(Integer dispatchId){
    return dao.select(Note.BY_DISPATCH, dispatchId);
  }

  public List<Note> getNotes(){
    return dao.selectAll();
  }

  public Note createNote(Note note){
    return dao.insert(note);
  }

  public Note updateNote(Note note){
    return dao.update(note);
  }

  public Note deleteNote(Note note){
    return dao.delete(note);
  }
}
