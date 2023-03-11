package com.nisum.registryusers.service;

import com.nisum.registryusers.models.entity.User;
import com.nisum.registryusers.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private JwtTokenService _jwtToken;

    @Override
    public User save(User user) {
        user.setToken(_jwtToken.generateToken(user));
        return _userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) _userRepository.findAll();
    }


}
