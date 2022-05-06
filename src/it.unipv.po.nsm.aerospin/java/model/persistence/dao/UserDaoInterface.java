package model.persistence.dao;

import model.persistence.entity.User;

import java.util.List;

public interface UserDaoInterface {

    List<User> findAll();
    User findByEmail(String email);
    void persist(User entity);
    void update(User entity);
    void delete(User entity);
    void deleteAll();
}
