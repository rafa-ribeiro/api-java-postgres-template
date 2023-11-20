package com.java.meli.user.infrastructure.database.mapper;

import com.java.meli.core.mapper.JsonNullableMapper;
import com.java.meli.user.application.models.UserDTO;
import com.java.meli.user.domain.User;
import com.java.meli.user.infrastructure.database.UserEntity;
import org.mapstruct.*;


@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity map(UserDTO userDTO);

    UserDTO map(UserEntity userEntity);

    @InheritConfiguration
    void update(UserDTO source, @MappingTarget UserEntity destination);

}
