package flapkap.vendingmachine.api.mapper;

import flapkap.vendingmachine.api.v1.resources.UserResource;
import flapkap.vendingmachine.api.v1.resources.UserResponseResource;
import flapkap.vendingmachine.api.v1.resources.UserUpdateResource;
import flapkap.vendingmachine.data.dto.UserDto;
import flapkap.vendingmachine.data.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

   public abstract UserDto map(UserResource userResource);

   @Mapping(target = "password", source = "userDto", qualifiedByName = "encryptPassword")
   public abstract User map(UserDto userDto);

    @Named("encryptPassword")
    protected String encryptPassword(final UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        return userDto.getPassword();
    }

   public abstract UserResponseResource map(User user);

   public abstract List<UserResponseResource> map(List<User> user);

   public abstract UserDto map(UserUpdateResource userUpdateResource);
}
