package com.exam.services.impl;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findByUsername(user.getUsername());
        if(local != null){
            System.out.println("User is already there !!");
            throw new Exception("User already present !!");
        }else{
           //user create
            for(UserRole ur : userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(String username, User user) {
        //get user from db
        User byUsername = this.userRepository.findByUsername(username);
        //if user is != null
        if(byUsername != null){
            //user.set(the one which u will get in the requst for update)user.setName() user.getName
            byUsername.setUsername(user.getUsername());
            byUsername.setFirstName(user.getFirstName());
            byUsername.setLastName(user.getLastName());
            byUsername.setEmail(user.getEmail());
            byUsername.setPhone(user.getPhone());
            byUsername.setProfile(user.getProfile());
            byUsername.setPassword(user.getPassword());
            //repository.save()
            this.userRepository.save(byUsername);
        }else{
            System.out.println(byUsername.getUsername()+" User not found");
        }
        //return the updated user
        return byUsername;
    }


}
