package model.flight.route;

import java.util.List;

public class TestSearch {

    public static void main(String[] args) {
        Search search = new Search();
        List<String> servedDepartures = search.getServedDepartures();
        for (String s: servedDepartures) {
            System.out.printf(s);
        }
    }




}
