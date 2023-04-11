create table "opus"."tb_usuario"
(
  id integer constraint tb_usuario_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, email varchar null unique
, nome varchar not null
, senha varchar not null
);

create sequence "opus"."seq_tb_usuario"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

create table "opus".tb_perfil_usuario
(
  id integer constraint tb_perfil_usuario_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, id_perfil integer not null
, id_usuario integer not null
);


create sequence "opus".seq_tb_perfil_usuario  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

alter table "opus".tb_perfil_usuario add foreign key (id_perfil) references "opus".tb_perfil(id);
alter table "opus".tb_perfil_usuario add foreign key (id_usuario) references "opus".tb_usuario(id);

create table "opus".tb_recuperar_conta
(
  id integer constraint tb_recuperar_conta_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, codigo_recuperacao varchar(50) unique not null
, expiracao timestamp not null
, id_usuario integer not null
);

create sequence "opus".seq_tb_recuperar_conta  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

alter table "opus".tb_recuperar_conta add foreign key (id_usuario) references "opus".tb_usuario(id);