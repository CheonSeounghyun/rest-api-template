package com.wini.restapitemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {

        String query = "INSERT INTO api_key(api_key_sn, api_key, use_yn, expr_yn, expr_date, reg_date, upd_date) VALUES (nextval('API_KEY_SN_SEQ'), REPLACE(RANDOM_UUID(), '-',''), 'Y', 'N', FORMATDATETIME(DATEADD('YEAR', 1, NOW()), 'yyyyMMddHHmmss'), FORMATDATETIME(NOW(), 'yyyyMMddHHmmss'), FORMATDATETIME(NOW(), 'yyyyMMddHHmmss'))";
        jdbcTemplate.execute(query);

        String uuidQuery = "SELECT api_key FROM api_key ORDER BY reg_date DESC LIMIT 1";
        String uuid = jdbcTemplate.queryForObject(uuidQuery, String.class);
        logger.info("Added Api Key: " + uuid);
    }
}
