package flapkap.vendingmachine.services;

import flapkap.vendingmachine.data.dto.UserDto;
import flapkap.vendingmachine.data.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public User register(UserDto user);

    public User updateUser(Long id, UserDto userDto);

    public void deleteUser(Long id);

    public User getUserById(final Long id);

    public Page<User> listUsers(final Pageable pageable);
}
