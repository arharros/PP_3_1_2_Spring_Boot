package ru.kata.pp_3_1_2_spring_boot.services;


import ru.kata.pp_3_1_2_spring_boot.model.User;

import java.util.List;

public interface UsersServices {

    void addUser(User user);

    Object findUserById(long id);

    void deleteUserById(long id);

    void updateUserById(long id, User user);

    List<User> listOfUsers();

}
