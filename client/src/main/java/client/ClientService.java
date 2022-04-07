package client;

import java.util.*;
import java.util.stream.Collectors;

import static client.Phone.Type.LANDLINE;

public class ClientService {

    public static int getAgeSumForName(String name, List<Client> clients) {
        return clients.stream()
                .filter(client -> client.getName().equals(name))
                .map(Client::getAge)
                .reduce(0, Integer::sum);
    }

    public static Set<String> getDistinctNames(List<Client> clients) {
        return clients.stream().map(Client::getName).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static boolean hasClientOlderThan(int age, List<Client> clients) {
        return clients.stream().anyMatch(client -> client.getAge() > age);
    }

    public static Map<Long, String> mapIdToName(List<Client> clients) {
        return clients.stream().collect(Collectors.toMap(Client::getId, Client::getName));
    }

    public static Map<Integer, List<Client>> mapAgeToClients(List<Client> clients) {
        return clients.stream().collect(Collectors.groupingBy(Client::getAge));
    }

    public static Client getOldestClientWithLandlinePhone(List<Client> clients) {
        return clients.stream()
                .filter(client -> client.getPhones() != null &&
                        client.getPhones().stream().anyMatch(phone -> phone.getType().equals(LANDLINE)))
                .max(Comparator.comparing(Client::getAge)).orElse(null);
    }

    public static String getAllPhones(List<Client> clients) {
        return clients.stream()
                .filter(client -> client.getPhones() != null && !client.getPhones().isEmpty())
                .flatMap(client -> client.getPhones().stream())
                .map(Phone::toString)
                .collect(Collectors.joining(","));
    }
}
