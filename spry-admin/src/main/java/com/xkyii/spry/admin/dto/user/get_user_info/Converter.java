package com.xkyii.spry.admin.dto.user.get_user_info;

import cn.hutool.core.bean.BeanUtil;
import com.xkyii.spry.admin.entity.SysDept;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.repository.SysDeptRepository;
import com.xkyii.spry.admin.repository.SysUserRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class Converter {

    @Inject
    SysDeptRepository deptRepository;

    @Inject
    SysUserRepository userRepository;

    public @Valid Uni<UserDto> convert(Uni<SysUser> userUni) {
        return Uni.createFrom().item(new UserDto())
            // 复制属性
            .chain(dto -> userUni.map(u -> {
                BeanUtil.copyProperties(u, dto);
                return dto;
            }))
            // 部门信息
            .chain(dto -> {
                if (dto.getDeptId() == null) {
                    return Uni.createFrom().item(dto);
                }
                return deptRepository.get(dto.getDeptId())
                    .onItem().transform(dept -> {
                        if (dept != null) {
                            dto.setDeptName(dept.getDeptName());
                            dto.setDeptId(dept.getDeptId());
                        }
                        return dto;
                    });
            })
            // 创建人信息
            .chain(dto -> {
                if (dto.getCreatorId() == null) {
                    return Uni.createFrom().item(dto);
                }
                return userRepository.get(dto.getCreatorId())
                    .onItem().ifNotNull().transform(creator -> {
                        dto.setCreatorName(creator.getUsername());
                        dto.setCreatorId(creator.getUserId());
                        return dto;
                    });
            })
            ;
    }
}
