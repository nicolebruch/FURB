CREATE DATABASE gestao_contas;
USE gestao_contas;

-- 1) Criação das tabelas com as restrições PK e FK.

CREATE TABLE ramo_atuacao(
cd_ramo INT PRIMARY KEY,
ds_ramo VARCHAR(50)
);

CREATE TABLE fornecedor(
cd_fornecedor INT PRIMARY KEY,
nm_fornecedor VARCHAR(50),
ds_email VARCHAR(50),
ds_website VARCHAR(50),
cd_ramo INT,
FOREIGN KEY(cd_ramo) REFERENCES ramo_atuacao(cd_ramo)
);

CREATE TABLE nota_fiscal_entrada(
nr_nf_entrada INT PRIMARY KEY,
dt_emissao DATE,
vl_total DECIMAL(8,2),
cd_fornecedor INT,
FOREIGN KEY(cd_fornecedor) REFERENCES fornecedor(cd_fornecedor)
);

CREATE TABLE titulo_pagar(
nr_titulo INT PRIMARY KEY,
dt_emissao DATE,
dt_vencimento DATE,
dt_pagamento DATE,
vl_titulo DECIMAL(8,2),
vl_multa DECIMAL(8,2),
nr_nf_entrada INT,
cd_fornecedor INT,
FOREIGN KEY(nr_nf_entrada) REFERENCES nota_fiscal_entrada(nr_nf_entrada),
FOREIGN KEY(cd_fornecedor) REFERENCES fornecedor(cd_fornecedor)
);

-- 2) Inclusão de registros para no mínimo 05 fornecedores ligados em 03 diferentes ramos de atuação.
-- Ainda a inclusão de 05 notas fiscais com 10 títulos a pagar em diferentes períodos.

INSERT INTO ramo_atuacao VALUES (1,'Primeiro ramo de atuação');
INSERT INTO ramo_atuacao VALUES (2,'Segundo ramo de atuação');
INSERT INTO ramo_atuacao VALUES (3,'Terceiro ramo de atuação');

INSERT INTO fornecedor VALUES (1,'Fornecedor 01','fornecedor01@gmail.com','fornecedor01.com.br',1);
INSERT INTO fornecedor VALUES (2,'Fornecedor 02','fornecedor02@gmail.com','fornecedor02.com.br',2);
INSERT INTO fornecedor VALUES (3,'Fornecedor 03','fornecedor03@gmail.com','fornecedor03.com.br',3);
INSERT INTO fornecedor VALUES (4,'Fornecedor 04','fornecedor04@gmail.com','fornecedor04.com.br',1);
INSERT INTO fornecedor VALUES (5,'Fornecedor 05','fornecedor05@gmail.com','fornecedor05.com.br',3);

INSERT INTO nota_fiscal_entrada VALUES (1,'2024-01-01',199.99,1);
INSERT INTO nota_fiscal_entrada VALUES (2,'2024-02-02',299.99,2);
INSERT INTO nota_fiscal_entrada VALUES (3,'2024-03-03',399.99,3);
INSERT INTO nota_fiscal_entrada VALUES (4,'2024-04-04',499.99,4);
INSERT INTO nota_fiscal_entrada VALUES (5,'2024-05-05',599.99,5);

INSERT INTO titulo_pagar VALUES (1,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,1,1);
INSERT INTO titulo_pagar VALUES (2,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,2,2);
INSERT INTO titulo_pagar VALUES (3,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,3,3);
INSERT INTO titulo_pagar VALUES (4,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,4,4);
INSERT INTO titulo_pagar VALUES (5,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,5,5);
INSERT INTO titulo_pagar VALUES (6,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,1,1);
INSERT INTO titulo_pagar VALUES (7,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,2,2);
INSERT INTO titulo_pagar VALUES (8,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,3,3);
INSERT INTO titulo_pagar VALUES (9,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,4,4);
INSERT INTO titulo_pagar VALUES (10,'2024-01-01','2024-03-03','2024-02-02',199.99,299.99,5,5);
