package de.htw.imi.springdatajpa.repos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Base class for tests that sets up and deletes
 * the SQL schema and test data for the in-memory test data base
 */
abstract class AbstractRepositoryTest {

    private static final String DROP_SCHEMA = "DROP SCHEMA IF EXISTS uni CASCADE";

    @Value("classpath:test-data.sql")
    private Resource dataInitFile;

    // the test schema is different because it uses H2 SQl syntax
    @Value("classpath:test-schema.sql")
    private Resource schemaInitFile;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        try {
            final String schemaSql =
                    StreamUtils.copyToString(schemaInitFile.getInputStream(), StandardCharsets.UTF_8);
            final String testDataSql =
                    StreamUtils.copyToString(dataInitFile.getInputStream(), StandardCharsets.UTF_8);

            jdbcTemplate.execute(schemaSql);
            jdbcTemplate.execute(testDataSql);

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.execute(DROP_SCHEMA);
    }
}