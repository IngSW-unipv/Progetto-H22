package model.persistence.dao;

import model.persistence.Connection;

import model.persistence.entity.Book;
import model.persistence.entity.Route;

import java.util.List;

public class RouteDao implements RouteDaoInterface{
    private Connection conn;

    public RouteDao() {
        this.conn = new Connection();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn){
        this.conn = conn;
    }


    @Override
    public void persist(Route entity) {

    }

    @Override
    public void update(Route entity) {

    }

    @Override
    public Route findById(String id) {
        return null;
    }

    @Override
    public void delete(Route route) {

    }

    @Override
    public List<Route> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
