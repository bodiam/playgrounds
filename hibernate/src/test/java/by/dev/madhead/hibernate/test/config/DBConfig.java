package by.dev.madhead.hibernate.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DBConfig {
    @Bean
    public EmbeddedDatabaseFactoryBean embeddedDatabase() {
        final EmbeddedDatabaseFactoryBean result = new EmbeddedDatabaseFactoryBean();

        result.setDatabaseType(EmbeddedDatabaseType.H2);
        result.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("database/schema.sql"), new ClassPathResource("database/data.sql")));

        return result;
    }
}
