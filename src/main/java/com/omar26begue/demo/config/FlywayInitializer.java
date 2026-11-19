package com.omar26begue.demo.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
@AllArgsConstructor
@ConditionalOnProperty(name = "spring.flyway.enabled", havingValue = "true")
public class FlywayInitializer implements InitializingBean {

    private final DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("========================================");
        log.info("Iniciando migraciones de Flyway...");
        log.info("========================================");

        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .locations("classpath:db/migration")
                    .load();

            var result = flyway.migrate();

            log.info("✓ Migraciones completadas exitosamente");
            log.info("  - Migraciones ejecutadas: {}", result.migrationsExecuted);
            log.info("  - Versión actual: {}", result.targetSchemaVersion);
            log.info("========================================");
        } catch (Exception e) {
            log.error("✗ Error ejecutando migraciones de Flyway", e);
            throw new RuntimeException("Flyway migration failed: " + e.getMessage(), e);
        }
    }
}