package model.persistence.daoInterface;


import model.persistence.entity.Role;

import java.util.List;

public interface RoleDaoInterface {

    List<Role> findAll();
    List<Role> findByRole(String role);
}
