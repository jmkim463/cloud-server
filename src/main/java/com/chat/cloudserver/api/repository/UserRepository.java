package com.chat.cloudserver.api.repository;

import com.chat.cloudserver.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT no FROM user WHERE id = :id AND password = :password")
    Optional<Long> findNoByIdAndPassword(@Param("id") String id, @Param("password") String password);

    @Query("SELECT COUNT(*) FROM user WHERE id = :id")
    Optional<Integer> countBySameID(@Param("id") String id);

}
