package model.persistence.daoInterface;

import model.persistence.entity.Crew;
import model.persistence.entity.User;

import java.util.List;

public interface UserDaoInterface {

    List<User> findAll();
    List<User> findByEmail(String email);
    public void persist(User entity);
    public void update(User entity);
    public void delete(User entity);
    public void deleteAll();
}
