package by.dev.madhead.hibernate.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        DBConfig.class
})
public class Config {
}
