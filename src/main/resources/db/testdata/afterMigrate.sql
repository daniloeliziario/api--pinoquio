delete from usuario;
delete from noticia;

set foreign_key_checks = 1;

alter table usuario auto_increment = 1;
alter table noticia auto_increment = 1;

insert into usuario (id, nome, email, senha, data_cadastro, ativo) values
(1, 'Danilo', 'danilo@pinoquio.com', '123', utc_timestamp, true),
(2, 'Daniel', 'daniel@pinoquio.com', '456', utc_timestamp, true);

insert into noticia (id, titulo, conteudo, autor, imagem, data_cadastro, data_atualizacao) values
(1, 'Danilo é muito bonito', 'Era uma vez um lugarzinho', 'Danilo Eliziario', 'https://imagem-danilo', utc_timestamp, utc_timestamp),
(2, 'Daniel é meu amigo', 'Era uma vez um lugarzinho', 'Daniel Eliziario', 'https://imagem-daniel', utc_timestamp, utc_timestamp);