package bugztracker.service.impl;


import bugztracker.entity.User;
import bugztracker.repository.IUserRepository;
import bugztracker.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Y. Vovk on 17.09.15.
 */
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User get(long id) {
        return userRepository.get(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void add(User entity) {
        userRepository.add(entity);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public void update(User entity) {
        userRepository.update(entity);
    }

    @Override
    public User find(String email) {
        return userRepository.find(email);
    }

    @Override
    public List<User> findAll(String query) {
        return userRepository.findAll(query);
    }

}
