package flapkap.vendingmachine.api.v1;

import flapkap.vendingmachine.api.mapper.UserMapper;
import flapkap.vendingmachine.api.v1.resources.*;
import flapkap.vendingmachine.data.dto.AuthTokenDto;
import flapkap.vendingmachine.data.dto.UserDto;
import flapkap.vendingmachine.exceptions.BusinessException;
import flapkap.vendingmachine.security.JwtUtils;
import flapkap.vendingmachine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController extends AbstractController implements UsersApi {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtService;

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
            throw BusinessException.badRequest(error.getMessage(), null);
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
    public ResponseEntity<UserAuthResponseResource> userAuthenticate(UserAuthResource userAuthResource) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthResource.getUsername(), userAuthResource.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(userAuthResource.getUsername());
            return ResponseEntity.ok().body(this.userMapper.map(new AuthTokenDto(token)));
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
