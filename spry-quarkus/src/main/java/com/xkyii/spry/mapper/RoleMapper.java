package com.xkyii.spry.mapper;

import com.xkyii.spry.entity.Role;
import org.mapstruct.*;

@Mapper(config = MappingConfig.class)
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Role target, Role source);
}
