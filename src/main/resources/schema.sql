create table BOOK (
     id number not null,
     title varchar2(150) not null,
     author varchar2(150) not null,
     description varchar2(150),
     constraint book_pk primary key(id)
);