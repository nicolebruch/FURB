CREATE DATABASE gestao_veiculo;
USE gestao_veiculo;

-- tabela combustivel
CREATE TABLE combustivel (
  cd_combustivel INT PRIMARY KEY,
  ds_combustivel VARCHAR(30)
);

-- tabela marca
CREATE TABLE marca (
  cd_marca INT PRIMARY KEY,
  ds_marca VARCHAR(30)
);

-- tabela localidade
CREATE TABLE localidade (
  cd_localidade INT PRIMARY KEY,
  ds_localidade VARCHAR(50)
);

-- tabela acessorio
CREATE TABLE acessorio (
  cd_acessorio INT PRIMARY KEY,
  ds_acessorio VARCHAR(50)
);

-- tabela cor
CREATE TABLE cor (
  cd_cor INT PRIMARY KEY,
  ds_cor VARCHAR(30)
);

-- tabela modelo
CREATE TABLE modelo (
  cd_modelo INT PRIMARY KEY,
  cd_marca INT,
  ds_modelo VARCHAR(50),
  FOREIGN KEY (cd_marca) REFERENCES marca(cd_marca)
);

-- tabela proprietario
CREATE TABLE proprietario (
  cd_proprietario INT PRIMARY KEY,
  cd_localidade INT,
  nm_proprietario VARCHAR(50),
  ds_logradouro VARCHAR(50),
  ds_complemento VARCHAR(50),
  ds_bairro VARCHAR(50),
  nr_telefone VARCHAR(15),
  ds_email VARCHAR(50),
  sg_uf CHAR(2),
  FOREIGN KEY (cd_localidade) REFERENCES localidade(cd_localidade)
);

-- tabela veiculo
CREATE TABLE veiculo (
  nr_placa CHAR(7) PRIMARY KEY,
  cd_cor INT,
  cd_proprietario INT,
  cd_modelo INT,
  nr_ano_fab INT,
  nr_ano_mod INT,
  qt_km_rodado INT,
  qt_portas INT,
  ds_observacao VARCHAR(100),
  FOREIGN KEY (cd_cor) REFERENCES cor(cd_cor),
  FOREIGN KEY (cd_proprietario) REFERENCES proprietario(cd_proprietario),
  FOREIGN KEY (cd_modelo) REFERENCES modelo(cd_modelo)
);

-- tabela veiculo_combustivel
CREATE TABLE veiculo_combustivel (
  nr_placa CHAR(7),
  cd_combustivel INT,
  PRIMARY KEY (nr_placa, cd_combustivel),
  FOREIGN KEY (nr_placa) REFERENCES veiculo(nr_placa),
  FOREIGN KEY (cd_combustivel) REFERENCES combustivel(cd_combustivel)
);

-- tabela veiculo_acessorio
CREATE TABLE veiculo_acessorio (
  nr_placa CHAR(7),
  cd_acessorio INT,
  PRIMARY KEY (nr_placa, cd_acessorio),
  FOREIGN KEY (nr_placa) REFERENCES veiculo(nr_placa),
  FOREIGN KEY (cd_acessorio) REFERENCES acessorio(cd_acessorio)
);
