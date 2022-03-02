package model.management;

import model.persistence.entity.Role;
import model.persistence.service.RoleService;

import java.util.List;

public class TestSearch {

    public static void main(String[] args) {
        SearchManager searchManager = new SearchManager();

        List<String> servedDepartures = searchManager.getServedArrivals("Lamezia Terme Airport");

        for (String s: servedDepartures) {
            System.out.printf(s);
        }

        System.exit(0);





    }






}
