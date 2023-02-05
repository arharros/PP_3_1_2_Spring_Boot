package ru.kata.pp_3_1_2_spring_boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pp_3_1_2_spring_boot.model.User;
import ru.kata.pp_3_1_2_spring_boot.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersServicesImp implements UsersServices {

    private final UserRepository userRepository;

    @Autowired
    public UsersServicesImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserById(long id) {
        Optional<User> findUser = userRepository.findById(id);
        return findUser.orElse(null);
    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateUserById(long id, User userForUpdate) {
        userForUpdate.setIdUser(id);
        userRepository.save(userForUpdate);
    }

    @Override
    public List<User> listOfUsers() {
        return userRepository.findAll(Sort.by(Sort.Order
                .by("idUser")));
    }

}
