create table "opus"."tb_operacao"
(
  id integer constraint tb_operacao_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, codigo varchar not null unique
, descricao varchar not null
);

create sequence "opus"."seq_tb_operacao"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

create table "opus"."tb_modulo"
(
  id integer constraint tb_modulo_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, codigo varchar not null unique
, descricao varchar not null
);

create sequence "opus"."seq_tb_modulo"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

create table "opus"."tb_operacao_modulo"
(
  id integer constraint tb_operacao_modulo_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, id_operacao integer not null
, id_modulo integer not null
);

create sequence "opus"."seq_operacao_modulo"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

alter table "opus"."tb_operacao_modulo" add foreign key (id_operacao) references "opus".tb_operacao(id);
alter table "opus"."tb_operacao_modulo" add foreign key (id_modulo) references "opus".tb_modulo(id);
