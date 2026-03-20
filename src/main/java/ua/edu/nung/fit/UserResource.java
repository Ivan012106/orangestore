package ua.edu.nung.fit;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/api/users") // Шлях до вашого API
@Produces(MediaType.APPLICATION_JSON) // Сервер завжди віддає JSON
@Consumes(MediaType.APPLICATION_JSON) // Сервер приймає JSON (для POST)
public class UserResource {

    // Тимчасовий список для імітації бази даних (для ЛР4 цього достатньо)
    private static List<User> users = new ArrayList<>();

    @GET
    public List<User> getUsers() {
        return users;
    }

    @POST
    public User addUser(User user) {
        users.add(user);
        return user;
    }
}