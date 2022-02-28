package model.persistence.dao;

import model.persistence.entity.User;

import java.util.List;

public interface UserDaoInterface {

    List<User> findAll();
    List<User> findByEmail(String email);
}
