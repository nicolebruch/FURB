-- 1
SELECT c.nm_cliente, a.nm_animal
FROM cliente c
JOIN animal a ON c.cd_cliente = a.cd_cliente;

-- 2
SELECT p.cd_produto, p.nm_produto, p.ds_produto, p.vl_custo, p.vl_venda, c.ds_categoria
FROM produto p
JOIN categoria c ON p.cd_categoria = c.cd_categoria;

-- 3
SELECT ag.nr_agendamento, ag.dt_agendamento, ag.hr_agendamento, sp.cd_prestador, sp.cd_servico
FROM agendamento ag
JOIN agendamento_servico sp ON ag.nr_agendamento = sp.nr_agendamento;

-- 4
SELECT s.ds_servico, s.vl_servico
FROM servico s;

-- 5
SELECT ps.nm_prestador, s.ds_servico
FROM prestador_servico ps
JOIN service_prestador sp ON ps.cd_prestador = sp.cd_prestador
JOIN servico s ON sp.cd_servico = s.cd_servico;

-- 6
SELECT nf.nr_nota_fiscal, c.nm_cliente
FROM nota_fiscal nf
JOIN cliente c ON nf.cd_cliente = c.cd_cliente;

-- 7
SELECT nf.nr_nota_fiscal, i.cd_produto, i.vl_produto
FROM nota_fiscal nf
JOIN item_nota_fiscal i ON nf.nr_nota_fiscal = i.nr_nota_fiscal;

-- 8
SELECT c.nm_cliente, a.nm_animal, e.nm_especie
FROM cliente c
JOIN animal a ON c.cd_cliente = a.cd_cliente
JOIN especie e ON a.cd_especie = e.cd_especie;

-- 9
SELECT ag.nr_agendamento, c.nm_cliente, a.nm_animal
FROM agendamento ag
JOIN cliente c ON ag.cd_cliente = c.cd_cliente
JOIN animal a ON ag.cd_animal = a.cd_animal;

-- 10
SELECT c.nm_cliente, e.nm_municipio
FROM cliente c
JOIN endereco en ON c.cd_endereco = en.cd_endereco
JOIN municipio e ON en.cd_municipio = e.cd_municipio;

-- 11
SELECT ps.nm_prestador, m.nm_municipio
FROM prestador_servico ps
JOIN endereco e ON ps.cd_endereco = e.cd_endereco
JOIN municipio m ON e.cd_municipio = m.cd_municipio;

-- 12
SELECT nf.nr_nota_fiscal, i.cd_produto, i.vl_produto, p.qt_estoque
FROM nota_fiscal nf
JOIN item_nota_fiscal i ON nf.nr_nota_fiscal = i.nr_nota_fiscal
JOIN produto p ON i.cd_produto = p.cd_produto;

-- 13
SELECT ag.nr_agendamento, s.ds_servico, ags.vl_servico
FROM agendamento ag
JOIN agendamento_servico ags ON ag.nr_agendamento = ags.nr_agendamento
JOIN servico s ON ags.cd_servico = s.cd_servico;

-- 14
SELECT ps.nm_prestador, s.ds_servico, s.vl_servico
FROM prestador_servico ps
JOIN service_prestador sp ON ps.cd_prestador = sp.cd_prestador
JOIN servico s ON sp.cd_servico = s.cd_servico
WHERE s.vl_servico > 100.00;

-- 15
SELECT a.nm_animal, r.nm_raca, e.nm_especie
FROM animal a
JOIN raca r ON a.cd_raca = r.cd_raca
JOIN especie e ON r.cd_especie = e.cd_especie
WHERE r.nm_raca = 'Poodle';
