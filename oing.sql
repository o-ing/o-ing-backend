# id - root
# pw - 1234

CREATE DATABASE oing CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

drop table oing;

use oing;

select * from member;
select * from club;

show tables;

drop table member;
drop table board;
drop table crew;
drop table crew_member;
drop table member;
drop table authority;
drop table member_authority;
drop table notice;

select * from authority;
select * from member_authority;

INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');


