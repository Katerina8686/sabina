package Lesson4;


import java.util.*;

class Lesson4 {
    public static void main(String[] args) {
        zadanie1();
        zadanie2();
    }

    private static void zadanie1() {
        Map<String, Integer> hm = new HashMap<>();
        String[] words = {
                "Cat", "Cat", "Dog",
                "lemon", "Apple", "Apple",
                "Dog", "Cat", "Java",
                "Yava", "Kent", "Coffee",
                "Dog", "Beta", "Humster",
                "Cat", "Java", "Yava", "Dog"
        };

        for (int i = 0; i < words.length; i++) {
            if (hm.containsKey(words[i]))
                hm.put(words[i], hm.get(words[i]) + 1);
            else
                hm.put(words[i], 1);
        }
        System.out.println(hm);
    }

    private static void zadanie2() {
        Directory directory = new Directory();

        directory.add("Alex", "8999123321");
        directory.add("Alex", "8912155326");
        directory.add("Scot", "8917155552");
        directory.add("Scot", "8913455672");
        directory.add("Alex", "899999999");
        directory.add("James", "899111111");
        directory.add("Scot", "89923231999");
        directory.add("Dan", "8888123113");
        directory.add("James", "8324325234");

        System.out.println(directory.get("Alex"));
        System.out.println(directory.get("James"));
        System.out.println(directory.get("Scot"));
        System.out.println(directory.get("Dan"));
    }
}

class Directory {
    private Map<String, List<String>> directory_hm = new HashMap<>();
    private List<String> phone_number_list;

    public void add(String surname, String phone_number) {
        if (directory_hm.containsKey(surname)) {
            phone_number_list = directory_hm.get(surname);
            phone_number_list.add(phone_number);
            directory_hm.put(surname, phone_number_list);
        } else {
            phone_number_list = new ArrayList<>();
            phone_number_list.add(phone_number);
            directory_hm.put(surname, phone_number_list);
        }
    }

    public List<String> get(String surname) {
        return directory_hm.get(surname);
    }
}