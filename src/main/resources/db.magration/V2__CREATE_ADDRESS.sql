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