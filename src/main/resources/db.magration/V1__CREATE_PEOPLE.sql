CREATE TABLE tb_people(
	id int(4) AUTO_INCREMENT,
	document varchar(11),
    name varchar(50),
    phone varchar(11),
    birthdate date,
    address_id int(4),
    primary key(id)
);