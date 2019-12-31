
--drop table if exists title;
--create table title (
--    titName nvarchar(32) primary key,
--) charset=utf8;

drop table if exists employee;
create table employee (
	empNo char(10) check(length(empNo) > 0) primary key     comment '��Ա�ţ��������0���ַ��Ҳ���Ϊ��',
	empName nvarchar(32) check(length(empNo) > 0) not null  comment '��Ա�����������0���ַ��Ҳ���Ϊ��',
	empBirthday datetime not null                           comment '��Ա���գ�����Ϊ��',
	empClockingIn int                                       comment '��Ա���¿������ۼӼ���',
	empTitle nvarchar(32) not null                          comment '��Աְ�ƣ�����Ϊ��',
	empDeptNo nvarchar(32) not null                         comment '��Ա���ţ����ò��ű������������Ϊ��',
	empHashedPassword varchar(128) not null                 comment '��Ա���������ժҪ������Ϊ��',
	foreign key(empDeptNo) references department(deptNo),
) charset=utf8;

drop table if exists administrator;
create table administrator (
	admNo char(10) check(length(empNo) > 0) primary key     commnet '����ţ��������0���ַ��Ҳ���Ϊ��'��
	admName nvarchar(32) check(length(empNo) > 0) not null  comment '����Ա�����������0���ַ��Ҳ���Ϊ��',
	admHashedPassword varchar(128) not null                 comment '����Ա���������ժҪ������Ϊ��',
) charset=utf8;

insert into administrator values 
('1000', 'admin', '$2a$10$dP88NB0boitmrLHJfdEoh.GJgnImDyqKSBYNcL5YgzTovwZXdns6S');


drop table if exists message;
create table message (
	senderEmpNo char(10) not null   comment '�����ߣ����ù�Ա������',
	receiverEmpNo char(10) not null comment '�����ߣ����ù�Ա������',
	sendTime datetime not null      comment '��������',
	msg text                        comment '��Ϣ����',
	foreign key(senderEmpNo) references employee(empNo),
	foreign key(receiverEmpNo) references employee(empNo)
) charset=utf8;

drop table if exists department;
create table department (
    deptNo char(10) check(length(empNo) > 0) primary key     comment '���źţ��������0���ַ��Ҳ���Ϊ��',
    deptName nvarchar(32) check(length(empNo) > 0) not null  comment '�������֣��������0���ַ��Ҳ���Ϊ��',
) charset=utf8;

INSERT INTO department VALUES ('1001','������'),('1002','���ڲ�'),('1121','������'),('1122','���۲�'),('1123','�ۺ�'),('1131','����'),('1132','������');
