create table "opus"."tb_perfil"
(
  id integer constraint tb_perfil_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, codigo varchar not null unique
, descricao varchar
);

create sequence "opus"."seq_tb_perfil"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;


create table "opus"."tb_modulo_perfil"
(
  id integer constraint tb_modulo_perfil_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, id_modulo integer not null
, id_perfil integer not null
);


create sequence "opus"."seq_tb_modulo_perfil"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

alter table "opus"."tb_modulo_perfil" add foreign key (id_modulo) references "opus".tb_modulo(id);
alter table "opus"."tb_modulo_perfil" add foreign key (id_perfil) references "opus".tb_perfil(id);