package test;

import client.Client;
import client.ClientService;
import client.Phone;
import org.junit.Test;

import java.util.*;

import static client.Phone.Type.LANDLINE;
import static client.Phone.Type.MOBILE;

public class ClientServiceTest {

    @Test
    public void ageSumForNameTest() {
        assert ClientService.getAgeSumForName("Ivan", CLIENTS) == 46;
        assert ClientService.getAgeSumForName("Oleg", CLIENTS) == 54;
        assert ClientService.getAgeSumForName("Tinkoff", CLIENTS) == 0;
        assert ClientService.getAgeSumForName("any", Collections.emptyList()) == 0;
    }

    @Test
    public void getDistinctNamesTest() {
        var res = ClientService.getDistinctNames(CLIENTS);
        var expected = new LinkedHashSet<>();
        expected.add("Ivan");
        expected.add("Petr");
        expected.add("Aleksandr");
        expected.add("Oleg");
        expected.add("Natasha");
        assert res.equals(expected);
    }

    @Test
    public void greaterAgeTest() {
        assert ClientService.hasClientOlderThan(0, CLIENTS);
        assert ClientService.hasClientOlderThan(30, CLIENTS);
        assert !ClientService.hasClientOlderThan(100, CLIENTS);
        assert !ClientService.hasClientOlderThan(54, CLIENTS);
        assert !ClientService.hasClientOlderThan(10, Collections.emptyList());
    }

    @Test
    public void mapIdToNameTest() {
        var expected = new LinkedHashMap<Long, String>();
        CLIENTS.forEach(client -> expected.put(client.getId(), client.getName()));
        assert ClientService.mapIdToName(CLIENTS).equals(expected);
    }

    @Test
    public void mapAgeToClientsTest() {
        var res = ClientService.mapAgeToClients(CLIENTS);
        CLIENTS.forEach(client -> {
            assert res.get(client.getAge()).contains(client);
        });
    }

    @Test
    public void getAllPhonesTest() {
        var expected =
                "Phone{number='123', type=MOBILE},Phone{number='123', type=LANDLINE}," +
                        "Phone{number='231', type=LANDLINE}," +
                        "Phone{number='101', type=MOBILE}," +
                        "Phone{number='345', type=MOBILE}";
        assert ClientService.getAllPhones(CLIENTS).equals(expected);
    }

    @Test
    public void oldestLandlineUserTest() {
        assert ClientService.getOldestClientWithLandlinePhone(CLIENTS).equals(CLIENTS.get(1));
        assert ClientService.getOldestClientWithLandlinePhone(Collections.emptyList()) == null;
        assert ClientService.getOldestClientWithLandlinePhone(CLIENTS.subList(3, 4)) == null;
    }

    private static final Random ID_GEN = new Random();
    private static final List<Client> CLIENTS = List.of(
            new Client(ID_GEN.nextLong(), "Ivan", 15, List.of(new Phone("123", MOBILE), new Phone("123", LANDLINE))),
            new Client(ID_GEN.nextLong(), "Petr", 28, List.of(new Phone("231", LANDLINE))),
            new Client(ID_GEN.nextLong(), "Aleksandr", 22, Collections.emptyList()),
            new Client(ID_GEN.nextLong(), "Oleg", 54, List.of(new Phone("101", MOBILE))),
            new Client(ID_GEN.nextLong(), "Ivan", 31, List.of(new Phone("345", MOBILE))),
            new Client(ID_GEN.nextLong(), "Natasha", 22, null)
    );
}
