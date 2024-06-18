package com.xkyii.spry.mapper;

import com.xkyii.spry.entity.User;
import org.mapstruct.*;

@Mapper(config = MappingConfig.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget User target, User source);
}
