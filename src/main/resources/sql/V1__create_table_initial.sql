CREATE TABLE tb_people(
	id int(4) AUTO_INCREMENT,
	document varchar(11),
    name varchar(50),
    phone varchar(11),
    birthdate date,
    address_id int(4),
    primary key(id)
);

CREATE TABLE tb_address(
	id int(4) AUTO_INCREMENT,
	street varchar(250),
    complement varchar(50),
    district varchar(50),
    city varchar(50),
    state varchar(50),
    zip_code varchar(8),
    number varchar(8),
    primary key(id)
);