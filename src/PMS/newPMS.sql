

drop table if exists employee;
create table employee (
	empNo char(10) primary key,
	empName nvarchar(32) not null,
	empBirthday datetime not null,
	empClockingIn int comment '���¿������ۼ�', 
	empTitle nvarchar(32) not null,
	empDept nvarchar(32) not null,
	empPassword  varchar(128) not null
) charset=utf8;


drop table if exists administrator;
create table administrator (
	admNo char(10) primary key,
	admName nvarchar(32) not null,
	admPassword varchar(128) not null
) charset=utf8;

insert into administrator values 
('1000', 'admin', '$2a$10$dP88NB0boitmrLHJfdEoh.GJgnImDyqKSBYNcL5YgzTovwZXdns6S');


drop table if exists message;
create table message (
	senderEmpNo char(10),
	receiverEmpNo char(10),
	sendTime datetime not null,
	msg text,
	foreign key(senderEmpNo) references employee(empNo),
	foreign key(receiverEmpNo) references employee(empNo)
) charset=utf8;



drop table if exists department;
create table department (
	deptNo char(10) primary key,
	deptName nvarchar(32) not null
) charset=utf8;

INSERT INTO `department` VALUES ('1001','������'),('1002','���ڲ�'),('1121','������'),('1122','���۲�'),('1123','�ۺ�'),('1131','����'),('1132','������');
