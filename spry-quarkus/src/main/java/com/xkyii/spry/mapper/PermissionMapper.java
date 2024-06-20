package com.xkyii.spry.mapper;

import com.xkyii.spry.entity.Permission;
import org.mapstruct.*;

@Mapper(config = MappingConfig.class)
public interface PermissionMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Permission target, Permission source);
}
