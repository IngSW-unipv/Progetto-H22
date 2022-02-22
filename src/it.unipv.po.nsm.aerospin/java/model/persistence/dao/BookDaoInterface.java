package model.persistence.dao;

import model.persistence.entity.Book;

import java.util.List;

public interface BookDaoInterface {
    public void persist(Book entity);
    public void update(Book entity);
    public Book findById(String id);
    public void delete(Book entity);
    public List<Book> findAll();
    public void deleteAll();
}
