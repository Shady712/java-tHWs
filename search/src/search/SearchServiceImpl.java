package search;

import java.util.*;

public class SearchServiceImpl implements SearchService {

    @Override
    public List<User> searchForFriendsInWidth(User me, String name) {
        List<User> result = new ArrayList<>();
        Queue<User> queue = new ArrayDeque<>();
        Set<User> visited = new HashSet<>();
        queue.add(me);
        visited.add(me);
        while (!queue.isEmpty()) {
            final var user = queue.remove();
            if (user.getName().equals(name)) {
                result.add(user);
            }
            for (final var friend : user.getFriends()) {
                if (!visited.contains(friend)) {
                    queue.add(friend);
                    visited.add(friend);
                }
            }
        }
        return result;
    }

    @Override
    public List<User> searchForFriendsInDepth(User me, String name) {
        List<User> result = new ArrayList<>();
        Set<User> visited = new HashSet<>();
        dfs(me, visited, result, name);
        return result;
    }

    private void dfs(User user, Set<User> visited, List<User> result, String name) {
        visited.add(user);
        if (user.getName().equals(name)) {
            result.add(user);
        }
        for (final var friend : user.getFriends()) {
            if (!visited.contains(friend)) {
                dfs(friend, visited, result, name);
            }
        }
    }
}
