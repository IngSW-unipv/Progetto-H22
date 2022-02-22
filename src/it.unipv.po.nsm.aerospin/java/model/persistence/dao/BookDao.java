package model.persistence.dao;

import model.persistence.Connection;
import model.persistence.entity.Book;

import java.util.List;

public class BookDao implements BookDaoInterface{
    private Connection conn;

    public BookDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<Book> findAll() {
        List<Book> books = (List<Book>) conn.getCurrentSession().createQuery("from Book").list();
        return books;
    }

    public Book findById(String id) {
        Book book = (Book) conn.getCurrentSession().get(Book.class, id);
        return book;
    }

    public void deleteAll() {
        List<Book> entityList = findAll();
        for (Book entity : entityList) {
            delete(entity);
        }
    }



    public void persist(Book entity) {
        conn.getCurrentSession().save(entity);
    }

    public void update(Book entity) {
        conn.getCurrentSession().update(entity);
    }


    public void delete(Book entity) {
        conn.getCurrentSession().delete(entity);
    }

}

