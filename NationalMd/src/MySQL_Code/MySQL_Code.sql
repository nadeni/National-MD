create database game ;
use game;
create table user_details(
User_id int not null auto_increment primary key,
User_name varchar(30),
User_password varchar(30),
User_phone varchar(30),
User_Email varchar(50)
)engine = memory auto_increment=1;

insert into user_details values(null,'root','123456','13453537634','1050289148@qq.com');

DELIMITER //
CREATE PROCEDURE createtable(IN tname varchar(20))
BEGIN
SET @s = CONCAT('CREATE TABLE ', tname, '(UID int not null auto_increment primary key, Utime Datetime , Ulevel int)engine = memory auto_increment=1;');
PREPARE stm FROM @s;
EXECUTE stm;
END //
DELIMITER ;

call createtable('root');
insert into root values (null,'2019-5-16 6:20:00',1);
insert into root values (null,'2019-5-16 6:20:00',2);
insert into root values (null,'2019-5-16 6:20:00',3);
insert into root values (null,'2019-5-16 6:20:00',4);
insert into root values (null,'2019-5-16 6:20:00',5);
insert into root values (null,'2019-5-16 6:20:00',6);
insert into root values (null,'2019-5-16 6:20:00',7);
insert into root values (null,'2019-5-16 6:20:00',8);
insert into root values (null,'2019-5-16 6:20:00',9);
insert into root values (null,'2019-5-16 6:20:00',10);
insert into root values (null,'2019-5-16 6:20:00',11);
insert into root values (null,'2019-5-16 6:20:00',12);
insert into root values (null,'2019-5-16 6:20:00',13);