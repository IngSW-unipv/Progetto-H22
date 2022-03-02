package model.persistence.service;

import model.persistence.dao.RoleDao;
import model.persistence.entity.Role;
import model.persistence.entity.Route;

import java.util.List;

public class RoleService {

    private static RoleDao roleDao;

    public RoleService() {
        roleDao = new RoleDao();
    }

    public List<Role> findAll() {
        roleDao.getConn().openCurrentSession();
        List<Role> roles = roleDao.findAll();
        roleDao.getConn().closeCurrentSession();
        return roles;
    }

    public List<Role> findByRole(String role) {
        roleDao.getConn().openCurrentSession();
        List<Role> roles = roleDao.findByRole(role);
        roleDao.getConn().closeCurrentSession();
        return roles;
    }
}
