package com.chat.cloudserver.login;

import com.chat.cloudserver.module.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class LoginController {

    @GetMapping("/login")
    public UserDTO login(@RequestParam String id, @RequestParam String password) {
        UserDTO userDTO = null;

        if("test01".equals(id) && "test01!".equals(password)) {
             userDTO = UserDTO.builder()
                    .no(1)
                    .id(id)
                    .password(password)
                    .name("이름")
                    .email("test@gmail.com")
                    .build();
        }

        return userDTO;
    }

    @GetMapping("/findByID")
    public String findByID(@RequestParam String email) {
        return null;
    }

    @GetMapping("/findByPassword")
    public String findByPassword(@RequestParam String id, @RequestParam String email) {
        return null;
    }

}
