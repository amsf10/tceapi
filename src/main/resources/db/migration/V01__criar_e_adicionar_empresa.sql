CREATE TABLE empresa (
  id bigint(20) PRIMARY KEY AUTO_INCREMENT,
  cnpj varchar(20) NOT NULL,
  razao_social varchar(60) NOT NULL,
  sigla varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO empresa (cnpj, razao_social, sigla) values ('05.818.935/0001-01', 'Tribunal de Contas do Estado do Piaui', 'TCEPI');
INSERT INTO empresa (cnpj, razao_social, sigla) values ('00.414.607/0001-18', 'Tribunal de Contas da União', 'TCU');