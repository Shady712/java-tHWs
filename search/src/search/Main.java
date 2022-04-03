package search;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        System.out.println("Tests succeeded!");
    }

    private static void test1() {
        User vasilii = new User("Василий");
        User arina = new User("Арина");
        User yulya = new User("Юля");
        User evgenii = new User("Евгений");
        User konstantin = new User("Константин");
        User vladimir = new User("Владимир");
        User mariya = new User("Мария");
        User dmitrii = new User("Дмитрий");
        User anatolii = new User("Анатолий");
        User gennadii = new User("Геннадий");
        User anna = new User("Анна");
        User mikhail = new User("Михаил");
        User sergei = new User("Сергей");
        User natasha = new User("Наташа");
        vasilii.setFriends(Arrays.asList(arina, yulya));
        arina.setFriends(Arrays.asList(vasilii, dmitrii));
        yulya.setFriends(Arrays.asList(vasilii, konstantin, evgenii));
        evgenii.setFriends(Arrays.asList(yulya, natasha));
        konstantin.setFriends(Arrays.asList(yulya, vladimir, mariya, natasha));
        vladimir.setFriends(Arrays.asList(konstantin, mariya));
        mariya.setFriends(Arrays.asList(konstantin, vladimir));
        dmitrii.setFriends(Arrays.asList(arina, anatolii, sergei));
        anatolii.setFriends(Arrays.asList(dmitrii, gennadii, mikhail));
        gennadii.setFriends(Arrays.asList(anatolii, anna));
        anna.setFriends(Arrays.asList(gennadii, mikhail));
        mikhail.setFriends(Arrays.asList(anatolii, sergei, anna, natasha));
        sergei.setFriends(Arrays.asList(dmitrii, mikhail));
        natasha.setFriends(Arrays.asList(konstantin, evgenii, mikhail));
        SearchService service = new SearchServiceImpl();

        List<User> foundFriends = service.searchForFriendsInDepth(vasilii, "Наташа");

        if (!foundFriends.contains(natasha)) {
            throw new RuntimeException();
        }

        foundFriends = service.searchForFriendsInWidth(vasilii, "Наташа");

        if (!foundFriends.contains(natasha)) {
            throw new RuntimeException();
        }
    }

    private static void test2() {
        User vasilii = new User("Василий");
        User arina = new User("Арина");
        User yulya = new User("Юля");
        User evgenii = new User("Евгений");
        User konstantin = new User("Константин");
        User vladimir = new User("Владимир");
        User mariya = new User("Мария");
        User dmitrii = new User("Дмитрий");
        User anatolii = new User("Анатолий");
        User gennadii = new User("Геннадий");
        User anna = new User("Анна");
        User mikhail = new User("Михаил");
        User sergei = new User("Сергей");
        User natasha = new User("Наташа");
        User natasha2 = new User("Наташа");
        vasilii.setFriends(Arrays.asList(arina, yulya));
        arina.setFriends(Arrays.asList(vasilii, dmitrii, natasha2));
        yulya.setFriends(Arrays.asList(vasilii, evgenii));
        evgenii.setFriends(Arrays.asList(yulya));
        konstantin.setFriends(Arrays.asList(vladimir, mariya));
        vladimir.setFriends(Arrays.asList(konstantin));
        mariya.setFriends(Arrays.asList(konstantin, vasilii));
        dmitrii.setFriends(Arrays.asList(arina, anatolii, natasha));
        anatolii.setFriends(Arrays.asList(dmitrii, gennadii));
        gennadii.setFriends(Arrays.asList(anatolii));
        anna.setFriends(Arrays.asList(mikhail));
        mikhail.setFriends(Arrays.asList(sergei, anna));
        sergei.setFriends(Arrays.asList(natasha, mikhail));
        natasha.setFriends(Arrays.asList(dmitrii, sergei));
        natasha2.setFriends(Arrays.asList(arina));

        SearchService service = new SearchServiceImpl();

        List<User> foundFriends = service.searchForFriendsInDepth(vasilii, "Наташа");

        if (!foundFriends.contains(natasha)) {
            throw new AssertionError();
        }
        if (!foundFriends.contains(natasha2)) {
            throw new AssertionError();
        }

        foundFriends = service.searchForFriendsInWidth(vasilii, "Наташа");

        if (!foundFriends.contains(natasha)) {
            throw new RuntimeException();
        }
        if (!foundFriends.contains(natasha2)) {
            throw new RuntimeException();
        }
    }

    private static void test3() {
        User vasilii = new User("Василий");
        User arina = new User("Арина");
        User mariya = new User("Мария");
        User vladimir = new User("Владимир");
        User evgenii = new User("Евгений");
        User yulya = new User("Юля");
        User konstantin = new User("Константин");
        User dmitrii = new User("Дмитрий");
        User natasha = new User("Наташа");
        vasilii.setFriends(Arrays.asList(arina, mariya, vladimir, evgenii));
        arina.setFriends(Arrays.asList(vasilii, dmitrii, vasilii));
        yulya.setFriends(Arrays.asList(konstantin, evgenii, vladimir, dmitrii));
        evgenii.setFriends(Arrays.asList(yulya, vasilii, dmitrii));
        konstantin.setFriends(Arrays.asList(vladimir, mariya, yulya, natasha));
        vladimir.setFriends(Arrays.asList(konstantin, vasilii, yulya));
        mariya.setFriends(Arrays.asList(konstantin, vasilii, natasha));
        dmitrii.setFriends(Arrays.asList(arina, evgenii, natasha, yulya));
        natasha.setFriends(Arrays.asList(dmitrii, arina, konstantin, mariya));

        SearchService service = new SearchServiceImpl();

        List<User> foundFriends = service.searchForFriendsInDepth(vasilii, "Наташа");

        if (!foundFriends.contains(natasha)) {
            throw new RuntimeException();
        }

        foundFriends = service.searchForFriendsInWidth(vasilii, "Наташа");

        if (!foundFriends.contains(natasha)) {
            throw new RuntimeException();
        }
    }
}
