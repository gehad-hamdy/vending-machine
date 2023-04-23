package flapkap.vendingmachine.api.v1;

import flapkap.vendingmachine.api.mapper.UserMapper;
import flapkap.vendingmachine.api.v1.resources.UserAuthResponseResource;
import flapkap.vendingmachine.api.v1.resources.UserResource;
import flapkap.vendingmachine.api.v1.resources.UserResponseResource;
import flapkap.vendingmachine.api.v1.resources.UserUpdateResource;
import flapkap.vendingmachine.data.dto.UserDto;
import flapkap.vendingmachine.exceptions.BusinessException;
import flapkap.vendingmachine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController extends AbstractController implements UsersApi {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseEntity<UserResponseResource> userRegister(UserResource userResource) {
        try {
            UserDto user = this.userMapper.map(userResource);
            return ResponseEntity.ok().body(this.userMapper.map(this.userService.register(user)));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        } catch (final InvalidDataAccessApiUsageException |
                       DataIntegrityViolationException exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<UserResponseResource> getUserById(Long id) {
        try {
            return ResponseEntity.ok().body(this.userMapper.map(this.userService.getUserById(id)));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        }
    }

    @Override
    public ResponseEntity<UserResponseResource> updateUser(Long id, UserUpdateResource userUpdateResource) {
        try {
            var userDto = this.userMapper.map(userUpdateResource);
            var user = this.userService.updateUser(id, userDto);
            return ResponseEntity.ok().body(this.userMapper.map(user));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        } catch (final Exception exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        try {
            this.userService.deleteUser(id);
            return UsersApi.super.deleteUser(id);
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        } catch (final Exception exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<UserResponseResource>> listUsers(Integer offset, Integer limit) {
        try {
            var page = this.userService.listUsers(pageOf(offset, limit));
            return ResponseEntity.ok()
                    .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                    .body(this.userMapper.map(page.getContent()));
        } catch (final BusinessException error) {
            throw BusinessException.badRequest(error.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        } catch (final Exception exception) {
            throw BusinessException.of(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<UserAuthResponseResource> userAuthenticate(UserUpdateResource userUpdateResource) {
        return UsersApi.super.userAuthenticate(userUpdateResource);
    }
}
