package model.persistence.dao;

import model.persistence.entity.User;

import java.util.List;

public interface UserDaoInterface {

    List<User> findAll();
    User findByEmail(String email);
    public void persist(User entity);
    public void update(User entity);
    public void delete(User entity);
    public void deleteAll();
}
