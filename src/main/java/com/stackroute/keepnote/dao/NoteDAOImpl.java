package com.stackroute.keepnote.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.hibernate.SessionFactory;

import com.stackroute.keepnote.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database
 * 					transaction. The database transaction happens inside the scope of a persistence
 * 					context.
 * */

@Repository
@Transactional
public class NoteDAOImpl implements NoteDAO {

    /*
     * Autowiring should be implemented for the SessionFactory.
     */
    @Autowired
    private SessionFactory sessionFactory;


    public NoteDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /*
     * Save the note in the database(note) table.
     */

    public boolean saveNote(Note note) {
        if (note.getNoteTitle() == null || note.getNoteContent() == null || note.getNoteStatus() == null) return false;
        sessionFactory.getCurrentSession().save(note);
        return true;
    }

    /*
     * Remove the note from the database(note) table.
     */

    public boolean deleteNote(int noteId) {
        if (getNoteById(noteId) == null) return false;
        else sessionFactory.getCurrentSession().delete(getNoteById(noteId));
        return true;
    }

    /*
     * retrieve all existing notes sorted by created Date in descending
     * order(showing latest note first)
     */
    @SuppressWarnings("unchecked")
    public List<Note> getAllNotes() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Note note");
        return query.getResultList();
    }

    /*
     * retrieve specific note from the database(note) table
     */
    public Note getNoteById(int noteId) {
        Note note = sessionFactory.getCurrentSession().get(Note.class, noteId);
        return note;

    }

    /* Update existing note */

    public boolean UpdateNote(Note note) {

        if (getNoteById(note.getNoteId()) == null) {
        	return false;
		}
        else {
//            sessionFactory.getCurrentSession().clear();
            sessionFactory.getCurrentSession().update(note);
            sessionFactory.getCurrentSession().flush();
            return true;
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
