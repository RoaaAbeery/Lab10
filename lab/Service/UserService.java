package com.example.lab.Service;

import com.example.lab.Model.User;
import com.example.lab.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> getusers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public Boolean updateUser(Integer id,User user){
        User olduser=userRepository.getById(id);
        if(olduser==null){
            return false;
        }
        olduser.setName(user.getName());
        olduser.setEmail(user.getEmail());
        olduser.setPassword(user.getPassword());
        olduser.setAge(user.getAge());
        olduser.setRole(user.getRole());
        userRepository.save(olduser);
        return true;
    }
    public Boolean deleteUser(Integer id){
        User oldUser=userRepository.getById(id);
        if(oldUser==null){
            return false;
        }
        userRepository.delete(oldUser);
        return true;
    }
}
