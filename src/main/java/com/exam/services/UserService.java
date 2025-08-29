package com.exam.services;

import com.exam.entity.User;
import com.exam.entity.UserRole;

import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    //delete user by Id
    public void deleteUser(Long userId);

    //update user by Id

    public User updateUser(String username, User user);
}
