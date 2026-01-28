-- criando as tabelas da base petshop
CREATE TABLE cliente
(cd_cliente INT,
nm_cliente VARCHAR(50),
nr_telefone VARCHAR(50),
ds_email VARCHAR(50),
dt_nascimento DATE -- aqui não vai a ","
);

CREATE TABLE especie
(cd_especie INT,
nm_especie VARCHAR(30)
);

CREATE TABLE raca
(cd_raca INT,
nm_raca VARCHAR(30),
cd_especie INT
);

CREATE TABLE categoria
(cd_categoria INT,
nm_categoria VARCHAR(50)
);

CREATE TABLE nota_fiscal
(nr_nota_fiscal INT,
dt_emissao DATE,
vl_total DECIMAL(8,2),
cd_cliente INT
);

CREATE TABLE produto
(cd_produto INT,
nm_produto VARCHAR(50),
ds_produto VARCHAR(50),
vl_custo DECIMAL(8,2),
vl_venda DECIMAL(8,2),
qt_estoque INT,
cd_categoria INT
);

CREATE TABLE animal
(cd_animal INT,
nm_animal VARCHAR(50),
ds_animal VARCHAR(50),
dt_nascimento DATE,
tp_porte CHAR(01),
cd_raca INT,
cd_cliente INT
);

CREATE TABLE item_nota_fiscal
(nr_nota_fiscal INT,
cd_produto INT,
qt_produto INT,
vl_produto DECIMAL(8,2)
);