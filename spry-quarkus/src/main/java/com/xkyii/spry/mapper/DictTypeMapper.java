package com.xkyii.spry.mapper;

import com.xkyii.spry.entity.DictType;
import org.mapstruct.*;

@Mapper(config = MappingConfig.class)
public interface DictTypeMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget DictType target, DictType source);
}
