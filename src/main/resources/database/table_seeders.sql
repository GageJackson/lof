USE lof_db;
INSERT INTO friend (name, icon,  summoner_level, summoner_id, puu_id, account_id)
VALUES ('gagermeister', 1, 100, 'm-_faKcheSfh6jJgn2RNi4aHB2XShSmWx95jaOm_GbXZDbE', 'LcGogwi3lEv5SCdlgEfIOhL8jOK-VW4BNhptrDtJEhJwSLqWgtK3gKPR2wG4L-k2E6D2Gycs38Lvsg', '5Z5oXpBc1YN9rz8gXEIjL9lYmzRzdaPctXV2NyMwoX7MnIM'),
       ('gvzhnv', 1, 100, 'ffnzCXzM7xH-P7uc82d1YlMNvL8l08WCOIVpjzeeXqXqiOcilQq8OEVpcA', '16cKrIE9nKSPkTOPzLEvZbjPDigPF3dG-pN4MO1gXDmdpOX6eUPVRLj5iH7Feq82BK8uK0zR7ymn9Q', 'bJugrugFdtSI3xICmCPkaDNfdpZ9FMSodxvR7LJiLt7W14I27ARXXYjq'),
       ('Gwertle', 1, 100, 'kMSf-CU2rdDVgGvCE-zmUsI3EEiMKjBSg78vKZGREs8ZBOE', '8xERpzCDfs2ZmhhxNzwRchJ3XYToRw0SqnOcluSmNfqE9YkKuncxT2686MZrUNqiURvw77PGHqDzuw', 'eHe5UpysqL_wgjHqb4vltLxl_cdADUzQME5k0PfGYjBCKps'),
       ('inconSPICuos', 1, 100, '8nNhmvN9Y2LFLcrnc3YsOuNBCDDBEfvOsJP8JwyscdpIGnE', 'pjdOZaG7zwvQ0FY6E5hxYE7ZgSqVEX2Abc26m1vYnQDld33rSNyi90BdhT3S7t2DLOmYmCbNUwp1kg', '77ABeydwPrkj_UkLKg1T7fL355MSztX2bn5S584u_lf4Cg'),
       ('ETXHarpy', 1, 100, 's7nImWDztci7yTS6oivIuVfMzyfIShFLdAooqPI1shITx14', 'Hu0geCS3GciRftSxokl2mAvRCgELp3n3Yq8eidGCQPmAHdCkRkwKzT49tHZdB5lYjD8-K3o0gCOLjw', 'sEdWteLeQ5U7w5WV4FZeI8zcm15t0S-9PYR8uLFNOrK9Fw'),
       ('mightywarrior7', 1, 100, '4oMpxQSllEvmhHHVQ0TEJFtQLsqdsTGYX1Ey7HObLjRM-D4', '59QeUpjTM1S-zUc7PYUaAG0jvy7xZ6evXklLNnpT2d1S-hF5iTSs6ir2Q7eAQ0-gBrE3DVynsFfK_Q', 't05Ef_XHw4ecxKGx6IQQ3DLQ7Fr3JJAjsh8UKRRePsUnsQ'),
       ('iamthejewbear', 1, 100, '-A9JREivIpiYUNnbfK3cfj9_OnraMefziZ17-J3XlsMUhMc', 'C09Y2tiCz3Up2C80SCFiOwesY3m_YQEPc-Pg1lQzQQLd_tIQHJ6n3cYEvyUFJEb0NRWCDX8HUIvOFg', 'zaNRPfUVOPdYjbLildr905pS_Yzo6bOvaHtQhRex6YaVheE'),
       ('RealMannyy', 1, 100, '2xTwNkswk_7BG6I1K731VihRHIvjrRlVY0rUpPArwSTKlk2PB58aLod7LA', 'kxc3nnpgScfzE9o5HWUpnZd4Cj0fOEW92NAUsAGYSMC0agYJ5dBoLsb5lnU67Kx64pmB4zAaGxlJpQ', '4FlCpQEXi-R1lUbrjFR_lgODZhDXjwS91gVsSenNUfCKSeC-GIg1enS7')

SELECT TABLE_SCHEMA AS "DB NAME",
       SUM(DATA_LENGTH + TABLES.INDEX_LENGTH) / 1024 / 1024 AS "SIZE MB"
    FROM information_schema.TABLES
GROUP BY TABLE_SCHEMA;