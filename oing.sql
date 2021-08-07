# id - root
# pw - 1234

CREATE DATABASE oing CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

use oing;

show tables;

drop table member;

create table member (
	member_id int not null,
    email varchar(20) not null,
    password varchar(20) not null,
    nickname varchar(20) not null,
    phonenumber varchar(20) not null,
    role varchar(20) not null,
    timestamp date,
    primary key (member_id)
);

create table crew (
	crew_id int not null,
    name varchar(20) not null,
    content varchar(20) not null,
    timestamp date,
    primary key (crew_id)
);

create table crew_member (
	crew_member_id int not null,
    member_id int not null,
    crew_id int not null,
    authority int not null,
    timestamp date,
    foreign key(member_id) references member (member_id),
    foreign key(crew_id) references crew (crew_id),
    primary key(crew_member_id)
);

create table board (
	board_id int not null,
    board_title varchar(20) not null,
    board_content varchar(20) not null,
    crew_id int not null,
    crew_member_id int not null,
    timestamp date,
    foreign key(crew_id) references crew (crew_id),
    foreign key(crew_member_id) references crew_member (crew_member_id),
    primary key(board_id)
);

create table notice (
	notice_id int not null,
    notice_title varchar(20) not null,
    notice_content varchar(20) not null,
    crew_id int not null,
    crew_member_id int not null,
    timestamp date,
    foreign key(crew_id) references crew (crew_id),
    foreign key(crew_member_id) references crew_member (crew_member_id),
    primary key(notice_id)
);



