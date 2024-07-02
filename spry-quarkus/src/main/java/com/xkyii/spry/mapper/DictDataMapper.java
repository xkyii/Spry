package com.xkyii.spry.mapper;

import com.xkyii.spry.entity.DictData;
import org.mapstruct.*;

@Mapper(config = MappingConfig.class)
public interface DictDataMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget DictData target, DictData source);
}
