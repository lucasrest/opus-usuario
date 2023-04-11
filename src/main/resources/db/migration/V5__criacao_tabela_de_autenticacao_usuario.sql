create table oauth_client_details (
    id integer constraint tb_oauth_client_details_pk primary key not null
    , inclusao timestamp not null
    , alteracao timestamp
    , status integer not null ,
    client_id VARCHAR(256) UNIQUE ,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

create sequence seq_oauth_client_details  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;