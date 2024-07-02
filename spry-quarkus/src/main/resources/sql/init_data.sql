-- ----------------------------
-- 1. User
-- ----------------------------
ALTER TABLE t_user AUTO_INCREMENT=10000;
INSERT INTO t_user (id, username, nickname, email, password, dept_id, created_at, status)
    VALUE (1, 'admin', 'admin', 'admin@xkyii.com', 'admin123', 1, sysdate(), 0);
INSERT INTO t_user (id, username, nickname, email, password, dept_id, created_at, status)
    VALUE (2, 'xkyii', 'xkyii', '0@xkyii.com', 'xk123', 1, sysdate(), 0);
INSERT INTO t_user (id, username, nickname, email, password, dept_id, created_at, status)
    VALUE (3, 'demo', 'demo', 'demo@xkyii.com', 'demo123', 2, sysdate(), 0);


-- ----------------------------
-- 2. Dept
-- ----------------------------
ALTER TABLE t_dept AUTO_INCREMENT=10000;
INSERT INTO t_dept (id, parent_id, ancestors, name, code, created_at, status)
    VALUE (1, 0, '/', 'xky00', 'xky00', sysdate(), 0);
INSERT INTO t_dept (id, parent_id, ancestors, name, code, created_at, status)
    VALUE (2, 1, '/1/', 'xky01', 'xky01', sysdate(), 0);


-- ----------------------------
-- 3. Role
-- ----------------------------
ALTER TABLE t_role AUTO_INCREMENT=10000;
INSERT INTO t_role (id, name, code, created_at, status)
    VALUE (1, 'admin', 'admin', sysdate(), 0);
INSERT INTO t_role (id, name, code, created_at, status)
    VALUE (2, 'tester', 'tester', sysdate(), 0);

-- ----------------------------
-- 4. Permission
-- ----------------------------
ALTER TABLE t_permission AUTO_INCREMENT=10000;
INSERT INTO t_permission (id, name, code, created_at, status)
    VALUE (1, 'admin', 'admin', sysdate(), 0);
INSERT INTO t_permission (id, name, code, created_at, status)
    VALUE (2, 'xkyii', 'xkyii', sysdate(), 0);


-- ----------------------------
-- 5. UserRole
-- ----------------------------
INSERT INTO t_user_role (user_id, role_id, remark)
    VALUE (1, 1, 'admin - admin');
INSERT INTO t_user_role (user_id, role_id, remark)
    VALUE (1, 2, 'admin - tester');
INSERT INTO t_user_role (user_id, role_id, remark)
    VALUE (2, 1, 'xkyii - admin');
INSERT INTO t_user_role (user_id, role_id, remark)
    VALUE (2, 2, 'xkyii - tester');


-- ----------------------------
-- 6. DictType
-- ----------------------------
INSERT INTO t_dict_type (id, name, code, created_at, status, remark)
    VALUE (1, '用户性别', 'sys_user_sex', sysdate(), 0, '用户性别列表');

-- ----------------------------
-- 7. DictData
-- ----------------------------
INSERT INTO t_dict_data (id, name, code, type, created_at, status, remark)
    VALUE (1, '男', '1', 'sys_user_sex', sysdate(), 0, '性别-男');
INSERT INTO t_dict_data (id, name, code, type, created_at, status, remark)
    VALUE (2, '女', '2', 'sys_user_sex', sysdate(), 0, '性别-女');
INSERT INTO t_dict_data (id, name, code, type, created_at, status, remark)
    VALUE (3, '未知', '3', 'sys_user_sex', sysdate(), 0, '性别-未知');