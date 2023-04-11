INSERT INTO oauth_client_details
	(id, inclusao, status, client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
     --password
	(1, '2021-02-02', 1, 'opus', '{bcrypt}$2a$10$duatArT/VHqZqFzIJErT2ecbvY8djEUxWMidhduwzyCJjuSdyLc5a',
	'admin','password,refresh_token', null, null, 86400, 86400, null, true);
);