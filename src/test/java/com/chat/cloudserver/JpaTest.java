package com.chat.cloudserver;

import com.chat.cloudserver.jpa.repository.UserRepository;
import com.chat.cloudserver.jpa.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class JpaTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testJpaService() {
        UserEntity userEntity = UserEntity.builder()
                .no("USER-000002")
                .id("tester")
                .password("tester1234!")
                .name("테스터")
                .email("test@gmail.com")
                .imageFilePath("temp")
                .build();

        System.out.println(userRepository);

        userRepository.save(userEntity);
    }

}
