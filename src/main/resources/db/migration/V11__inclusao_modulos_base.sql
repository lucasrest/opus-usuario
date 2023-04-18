insert into "opus".tb_perfil(id, inclusao, status, codigo, descricao)
values(1, '2023/04/18', 1, 'ADMIN', 'Modulo Admin');

insert into "opus".tb_perfil(id, inclusao, status, codigo, descricao)
values(2, '2023/04/18', 1, 'GERENTE', 'Modulo Gerente');

insert into "opus".tb_perfil(id, inclusao, status, codigo, descricao)
values(3, '2023/04/18', 1, 'ANALISTA', 'Modulo Analista');

insert into "opus".tb_perfil(id, inclusao, status, codigo, descricao)
values(4, '2023/04/18', 1, 'TALENTO', 'Modulo Talento');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(1, '2023/04/18', 1, 'campanha-buscartodos', 'Buscar todoas as campanhas');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(2, '2023/04/18', 1, 'campanha-buscarporid', 'Buscar campanha por id');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(3, '2023/04/18', 1, 'campanha-incluir', 'Incluir campanha');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(4, '2023/04/18', 1, 'campanha-atualizar', 'Buscar Atualizar campanha');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(5, '2023/04/18', 1, 'campanha-excluir', 'Excluir campanha');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(6, '2023/04/18', 1, 'campanha-buscartodosfiltro', 'Buscar campanha com filtro');

insert into "opu".tb_modulo(id, inclusao, status, codigo, descricao)
values(1, '2023/04/18', 1, 'mod-campanha', 'Modulo completo campanha');

insert into "bsx".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(1, '2023/04/18', 1, 1, 1);

insert into "bsx".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(2, '2023/04/18', 1, 2, 1);

insert into "bsx".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(3, '2023/04/18', 1, 3, 1);

insert into "bsx".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(4, '2023/04/18', 1, 4, 1);

insert into "bsx".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(5, '2023/04/18', 1, 5, 1);

insert into "bsx".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(6, '2023/04/18', 1, 6, 1);

insert into "bsx".tb_modulo_perfil(id, inclusao, status, id_modulo, id_perfil)
values(1, '2023/04/18', 1, 1, 1);

insert into "bsx".tb_modulo_perfil(id, inclusao, status, id_modulo, id_perfil)
values(1, '2023/04/18', 1, 1, 2);

insert into "bsx".tb_modulo_perfil(id, inclusao, status, id_modulo, id_perfil)
values(1, '2023/04/18', 1, 1, 3);