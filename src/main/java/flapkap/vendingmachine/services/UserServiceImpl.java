package flapkap.vendingmachine.services;

import flapkap.vendingmachine.api.mapper.UserMapper;
import flapkap.vendingmachine.data.dto.UserDto;
import flapkap.vendingmachine.data.entities.Role;
import flapkap.vendingmachine.data.entities.User;
import flapkap.vendingmachine.data.repositories.RoleRepository;
import flapkap.vendingmachine.data.repositories.UserRepository;
import flapkap.vendingmachine.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    User currentUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " not found."));
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole().getName())));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public User register(UserDto userDto) {
        User user = this.userMapper.map(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = this.getRoleById(userDto.getRoleId());
        user.setRole(role);
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        User user = this.getUserById(id);
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.getUserById(id);
        this.userRepository.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() ->
                BusinessException.badRequest("User not found", HttpStatus.BAD_REQUEST.toString()));
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() ->
                BusinessException.badRequest("User not found", HttpStatus.BAD_REQUEST.toString()));
    }

    @Override
    public Page<User> listUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    private Role getRoleById(Long id) {
        return this.roleRepository.findById(id).orElseThrow(() ->
                BusinessException.badRequest("Role not found", HttpStatus.BAD_REQUEST.toString()));
    }
}
