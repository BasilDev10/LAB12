package com.example.lab12.Service;

import com.example.lab12.Model.MyUser;
import com.example.lab12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public void registerUser(MyUser myUser){
        myUser.setRole("USER");
        myUser.setPassword(new BCryptPasswordEncoder().encode(myUser.getPassword()));
        authRepository.save(myUser);
    }

    public List<MyUser> getAllUsers(){
        return authRepository.findAll();
    }
}
