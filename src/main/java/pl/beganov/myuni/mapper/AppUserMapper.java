package pl.beganov.myuni.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.beganov.myuni.dto.UserResponse;
import pl.beganov.myuni.entity.AppUser;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);

    AppUser responseToAppUser(UserResponse response);
}
