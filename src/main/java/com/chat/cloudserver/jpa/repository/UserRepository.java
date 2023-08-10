package com.chat.cloudserver.jpa.repository;

import com.chat.cloudserver.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT no FROM user WHERE id = :id AND password = :password")
    Optional<String> findUserNoByIdAndPassword(@Param("id") String id, @Param("password") String password);


}
