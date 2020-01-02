/*!40101 SET NAMES utf8 */;

-- 权限表
drop table if exists privilege;
create table privilege (
	priName varchar(32) primary key			comment '权限值',
	check(length(priName) > 0)
) charset=utf8;

-- 权限表预设值
insert into privilege values
('管理员'),
('人事员工'),
('普通员工');


-- 职称表
drop table if exists title;
create table title (
	titName nvarchar(32) primary key	comment '职称值',
	check (length(titName) > 0)
) charset=utf8;

-- 职称表预设值
insert into title values
('主任'), 
('组长'), 
('员工'), 
('实习员工');


-- 部门表
drop table if exists department;
create table department (
    depNo char(10) primary key     	comment '部门号，必须大于0个字符且不能为空',
    depName nvarchar(32) not null  	comment '部门名字，必须大于0个字符且不能为空',
	check(length(depNo) > 0),
	check(length(depName) > 0) 
) charset=utf8;

-- 部门表预设值
insert into department values 
('1001','行政部'),
('1002','后勤部'),
('1121','生产部'),
('1122','销售部'),
('1123','售后部'),
('1131','财务部'),
('1132','保安部');


-- 员工表
drop table if exists employee;
create table employee (
	empNo char(10) primary key      	comment '雇员号，必须大于0个字符且不能为空',
	empName nvarchar(32) not null   	comment '雇员名，必须大于0个字符且不能为空',
	empBirthday date not null           comment '雇员生日，不能为空，日期类型',
	empDepNo nvarchar(32) not null     comment '雇员部门，引用部门表的主键，不能为空',
	empTitle nvarchar(32) not null      comment '雇员职称，不能为空',
	empClockingIn int                   comment '雇员单月考勤数累加计数',
	check (length(empNo) > 0),
	check (length(empName) > 0),
	foreign key(empDepNo) references department(depNo),
	foreign key(empTitle) references title(titName)
) charset=utf8;


-- 账户表
drop table if exists account;
create table account (
	accName nvarchar(32) primary key 		comment '账户名，主键，长度必须大于0',
	accHashedPassword varchar(128) not null comment '账户密码的数字摘要，不能为null',
	accEmpNo char(10) unique				comment '账户对应的员工号，外键引用，可以为空，值唯一',
	accPrivilege varchar(32) not null		comment '账户权限',
	check (length(accName) > 0),
	foreign key(accEmpNo) references employee(empNo),
	foreign key(accPrivilege) references privilege(priName)
) charset=utf8;

-- 账户表预设管理员
insert into account values 
('admin', '$2a$10$dP88NB0boitmrLHJfdEoh.GJgnImDyqKSBYNcL5YgzTovwZXdns6S', null, '管理员');


-- 内部通信信息表
drop table if exists message;
create table message (
	senderAccNo char(10) not null   	comment '发送者，引用雇员表主键',
	receiverAccNo char(10) not null 	comment '接受者，引用雇员表主键',
	sendTime datetime not null      	comment '发送日期，时间日期类型',
	message text                        comment '消息内容',
	foreign key(senderAccNo) references employee(empNo),
	foreign key(receiverAccNo) references employee(empNo)
) charset=utf8;