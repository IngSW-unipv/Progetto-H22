package model.management;

import model.management.Search;

import java.util.List;

public class TestSearch {

    public static void main(String[] args) {
        Search search = new Search();
        List<String> servedDepartures = search.getServedArrivals("Lamezia Terme Airport");

        for (String s: servedDepartures) {
            System.out.printf(s);
        }

        System.exit(0);





    }






}
