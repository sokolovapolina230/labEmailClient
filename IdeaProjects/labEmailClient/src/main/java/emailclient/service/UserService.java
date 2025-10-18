package emailclient.service;

import emailclient.model.User;
import emailclient.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public void addUser(User user) { userRepository.add(user); }
    public List<User> getAllUsers() { return userRepository.getAll(); }
    public void updateUser(User user) { userRepository.update(user); }
    public void deleteUser(int id) { userRepository.delete(id); }
}

