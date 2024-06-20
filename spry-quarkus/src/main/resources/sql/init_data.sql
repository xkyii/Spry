-- ----------------------------
-- 1. User
-- ----------------------------
ALTER TABLE t_user AUTO_INCREMENT=10000;
INSERT INTO t_user (id, username, nickname, email, password, created_at, status)
    VALUE (1, 'admin', 'admin', 'admin@xkyii.com', 'admin123', sysdate(), 0);
INSERT INTO t_user (id, username, nickname, email, password, created_at, status)
    VALUE (2, 'xkyii', 'xkyii', '0@xkyii.com', 'xk123', sysdate(), 0);
INSERT INTO t_user (id, username, nickname, email, password, created_at, status)
    VALUE (3, 'demo', 'demo', 'demo@xkyii.com', 'demo123', sysdate(), 0);


-- ----------------------------
-- 2. Dept
-- ----------------------------
ALTER TABLE t_dept AUTO_INCREMENT=10000;
INSERT INTO t_dept (id, parent_id, ancestors, name, code, created_at, status)
    VALUE (1, 0, '0', 'xky00', 'xky00', sysdate(), 0);
INSERT INTO t_dept (id, parent_id, ancestors, name, code, created_at, status)
    VALUE (2, 1, '0/1', 'xky01', 'xky01', sysdate(), 0);


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
INSERT INTO t_user_role (user_id, role_id) VALUE (1, 1); -- admin - admin
INSERT INTO t_user_role (user_id, role_id) VALUE (1, 2); -- admin - tester
INSERT INTO t_user_role (user_id, role_id) VALUE (2, 1); -- xkyii - admin
INSERT INTO t_user_role (user_id, role_id) VALUE (2, 2); -- xkyii - tester
