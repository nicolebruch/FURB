CREATE DATABASE petshop;
USE petshop;

-- tabela municipio
CREATE TABLE municipio (
    cd_municipio INT AUTO_INCREMENT PRIMARY KEY,
    nm_municipio VARCHAR(50),
    sg_uf CHAR(2)
);

-- tabela endereco
CREATE TABLE endereco (
    cd_endereco INT AUTO_INCREMENT PRIMARY KEY,
    ds_logradouro VARCHAR(50),
    ds_complemento VARCHAR(50),
    nm_bairro VARCHAR(30),
    nr_cep CHAR(8),
    cd_municipio INT,
    FOREIGN KEY (cd_municipio) REFERENCES municipio(cd_municipio)
);

-- tabela cliente
CREATE TABLE cliente (
    cd_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nm_cliente VARCHAR(50),
    nr_telefone VARCHAR(15),
    ds_email VARCHAR(50),
    dt_nascimento DATE,
    cd_endereco INT,
    FOREIGN KEY (cd_endereco) REFERENCES endereco(cd_endereco)
);

-- tabela especie
CREATE TABLE especie (
    cd_especie INT AUTO_INCREMENT PRIMARY KEY,
    nm_especie VARCHAR(30)
);

-- tabela raca
CREATE TABLE raca (
    cd_raca INT AUTO_INCREMENT PRIMARY KEY,
    nm_raca VARCHAR(30),
    cd_especie INT,
    FOREIGN KEY (cd_especie) REFERENCES especie(cd_especie)
);

-- tabela categoria
CREATE TABLE categoria (
    cd_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nm_categoria VARCHAR(50)
);

-- tabela produto
CREATE TABLE produto (
    cd_produto INT AUTO_INCREMENT PRIMARY KEY,
    nm_produto VARCHAR(50),
    ds_produto VARCHAR(50),
    vl_custo DECIMAL(8,2),
    vl_venda DECIMAL(8,2),
    qt_estoque INT,
    cd_categoria INT,
    FOREIGN KEY (cd_categoria) REFERENCES categoria(cd_categoria)
);

-- tabela animal
CREATE TABLE animal (
    cd_animal INT AUTO_INCREMENT PRIMARY KEY,
    nm_animal VARCHAR(50),
    ds_animal VARCHAR(50),
    dt_nascimento DATE,
    tp_porte CHAR(1),
    cd_raca INT,
    cd_cliente INT,
    FOREIGN KEY (cd_raca) REFERENCES raca(cd_raca),
    FOREIGN KEY (cd_cliente) REFERENCES cliente(cd_cliente)
);

-- tabela nota_fiscal
CREATE TABLE nota_fiscal (
    nr_nota_fiscal INT AUTO_INCREMENT PRIMARY KEY,
    dt_emissao DATE,
    vl_total DECIMAL(8,2),
    cd_cliente INT,
    FOREIGN KEY (cd_cliente) REFERENCES cliente(cd_cliente)
);

-- tabela item_nota_fiscal
CREATE TABLE item_nota_fiscal (
    nr_nota_fiscal INT,
    cd_produto INT,
    qt_produto INT,
    vl_produto DECIMAL(8,2),
    PRIMARY KEY (nr_nota_fiscal, cd_produto),
    FOREIGN KEY (nr_nota_fiscal) REFERENCES nota_fiscal(nr_nota_fiscal),
    FOREIGN KEY (cd_produto) REFERENCES produto(cd_produto)
);

-- tabela servico
CREATE TABLE servico (
    cd_servico INT AUTO_INCREMENT PRIMARY KEY,
    ds_servico VARCHAR(50),
    vl_servico DECIMAL(6,2)
);

-- tabela prestador_servico
CREATE TABLE prestador_servico (
    cd_prestador INT AUTO_INCREMENT PRIMARY KEY,
    nm_prestador VARCHAR(50),
    nr_telefone VARCHAR(15),
    ds_email VARCHAR(50),
    cd_endereco INT,
    FOREIGN KEY (cd_endereco) REFERENCES endereco(cd_endereco)
);

-- tabela servico_prestador
CREATE TABLE servico_prestador (
    cd_prestador INT,
    cd_servico INT,
    PRIMARY KEY (cd_prestador, cd_servico),
    FOREIGN KEY (cd_prestador) REFERENCES prestador_servico(cd_prestador),
    FOREIGN KEY (cd_servico) REFERENCES servico(cd_servico)
);

-- tabela agendamento
CREATE TABLE agendamento (
    nr_agendamento INT AUTO_INCREMENT PRIMARY KEY,
    dt_agendamento DATE,
    hr_agendamento TIME,
    cd_cliente INT,
    cd_animal INT,
    cd_prestador INT,
    FOREIGN KEY (cd_cliente) REFERENCES cliente(cd_cliente),
    FOREIGN KEY (cd_animal) REFERENCES animal(cd_animal),
    FOREIGN KEY (cd_prestador) REFERENCES prestador_servico(cd_prestador)
);

-- tabela agendamento_servico
CREATE TABLE agendamento_servico (
    nr_agendamento INT,
    cd_servico INT,
    qt_servico INT,
    vl_servico DECIMAL(6,2),
    PRIMARY KEY (nr_agendamento, cd_servico),
    FOREIGN KEY (nr_agendamento) REFERENCES agendamento(nr_agendamento),
    FOREIGN KEY (cd_servico) REFERENCES servico(cd_servico)
);

