
--drop table if exists title;
--create table title (
--    titName nvarchar(32) primary key,
--) charset=utf8;

drop table if exists employee;
create table employee (
	empNo char(10) check(length(empNo) > 0) primary key     comment '雇员号，必须大于0个字符且不能为空',
	empName nvarchar(32) check(length(empNo) > 0) not null  comment '雇员名，必须大于0个字符且不能为空',
	empBirthday datetime not null                           comment '雇员生日，不能为空',
	empClockingIn int                                       comment '雇员单月考勤数累加计数',
	empTitle nvarchar(32) not null                          comment '雇员职称，不能为空',
	empDeptNo nvarchar(32) not null                         comment '雇员部门，引用部门表的主键，不能为空',
	empHashedPassword varchar(128) not null                 comment '雇员密码的数字摘要，不能为空',
	foreign key(empDeptNo) references department(deptNo),
) charset=utf8;

drop table if exists administrator;
create table administrator (
	admNo char(10) check(length(empNo) > 0) primary key     commnet '管理号，必须大于0个字符且不能为空'，
	admName nvarchar(32) check(length(empNo) > 0) not null  comment '管理员名，必须大于0个字符且不能为空',
	admHashedPassword varchar(128) not null                 comment '管理员密码的数字摘要，不能为空',
) charset=utf8;

insert into administrator values 
('1000', 'admin', '$2a$10$dP88NB0boitmrLHJfdEoh.GJgnImDyqKSBYNcL5YgzTovwZXdns6S');


drop table if exists message;
create table message (
	senderEmpNo char(10) not null   comment '发送者，引用雇员表主键',
	receiverEmpNo char(10) not null comment '接受者，引用雇员表主键',
	sendTime datetime not null      comment '发送日期',
	msg text                        comment '消息内容',
	foreign key(senderEmpNo) references employee(empNo),
	foreign key(receiverEmpNo) references employee(empNo)
) charset=utf8;

drop table if exists department;
create table department (
    deptNo char(10) check(length(empNo) > 0) primary key     comment '部门号，必须大于0个字符且不能为空',
    deptName nvarchar(32) check(length(empNo) > 0) not null  comment '部门名字，必须大于0个字符且不能为空',
) charset=utf8;

INSERT INTO department VALUES ('1001','行政部'),('1002','后勤部'),('1121','生产部'),('1122','销售部'),('1123','售后部'),('1131','财务部'),('1132','保安部');
