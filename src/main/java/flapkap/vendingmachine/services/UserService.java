package flapkap.vendingmachine.services;

import flapkap.vendingmachine.data.entities.User;
import flapkap.vendingmachine.data.repositories.UserRepository;
import flapkap.vendingmachine.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User register(User user){
        return this.userRepository.save(user);
    }

    public User updateUser(User user){
        return this.userRepository.save(user);
    }

    public void deleteUser(User user){
        this.userRepository.delete(user);
    }

    public User getUserById(Long id){
       return this.userRepository.findById(id).orElseThrow(() ->
               BusinessException.badRequest("User not found", HttpStatus.BAD_REQUEST.toString()));
    }
}
