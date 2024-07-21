package az.vali.computer_store.service;

import az.vali.computer_store.entity.User;
import az.vali.computer_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        user.setId((long) 0);
        User databaseUser = userRepository.save(user);
        return databaseUser;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
