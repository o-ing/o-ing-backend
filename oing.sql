# id - root
# pw - 1234

CREATE DATABASE oing CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

drop table oing;

use oing;

select * from member;

UPDATE member
SET member_role = 'ROLE_ADMIN'
WHERE member_id = 2;

UPDATE member
SET member_role = 'ROLE_ADMIN'
WHERE member_email = 'bsybear6233@gmail.com';





select * from club;
delete from club where club_id = 1;

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
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_MIDDLE_ADMIN');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');


