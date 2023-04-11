insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(6, '2021/07/03', 1, 'operacao-buscartodos', 'Buscar todas as operações ');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(7, '2021/07/03', 1, 'operacao-buscarporid', 'Buscar operação por id ');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(8, '2021/07/03', 1, 'operacao-incluir', 'Incluir Operação');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(9, '2021/07/03', 1, 'operacao-atualizar', 'Atualizar uma Operação');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(10, '2021/07/03', 1, 'operacao-excluir', 'Deletar uma operação');



insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(11, '2021/07/03', 1, 'modulo-buscartodos', 'Buscar todos os modulos ');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(12, '2021/07/03', 1, 'modulo-buscarporid', 'Buscar modulo por id ');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(13, '2021/07/03', 1, 'modulo-incluir', 'Incluir modulo');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(14, '2021/07/03', 1, 'modulo-atualizar', 'Atualizar modulo');

insert into "opus".tb_operacao(id, inclusao, status, codigo, descricao)
values(15, '2021/07/03', 1, 'modulo-excluir', 'Deletar modulo');


insert into "opus".tb_modulo(id, inclusao, status, codigo, descricao)
values(1, '2021/07/03', 1, 'mod-operacao', 'Modulo de operações');

insert into "opus".tb_modulo(id, inclusao, status, codigo, descricao)
values(2, '2021/07/03', 1, 'mod-modulo', 'Modulo de modulos');



insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(1, '2021/07/03', 1, 6, 1);

insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(2, '2021/07/03', 1, 7, 1);

insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(3, '2021/07/03', 1, 8, 1);

insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(4, '2021/07/03', 1, 9, 1);

insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(5, '2021/07/03', 1, 10, 1);


insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(6, '2021/07/03', 1, 11, 2);

insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(7, '2021/07/03', 1, 12, 2);

insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(8, '2021/07/03', 1, 13, 2);

insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(9, '2021/07/03', 1, 14, 2);

insert into "opus".tb_operacao_modulo(id, inclusao, status, id_operacao, id_modulo)
values(10, '2021/07/03', 1, 15, 2);


insert into "opus".tb_modulo_perfil(id, inclusao, status, id_modulo, id_perfil)
values(1, '2021/07/03', 1, 1, 1);

insert into "opus".tb_modulo_perfil(id, inclusao, status, id_modulo, id_perfil)
values(2, '2021/07/03', 1, 2, 1);


