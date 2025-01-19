package me.ahngeunsu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.User;
import me.ahngeunsu.springbootdeveloper.dto.AddUserRequest;
import me.ahngeunsu.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                        .email(dto.getEmail())
                // 1. 패스워드 암호화
                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long UserId){
        return userRepository.findById(UserId)
                .orElseThrow(() -> new IllegalArgumentException("unexpected user"));
    }
    /*
        RefreshTokenService.java
    */
}