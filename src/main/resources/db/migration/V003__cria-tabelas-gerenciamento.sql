create table professor (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	data_cadastro datetime not null,
	data_atualizacao datetime not null,
	
	primary key (id)
	
) engine=InnoDB default charset=utf8;

create table ano (
	id bigint not null auto_increment,
	titulo varchar(80) not null,
	data_cadastro datetime not null,
	data_atualizacao datetime not null,
	
	primary key (id)
	
) engine=InnoDB default charset=utf8;

create table materia (
	id bigint not null auto_increment,
	titulo varchar(80) not null,
	descricao varchar(250) not null,
	ano_id bigint,
	professor_id bigint not null,
	data_cadastro datetime not null,
	data_atualizacao datetime not null,
	
	primary key (id),
	constraint fk_materia_ano foreign key (ano_id) references ano (id),
	constraint fk_materia_professor foreign key (professor_id) references professor (id)
	
) engine=InnoDB default charset=utf8;

create table bimestre (
	id bigint not null auto_increment,
	primeira_nota decimal(3,1),
	segunda_nota decimal(3,1),
	terceira_nota decimal(3,1),
	recuperacao decimal(3,1),
	media decimal(3,1),
	falta smallint(2),
	data_cadastro datetime not null,
	data_atualizacao datetime not null,
	
	primary key (id)
	
) engine=InnoDB default charset=utf8;

create table disciplina (
	id bigint not null auto_increment,
	materia varchar(80) not null,
	primeiro_bimestre_id bigint not null,
	segundo_bimestre_id bigint not null,
	terceiro_bimestre_id bigint not null,
	quarto_bimestre_id bigint not null,
	data_cadastro datetime not null,
	data_atualizacao datetime not null,
	
	primary key (id),
	constraint fk_disciplina_primeiro_bimestre foreign key (primeiro_bimestre_id) references bimestre (id),
	constraint fk_disciplina_segundo_bimestre foreign key (segundo_bimestre_id) references bimestre (id),
	constraint fk_disciplina_terceiro_bimestre foreign key (terceiro_bimestre_id) references bimestre (id),
	constraint fk_disciplina_quarto_bimestre foreign key (quarto_bimestre_id) references bimestre (id)
	
) engine=InnoDB default charset=utf8;

create table aluno (
	matricula bigint not null auto_increment,
	nome varchar(80) not null,
	nome_pai varchar(80) not null,
	nome_mae varchar(80) not null,
	data_nascimento date not null,
	ano_id bigint not null,	
	data_cadastro datetime not null,
	data_atualizacao datetime not null,
	ativo tinyint(1) not null,
	
	primary key (matricula),
	constraint fk_aluno_ano foreign key (ano_id) references ano (id)
	
) engine=InnoDB default charset=utf8;

create table boletim (
	id bigint not null auto_increment,
	aluno bigint not null,
	ano varchar(80) not null,
	data_cadastro datetime not null,
	data_atualizacao datetime not null,
	
	primary key (id)
	
) engine=InnoDB default charset=utf8;

create table boletim_disciplina (
	boletim_id bigint not null,
	disciplina_id bigint not null,
	
	primary key (boletim_id, disciplina_id)
	
) engine=InnoDB default charset=utf8;