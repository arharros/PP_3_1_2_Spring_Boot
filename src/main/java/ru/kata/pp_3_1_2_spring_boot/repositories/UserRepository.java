package ru.kata.pp_3_1_2_spring_boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.pp_3_1_2_spring_boot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
