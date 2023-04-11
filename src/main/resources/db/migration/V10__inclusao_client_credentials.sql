INSERT INTO oauth_client_details
	(id, inclusao, status, client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES

--campanha_secr3t3!
(2, '2022-04-13', 1, 'campanha', '{bcrypt}$2a$12$pakUlsJjptjPvlLvB5Y4HelRQx.4SA8prygXH9e5hHi5iodX6YqFa',
	'campanha,usuario','client_credentials', null, null, 86400, 86400, null, true
);