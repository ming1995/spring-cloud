/*创建表的sql*/
drop table if exists users;
create table users(
	id  int   primary key,
	username varchar(40),
	name varchar(20),
	age  int(3),
	balance  DECIMAL(10,2)
);