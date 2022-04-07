set foreign_key_checks = 0;

delete from usuario;
delete from noticia;
delete from materia;
delete from professor;
delete from disciplina;
delete from boletim;
delete from boletim_disciplina;
delete from bimestre;
delete from aluno;
delete from ano;

set foreign_key_checks = 1;

alter table usuario auto_increment = 1;
alter table noticia auto_increment = 1;
alter table materia auto_increment = 1;
alter table professor auto_increment = 1;
alter table disciplina auto_increment = 1;
alter table boletim auto_increment = 1;
alter table boletim_disciplina auto_increment = 1;
alter table bimestre auto_increment = 1;
alter table aluno auto_increment = 1;
alter table ano auto_increment = 1;

insert into usuario (id, nome, email, senha, data_cadastro, ativo) values
(1, 'Danilo', 'danilo@pinoquio.com', '123', utc_timestamp, true),
(2, 'Daniel', 'daniel@pinoquio.com', '456', utc_timestamp, true);

insert into noticia (id, titulo, conteudo, autor, imagem, data_cadastro, data_atualizacao) values
(1, 'Danilo é muito bonito', 'Era uma vez um lugarzinho', 'Danilo Eliziario', 'https://imagem-danilo', utc_timestamp, utc_timestamp),
(2, 'Daniel é meu amigo', 'Era uma vez um lugarzinho', 'Daniel Eliziario', 'https://imagem-daniel', utc_timestamp, utc_timestamp);

insert into professor (id, nome, data_cadastro, data_atualizacao) values
(1, 'João', utc_timestamp, utc_timestamp),
(2, 'Maria', utc_timestamp, utc_timestamp),
(3, 'José', utc_timestamp, utc_timestamp);

insert into ano (id, titulo, data_cadastro, data_atualizacao) values
(1, '1º Ano', utc_timestamp, utc_timestamp),
(2, '2º Ano', utc_timestamp, utc_timestamp);

insert into materia (id, titulo, descricao, ano_id, professor_id, data_cadastro, data_atualizacao) values
(1, 'Matemática', 'Matemática do 1º Ano', 1, 1, utc_timestamp, utc_timestamp),
(2, 'Geografia', 'Geografia do 1º Ano', 1, 2, utc_timestamp, utc_timestamp),
(3, 'Português', 'Português do 1º Ano', 1, 3, utc_timestamp, utc_timestamp),
(4, 'Matemática', 'Matemática do 2º Ano', 2, 1, utc_timestamp, utc_timestamp),
(5, 'Geografia', 'Geografia do 2º Ano', 2, 2, utc_timestamp, utc_timestamp),
(6, 'Português', 'Português do 2º Ano', 2, 3, utc_timestamp, utc_timestamp);

insert into aluno (matricula, nome, nome_pai, nome_mae, data_nascimento, ano_id, data_cadastro, data_atualizacao, ativo) values
(1, 'Danilo', 'Arinaldo', 'Desterro', utc_timestamp, 1, utc_timestamp, utc_timestamp, true),
(2, 'Daniel', 'Arinaldo', 'Desterro', utc_timestamp, 1, utc_timestamp, utc_timestamp, true),
(3, 'Camila', 'Arinaldo', 'Desterro', utc_timestamp, 2, utc_timestamp, utc_timestamp, true),
(4, 'André', 'Arinaldo', 'Desterro', utc_timestamp, 2, utc_timestamp, utc_timestamp, true);





