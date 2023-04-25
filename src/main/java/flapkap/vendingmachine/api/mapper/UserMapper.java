package flapkap.vendingmachine.api.mapper;

import flapkap.vendingmachine.api.v1.resources.UserAuthResponseResource;
import flapkap.vendingmachine.api.v1.resources.UserResource;
import flapkap.vendingmachine.api.v1.resources.UserResponseResource;
import flapkap.vendingmachine.api.v1.resources.UserUpdateResource;
import flapkap.vendingmachine.data.dto.AuthTokenDto;
import flapkap.vendingmachine.data.dto.UserDto;
import flapkap.vendingmachine.data.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(UserResource userResource);

    User map(UserDto userDto);
    UserResponseResource map(User user);

    List<UserResponseResource> map(List<User> user);

    UserDto map(UserUpdateResource userUpdateResource);

    UserAuthResponseResource map(AuthTokenDto authTokenDto);
}
