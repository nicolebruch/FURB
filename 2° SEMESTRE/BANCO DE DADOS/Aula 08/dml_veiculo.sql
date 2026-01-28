USE gestao_veiculo;

-- tabela combustivel
INSERT INTO combustivel (cd_combustivel, ds_combustivel) VALUES 
(1, 'Gasolina'),
(2, 'Etanol'),
(3, 'Diesel'),
(4, 'GNV'),
(5, 'Elétrico'),
(6, 'Híbrido'),
(7, 'Biodiesel'),
(8, 'Querosene'),
(9, 'Flex'),
(10, 'Hidrogênio');

-- tabela marca
INSERT INTO marca (cd_marca, ds_marca) VALUES
(1, 'Toyota'),
(2, 'Honda'),
(3, 'Chevrolet'),
(4, 'Volkswagen'),
(5, 'Fiat'),
(6, 'Ford'),
(7, 'Hyundai'),
(8, 'Nissan'),
(9, 'Peugeot'),
(10, 'Renault');

-- tabela localidade
INSERT INTO localidade (cd_localidade, ds_localidade) VALUES
(1, 'São Paulo'),
(2, 'Rio de Janeiro'),
(3, 'Belo Horizonte'),
(4, 'Curitiba'),
(5, 'Porto Alegre'),
(6, 'Salvador'),
(7, 'Fortaleza'),
(8, 'Brasília'),
(9, 'Recife'),
(10, 'Manaus');

-- tabela acessorio
INSERT INTO acessorio (cd_acessorio, ds_acessorio) VALUES
(1, 'Ar-condicionado'),
(2, 'Direção hidráulica'),
(3, 'Airbag'),
(4, 'Freios ABS'),
(5, 'Sensor de estacionamento'),
(6, 'Câmera de ré'),
(7, 'Travas elétricas'),
(8, 'Vidros elétricos'),
(9, 'Banco de couro'),
(10, 'Teto solar');

-- tabela cor
INSERT INTO cor (cd_cor, ds_cor) VALUES
(1, 'Branco'),
(2, 'Preto'),
(3, 'Prata'),
(4, 'Vermelho'),
(5, 'Azul'),
(6, 'Cinza'),
(7, 'Verde'),
(8, 'Amarelo'),
(9, 'Marrom'),
(10, 'Bege');

-- tabela modelo
INSERT INTO modelo (cd_modelo, cd_marca, ds_modelo) VALUES
(1, 1, 'Corolla'),
(2, 2, 'Civic'),
(3, 3, 'Onix'),
(4, 4, 'Gol'),
(5, 5, 'Argo'),
(6, 6, 'Ka'),
(7, 7, 'HB20'),
(8, 8, 'Versa'),
(9, 9, '208'),
(10, 10, 'Kwid');

-- tabela proprietario
INSERT INTO proprietario (cd_proprietario, cd_localidade, nm_proprietario, ds_logradouro, ds_complemento, ds_bairro, nr_telefone, ds_email, sg_uf) VALUES
(1, 1, 'Carlos Silva', 'Rua A', 'Ap 101', 'Centro', '11987654321', 'carlos@email.com', 'SP'),
(2, 2, 'Mariana Souza', 'Rua B', 'Casa 2', 'Copacabana', '21998765432', 'mariana@email.com', 'RJ'),
(3, 3, 'Pedro Lima', 'Rua C', 'Ap 302', 'Savassi', '31999887766', 'pedro@email.com', 'MG'),
(4, 4, 'Fernanda Alves', 'Rua D', '', 'Batel', '41991234567', 'fernanda@email.com', 'PR'),
(5, 5, 'Lucas Oliveira', 'Rua E', '', 'Moinhos', '51993214567', 'lucas@email.com', 'RS'),
(6, 6, 'Patrícia Ramos', 'Rua F', 'Casa 5', 'Pituba', '71998761234', 'patricia@email.com', 'BA'),
(7, 7, 'João Santos', 'Rua G', '', 'Aldeota', '85993215678', 'joao@email.com', 'CE'),
(8, 8, 'Roberta Costa', 'Rua H', 'Ap 204', 'Asa Sul', '61999998888', 'roberta@email.com', 'DF'),
(9, 9, 'André Barbosa', 'Rua I', '', 'Boa Viagem', '81991231234', 'andre@email.com', 'PE'),
(10, 10, 'Sofia Nunes', 'Rua J', '', 'Centro', '92995678900', 'sofia@email.com', 'AM');

-- tabela veiculo
INSERT INTO veiculo (nr_placa, cd_cor, cd_proprietario, cd_modelo, nr_ano_fab, nr_ano_mod, qt_km_rodado, qt_portas, ds_observacao) VALUES
('ABC1234', 1, 1, 1, 2015, 2016, 85000, 4, 'Bem conservado'),
('BCD2345', 2, 2, 2, 2018, 2019, 55000, 4, 'Único dono'),
('CDE3456', 3, 3, 3, 2020, 2020, 25000, 4, 'Baixa km'),
('DEF4567', 4, 4, 4, 2012, 2013, 120000, 2, 'Carro de garagem'),
('EFG5678', 5, 5, 5, 2019, 2020, 30000, 4, 'Revisões em dia'),
('FGH6789', 6, 6, 6, 2016, 2017, 70000, 4, 'Completo'),
('GHI7890', 7, 7, 7, 2017, 2018, 62000, 4, 'Pouco rodado'),
('HIJ8901', 8, 8, 8, 2021, 2022, 15000, 4, 'Novo com pouco uso'),
('IJK9012', 9, 9, 9, 2010, 2011, 130000, 2, 'Carro antigo'),
('JKL0123', 10, 10, 10, 2022, 2023, 8000, 4, 'Sem detalhes');

-- tabela veiculo_combustivel
INSERT INTO veiculo_combustivel (nr_placa, cd_combustivel) VALUES
('ABC1234', 1),
('BCD2345', 2),
('CDE3456', 3),
('DEF4567', 1),
('EFG5678', 4),
('FGH6789', 1),
('GHI7890', 5),
('HIJ8901', 6),
('IJK9012', 3),
('JKL0123', 9);

-- tabela veiculo_acessorio
INSERT INTO veiculo_acessorio (nr_placa, cd_acessorio) VALUES
('ABC1234', 1),
('ABC1234', 2),
('BCD2345', 3),
('BCD2345', 4),
('CDE3456', 5),
('DEF4567', 6),
('EFG5678', 7),
('FGH6789', 8),
('GHI7890', 9),
('JKL0123', 10);

SHOW TABLES;
SELECT * FROM veiculo;
SELECT * FROM veiculo_combustivel;
