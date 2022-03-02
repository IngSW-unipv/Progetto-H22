package model.persistence.dao;


import model.persistence.entity.Role;

import java.util.List;

public interface RoleDaoInterface {

    List<Role> findAll();
    List<Role> findByRole(String role);
}
