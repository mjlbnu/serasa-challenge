CREATE TABLE EMPRESA(
	id bigint generated by default as identity,
	nome varchar(100) unique not null,
	pontuacao float not null,
	primary key (id)
);

CREATE TABLE NOTAS(
	id bigint generated by default as identity,
	periodo int not null,
	numero_nota int not null,
	tp_nota varchar(20) not null,
	id_empresa bigint not null,
	status varchar(20) not null,
	primary key (id)
);

ALTER TABLE NOTAS ADD CONSTRAINT FK1 FOREIGN KEY (id_empresa) REFERENCES EMPRESA;

INSERT INTO EMPRESA (nome, pontuacao) VALUES ('Empresa 1', 50.0);
INSERT INTO EMPRESA (nome, pontuacao) VALUES ('Empresa 2', 50.0);
INSERT INTO EMPRESA (nome, pontuacao) VALUES ('Empresa 3', 50.0);

INSERT INTO NOTAS(periodo, numero_nota, tp_nota, id_empresa, status) VALUES(202001, 123, 'FISCAL', 1, 'PENDENTE');
INSERT INTO NOTAS(periodo, numero_nota, tp_nota, id_empresa, status) VALUES(202001, 456, 'FISCAL', 1, 'PENDENTE');
INSERT INTO NOTAS(periodo, numero_nota, tp_nota, id_empresa, status) VALUES(202001, 789, 'FISCAL', 1, 'PENDENTE');
INSERT INTO NOTAS(periodo, numero_nota, tp_nota, id_empresa, status) VALUES(202001, 621, 'DEBITO', 2, 'PENDENTE');
INSERT INTO NOTAS(periodo, numero_nota, tp_nota, id_empresa, status) VALUES(202001, 634, 'DEBITO', 2, 'PENDENTE');
INSERT INTO NOTAS(periodo, numero_nota, tp_nota, id_empresa, status) VALUES(202001, 665, 'DEBITO', 2, 'PENDENTE');
INSERT INTO NOTAS(periodo, numero_nota, tp_nota, id_empresa, status) VALUES(202001, 999, 'FISCAL', 2, 'PENDENTE');
INSERT INTO NOTAS(periodo, numero_nota, tp_nota, id_empresa, status) VALUES(202001, 888, 'FISCAL', 2, 'PENDENTE');
INSERT INTO NOTAS(periodo, numero_nota, tp_nota, id_empresa, status) VALUES(202001, 777, 'DEBITO', 2, 'PENDENTE');

