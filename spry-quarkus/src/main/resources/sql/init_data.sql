
-- User
INSERT INTO t_user (id, username, nickname, email, password, created_at, status)
             VALUE (1, 'admin', 'admin', 'admin@xkyii.com', 'admin123', sysdate(), 0);
INSERT INTO t_user (id, username, nickname, email, password, created_at, status)
             VALUE (2, 'xkyii', 'xkyii', '0@xkyii.com', 'xk123', sysdate(), 0);
INSERT INTO t_user (id, username, nickname, email, password, created_at, status)
             VALUE (3, 'demo', 'demo', 'demo@xkyii.com', 'demo123', sysdate(), 0);

-- Dept
INSERT INTO t_dept (id, parent_id, ancestors, name, code, created_at, status)
             VALUE (1, 0, '0', 'xky00', 'xky00', sysdate(), 0);
INSERT INTO t_dept (id, parent_id, ancestors, name, code, created_at, status)
             VALUE (2, 1, '0/1', 'xky01', 'xky01', sysdate(), 0);


-- Role
INSERT INTO t_role (id, name, code, created_at, status)
             VALUE (1, 'admin', 'admin', sysdate(), 0);
INSERT INTO t_role (id, name, code, created_at, status)
             VALUE (2, 'xkyii', 'xkyii', sysdate(), 0);