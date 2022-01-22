create database demo1;
create database demo2;

create table `demo1`.table1 (
    id int
);

create table `demo2`.table2 (
    id int
);

create table `demo1`.sharding_table (
    id int
);

create table `demo2`.sharding_table (
    id int
);

insert into `demo1`.sharding_table (id) values(1);
insert into `demo2`.sharding_table (id) values(1);