package com.xkyii.spry.admin.dto.user.get_user_info;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.repository.SysDeptRepository;
import com.xkyii.spry.admin.repository.SysRoleRepository;
import com.xkyii.spry.admin.repository.SysUserRepository;
import io.quarkus.runtime.util.StringUtil;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class Converter {

    @Inject
    SysDeptRepository deptRepository;

    @Inject
    SysUserRepository userRepository;

    @Inject
    SysRoleRepository roleRepository;

    public @Valid Uni<UserDto> convertUser(Uni<SysUser> userUni) {
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

    public @Valid Uni<RoleDto> convertRole(Uni<SysUser> userUni) {
        return userUni.flatMap(user -> roleRepository.getRolesByUserId(user.getUserId()).map(role -> {
            RoleDto dto = new RoleDto();
            dto.setRoleId(role.getRoleId());
            dto.setRoleName(role.getRoleName());
//            dto.setDataScope

            if(!StrUtil.isNotEmpty(role.getDeptIdSet())) {
                Set<Long> deptIdSet = StrUtil.split(role.getDeptIdSet(), ",").stream()
                    .map(Convert::toLong).collect(Collectors.toSet());
                dto.setDeptIdSet(deptIdSet);
            }

//            dto.setRoleKey("");
//            dto.setMenuPermissions();
//            dto.setMenuIds();

            return dto;
        }));
    }
}
