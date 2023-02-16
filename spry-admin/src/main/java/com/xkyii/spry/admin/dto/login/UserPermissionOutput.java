package com.xkyii.spry.admin.dto.login;

import com.xkyii.spry.admin.dto.DictionaryData;
import com.xkyii.spry.admin.dto.UserDto;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Schema(description = "用户信息出参")
public class UserPermissionOutput {

    @Schema(title="角色名")
    private String roleKey;

    @Schema(title="用户信息")
    private UserDto user;

    @Schema(title="权限列表")
    private Set<String> permissions;

    @Schema(title="字典列表")
    private Map<String, List<DictionaryData>> dictTypes;

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Map<String, List<DictionaryData>> getDictTypes() {
        return dictTypes;
    }

    public void setDictTypes(Map<String, List<DictionaryData>> dictTypes) {
        this.dictTypes = dictTypes;
    }
}
