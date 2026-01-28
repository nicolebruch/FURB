-- 1. contar quantos animais são de pequeno porte
SELECT COUNT(*) AS qtd_animais_pequeno_porte
FROM animal
WHERE tp_porte = 'P';

-- 2. listar o valor total de notas fiscais emitidas no ano de 2024
SELECT SUM(vl_total) AS total_notas_2024
FROM nota_fiscal
WHERE YEAR(dt_emissao) = 2024;

-- 3. listar o total de agendamentos realizados por cada prestador de serviço
SELECT cd_prestador, COUNT(*) AS total_agendamentos
FROM agendamento
GROUP BY cd_prestador;

-- 4. calcular o valor total dos serviços prestados por cada agendamento
SELECT nr_agendamento, SUM(vl_servico * qt_servico) AS total_servicos
FROM agendamento_servico
GROUP BY nr_agendamento;

-- 5. calcular a média de idade dos animais de cada raça
SELECT r.cd_raca, r.nm_raca,
       AVG(TIMESTAMPDIFF(YEAR, a.dt_nascimento, CURDATE())) AS media_idade
FROM animal a
JOIN raca r ON a.cd_raca = r.cd_raca
GROUP BY r.cd_raca, r.nm_raca;

-- 6. listar o total de produtos cadastrados por categoria
SELECT c.nm_categoria, COUNT(p.cd_produto) AS total_produtos
FROM categoria c
LEFT JOIN produto p ON c.cd_categoria = p.cd_categoria
GROUP BY c.nm_categoria;

-- 7. listar as cidades com o número de clientes cadastrados, ordenando pelo maior número
SELECT m.nm_municipio, COUNT(c.cd_cliente) AS total_clientes
FROM cliente c
JOIN endereco e ON c.cd_endereco = e.cd_endereco
JOIN municipio m ON e.cd_municipio = m.cd_municipio
GROUP BY m.nm_municipio
ORDER BY total_clientes DESC;

-- 8. calcular o valor total de vendas para cada cliente em notas fiscais
SELECT c.nm_cliente, SUM(n.vl_total) AS total_vendas
FROM cliente c
JOIN nota_fiscal n ON c.cd_cliente = n.cd_cliente
GROUP BY c.nm_cliente;

-- 9. obter o nome do prestador de serviço com o número de agendamentos
SELECT p.nm_prestador, COUNT(a.nr_agendamento) AS total_agendamentos
FROM prestador_servico p
LEFT JOIN agendamento a ON p.cd_prestador = a.cd_prestador
GROUP BY p.nm_prestador;

-- 10. calcular a receita total de serviços prestados por cada prestador
SELECT p.nm_prestador, SUM(s.vl_servico * asv.qt_servico) AS receita_total
FROM prestador_servico p
JOIN agendamento a ON p.cd_prestador = a.cd_prestador
JOIN agendamento_servico asv ON a.nr_agendamento = asv.nr_agendamento
JOIN servico s ON asv.cd_servico = s.cd_servico
GROUP BY p.nm_prestador;

-- 11. calcular o valor médio dos produtos vendidos por categoria
SELECT c.nm_categoria, AVG(p.vl_venda) AS media_valor_venda
FROM categoria c
JOIN produto p ON c.cd_categoria = p.cd_categoria
GROUP BY c.nm_categoria;

-- 12. listar os clientes que realizaram mais de 5 agendamentos
SELECT c.nm_cliente, COUNT(a.nr_agendamento) AS total_agendamentos
FROM cliente c
JOIN agendamento a ON c.cd_cliente = a.cd_cliente
GROUP BY c.nm_cliente
HAVING COUNT(a.nr_agendamento) > 5;

-- 13. calcular a receita média de cada prestador de serviço em agendamentos
SELECT p.nm_prestador, AVG(s.vl_servico * asv.qt_servico) AS receita_media
FROM prestador_servico p
JOIN agendamento a ON p.cd_prestador = a.cd_prestador
JOIN agendamento_servico asv ON a.nr_agendamento = asv.nr_agendamento
JOIN servico s ON asv.cd_servico = s.cd_servico
GROUP BY p.nm_prestador;

-- 14. listar as espécies com mais de 3 raças cadastradas
SELECT e.nm_especie, COUNT(r.cd_raca) AS total_racas
FROM especie e
JOIN raca r ON e.cd_especie = r.cd_especie
GROUP BY e.nm_especie
HAVING COUNT(r.cd_raca) > 3;

-- 15. listar os clientes que possuem mais de um animal cadastrado
SELECT c.nm_cliente, COUNT(a.cd_animal) AS total_animais
FROM cliente c
JOIN animal a ON c.cd_cliente = a.cd_cliente
GROUP BY c.nm_cliente
HAVING COUNT(a.cd_animal) > 1;

