package com.xkyii.spry.mapper;

import com.xkyii.spry.dto.dept.DeptDto;
import com.xkyii.spry.entity.Dept;
import org.mapstruct.*;

@Mapper(config = MappingConfig.class)
public interface DeptMapper {

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Dept target, Dept source);

    DeptDto toDto(Dept dept);
}
