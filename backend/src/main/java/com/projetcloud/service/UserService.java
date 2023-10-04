package com.projetcloud.service;

import com.projetcloud.dto.request.UserDto;
import com.projetcloud.exceptions.BadLoginException;
import com.projetcloud.exceptions.LoginAlreadyUsedException;
import com.projetcloud.repository.UserRepository;
import com.projetcloud.util.JwtTokenUtil;
import com.projetcloud.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtTokenUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, JwtTokenUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(UserDto utilisateur) throws BadLoginException {
        Optional<User> user = userRepository.findByUsername(utilisateur.getUsername());

        if (user.isEmpty()) {
            throw new BadLoginException("Utilisateur introuvable");
        }

        if (!passwordEncoder.matches(utilisateur.getPassword(), user.get().getPassword())) {
            throw new BadLoginException("Utilisateur introuvable");
        }

        return jwtUtil.generateToken(user.get());
    }

    public String register(UserDto utilisateur) throws LoginAlreadyUsedException {
        Optional<User> user = userRepository.findByUsername(utilisateur.getUsername());

        if (user.isPresent()) {
            throw new LoginAlreadyUsedException("Utilisateur existant");
        }

        String pwHashed = passwordEncoder.encode(utilisateur.getPassword());
        List<User.Roles> roles = new ArrayList<>();
        roles.add(User.Roles.ROLE_USER);

        User addedUser = new User(utilisateur.getUsername(), pwHashed, roles);

        userRepository.save(addedUser);
        return jwtUtil.generateToken(addedUser);
    }
}
