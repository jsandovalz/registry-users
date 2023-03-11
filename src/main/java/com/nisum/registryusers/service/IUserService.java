package com.nisum.registryusers.service;

import com.nisum.registryusers.models.entity.User;

import java.util.List;

public interface IUserService {
    User save(User user);
    List<User> findAll();
}
