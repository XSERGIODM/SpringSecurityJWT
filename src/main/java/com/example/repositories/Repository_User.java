package com.example.repositories;

import com.example.models.Model_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repository_User extends JpaRepository<Model_User, Long> {
    Optional<Model_User> findByUsername(String username);


    /*
    para una búsqueda mas personalizada
    @Query("select users from users  where users.username = ?1")
    Optional<Model_User> getName (String username);
    */
}