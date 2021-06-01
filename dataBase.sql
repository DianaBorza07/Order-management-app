###### Create database #####
drop database if exists warehouse;
create database warehouse;

##### Create tables #####
use warehouse;

drop table if exists `client`;
create table `client`(
id int not null primary key auto_increment,
clientName varchar(30),
emailAddress varchar(60),
gender varchar(10),
phoneNumber varchar(12)
);

drop table if exists product;
create table product(
id int not null primary key auto_increment,
productName varchar(50),
price int,
stock int,
weight float,
returnable boolean
);

drop table if exists `order`;
create table`order`(
id int not null primary key auto_increment,
productID int,
productPrice int,
productQuantity int,
clientID int,
totalPrice int
);

##### create relationships between tables #####
alter table `order` 
add foreign key (productID) references product(id);

alter table `order` 
add foreign key (clientID) references `client`(id);

###### insert data into tables #####
insert into `client`(clientName,emailAddress,gender,phoneNumber)
values ('Silvia M','silvia@gmail,com','Female','0745528101'),
('Andrei S','andrei@gmail.com','Male','0746783425'),
('Mihai V','mihai@yahoo.com','Male','0264352434');

insert into `product`(productName,price,stock,weight,returnable)
values ('Peach',20,6,0.7,false),
('Frozen Peas',15,2,0.4,false),
('Chips',4,3,1,true),
('Meat',22,2,1.2,true),
('Water bottle',2,4,0.5,true),
('Pepsi twist',3,3,0.5,false),
('Strawberries',30,5,1.7,false),
('Bread',10,6,0.8,false),
('Chedar cheese',25,10,0.2,true);

insert into `order`(productID,productPrice,productQuantity,clientID,totalPrice)
values (2,15,1,1,15),
(9,25,5,2,125),
(7,30,2,3,60);






