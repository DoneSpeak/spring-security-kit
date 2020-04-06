-- 选择mysql数据库
use mysql;
-- 创建本地用户
create user 'salak'@'localhost' identified by 'Salacca';
-- 刷新MySQL的系统权限相关表，使添加用户操作生效，以免会出现拒绝访问
flush privileges;

create database security_kit_browser;

-- 赋予部分权限，其中的shopping.*表示对以shopping所有文件操作。
grant select,delete,update,insert on security_kit_browser.* to salak@'localhost' identified by 'Salacca';
flush privileges;
