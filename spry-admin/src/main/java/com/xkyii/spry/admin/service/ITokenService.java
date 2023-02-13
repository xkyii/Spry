package com.xkyii.spry.admin.service;

import com.xkyii.spry.admin.entity.SysUser;

public interface ITokenService {

    String generateToken(SysUser user);
}
