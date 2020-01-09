/*!40101 SET NAMES utf8 */;


-- 权限表 --------------------------
drop table if exists privilege;
create table privilege (
	priName varchar(32) primary key     comment '权限值',
	check(length(priName) > 0)
) charset=utf8;

-- 权限表预设值
insert into privilege values
('管理员'),
('人事员工'),
('普通员工');


-- 职称表 --------------------------
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


-- 部门表 --------------------------
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
('1003','人事部'),
('1121','生产部'),
('1122','销售部'),
('1123','售后部'),
('1131','财务部'),
('1132','保安部');


-- 员工表 --------------------------
drop table if exists employee;
create table employee (
	empNo char(32) primary key      	comment '雇员号，必须大于0个字符且不能为空',
	empName nvarchar(32) not null   	comment '雇员名，必须大于0个字符且不能为空',
	empBirthday date not null           comment '雇员生日，不能为空，日期类型',
	empDepNo nvarchar(32)               comment '雇员部门，引用部门表的主键，不能为空',
	empTitle nvarchar(32)               comment '雇员职称，不能为空',
	check (length(empNo) > 0),
	check (length(empName) > 0),
	foreign key(empDepNo) references department(depNo) on delete set null,
	foreign key(empTitle) references title(titName) on delete set null
) charset=utf8;

-- 测试账号
insert into employee values
('awoawmdwdjasasawa@126.com', '李普通', '1988-1-1', '1131', '员工'),
('test1002@qq.com', '王普通', '1982-1-1', '1123', '员工'),
('test1003@qq.com', '高普通', '1982-1-1', '1121', '组长'),
('test1004@qq.com', '张人事', '1973-1-1', '1003', '主任'),
('test1005@qq.com', '林人事', '1971-1-1', '1003', '主任');


-- 账户表 --------------------------
drop table if exists account;
create table account (
	accName nvarchar(32) primary key 		comment '账户名，主键，长度必须大于0',
	accHashedPassword varchar(128) not null comment '账户密码的数字摘要，不能为null',
	accEmpNo char(32) unique				comment '账户对应的员工号，外键引用，可以为空，值唯一',
	accPrivilege varchar(32) not null		comment '账户权限',
	check (length(accName) > 0),
	foreign key(accEmpNo) references employee(empNo) on delete cascade on update cascade,
	foreign key(accPrivilege) references privilege(priName)
) charset=utf8;

-- 账户表预设
insert into account values
-- 管理员 密码admin
('admin', '$2a$10$dP88NB0boitmrLHJfdEoh.GJgnImDyqKSBYNcL5YgzTovwZXdns6S', null, '管理员'),
-- 测试账号 密码test
('normalStaff', '$2a$10$z/3aa8Q53R5zrs/ztBIN6eO2z7oAERNFms..OTR5ReqBlR2pxqOru', 'awoawmdwdjasasawa@126.com', '普通员工'),
('normalStaff2', '$2a$10$z/3aa8Q53R5zrs/ztBIN6eO2z7oAERNFms..OTR5ReqBlR2pxqOru', 'test1002@qq.com', '普通员工'),
('normalStaff3', '$2a$10$z/3aa8Q53R5zrs/ztBIN6eO2z7oAERNFms..OTR5ReqBlR2pxqOru', 'test1003@qq.com', '普通员工'),
('personnelStaff', '$2a$10$z/3aa8Q53R5zrs/ztBIN6eO2z7oAERNFms..OTR5ReqBlR2pxqOru', 'test1004@qq.com', '人事员工'),
('personnelStaff1', '$2a$10$z/3aa8Q53R5zrs/ztBIN6eO2z7oAERNFms..OTR5ReqBlR2pxqOru', 'test1005@qq.com', '人事员工');


-- 内部通信信息表 --------------------------
drop table if exists message;
create table message (
    msgNo int not null auto_increment           comment '消息编号，方便定位消息',
	msgSenderAccName nvarchar(32) not null   	comment '发送者，引用雇员表主键',
    msgReceiverAccName nvarchar(32) not null 	comment '接受者，引用雇员表主键',
	msgSendTime datetime not null      	        comment '发送日期，时间日期类型',
	msgMessage text                             comment '消息内容',
	primary key(msgNo, msgSenderAccName, msgReceiverAccName, msgSendTime) comment '一秒内可以发送多条消息',
	foreign key(msgSenderAccName) references account(accName),
	foreign key(msgSenderAccName) references account(accName)
) charset=utf8;

-- 测试数据
insert message values
(null, 'normalStaff', 'personnelStaff', '2020-1-4 00:00:00', '测试消息1001->1003 A'),
(null, 'normalStaff', 'personnelStaff', '2020-1-4 00:00:01', '测试消息1001->1003 B'),
(null, 'normalStaff2', 'personnelStaff', '2020-1-4 00:00:01', '测试消息1002->1003 A'),
(null, 'personnelStaff', 'normalStaff', '2020-1-4 00:01:05', '测试消息1003->1001 A'),
(null, 'personnelStaff', 'normalStaff2', '2020-1-4 00:05:01', '测试消息1003->1002 A');



drop table if exists ClockingIn;
create table ClockingIn(
	Cleno nvarchar(32) 	    comment '打卡员工号',
	Cldatetime datetime     comment '打卡时间',
	Clstatus nvarchar(32)   comment '打卡状态',
	primary key(Cleno, Cldatetime),
	foreign key(Cleno) references Employee(empNo) on delete cascade
) charset=utf8;

drop table if exists ClockingInTime;
create table ClockingInTime(
	ClockingInTime TIME     comment '考勤时间'
) charset=utf8;

insert into ClockingInTime values
('08:00:00');