INSERT INTO api_key(
                    api_key_sn,
                    api_key,
                    use_yn,
                    expr_yn,
                    expr_date,
                    reg_date,
                    upd_date
            ) VALUES (
                    nextval('API_KEY_SN_SEQ'),
                    '86331fbb9f694a829161ee58ce077ec7',
                    'Y',
                    'N',
                    FORMATDATETIME(DATEADD('YEAR', 1, NOW()), 'yyyyMMddHHmmss'),
                    FORMATDATETIME(NOW(), 'yyyyMMddHHmmss'),
                    FORMATDATETIME(NOW(), 'yyyyMMddHHmmss'));
