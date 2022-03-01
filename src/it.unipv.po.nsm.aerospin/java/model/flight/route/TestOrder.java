package model.flight.route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestOrder {
    public static void main(String[] args) {
        List<String> s = new ArrayList<>();
        s.add("bravo");
        s.add("alpha");
        s.add("bravo");
        s.add("bravo");
        s.add("sierra");
        s.add("hotel");
        s.add("paul");

        s.sort(Comparator.naturalOrder());
        s = s.stream().distinct().collect(Collectors.toList());

        for (String a:s) {
            System.out.println(a);
        }


    }
}
