create table noticia (
	id bigint not null auto_increment,
	titulo varchar(255) not null,
	conteudo text not null,
	autor varchar(80) not null,
	imagem varchar(255) not null,
	data_cadastro datetime not null,
	data_atualizacao datetime not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;