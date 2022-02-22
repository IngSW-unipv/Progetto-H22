package model.persistence.service;

import model.persistence.dao.BookDao;
import model.persistence.entity.Book;

import java.util.List;

public class BookService {
    private static BookDao bookDao;

    public BookService() {
        bookDao = new BookDao();
    }

    public void persist(Book entity) {
        bookDao.getConn().openCurrentSessionwithTransaction();
        bookDao.persist(entity);
        bookDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(Book entity) {
        bookDao.getConn().openCurrentSessionwithTransaction();
        bookDao.update(entity);
        bookDao.getConn().closeCurrentSessionwithTransaction();
    }

    public Book findById(String id) {
        bookDao.getConn().openCurrentSession();
        Book book = bookDao.findById(id);
        bookDao.getConn().closeCurrentSession();
        return book;
    }

    public void delete(String id) {
        bookDao.getConn().openCurrentSessionwithTransaction();
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        bookDao.getConn().closeCurrentSessionwithTransaction();
    }

    public List<Book> findAll() {
        bookDao.getConn().openCurrentSession();
        List<Book> books = bookDao.findAll();
        bookDao.getConn().closeCurrentSession();
        return books;
    }

    public void deleteAll() {
        bookDao.getConn().openCurrentSessionwithTransaction();
        bookDao.deleteAll();
        bookDao.getConn().closeCurrentSessionwithTransaction();
    }

    public BookDao bookDao() {
        return bookDao;
    }
}