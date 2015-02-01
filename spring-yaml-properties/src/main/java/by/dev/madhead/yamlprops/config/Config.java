package by.dev.madhead.yamlprops.config;

import by.dev.madhead.yamlprops.services.ServicesPackage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@Configuration
@ComponentScan(basePackageClasses = {ServicesPackage.class})
public class Config {
    @Bean(name = "yamlProperties")
    public YamlPropertiesFactoryBean yamlProperties() {
        final YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();

        yaml.setResources(new ClassPathResource("application.yaml"));

        return yaml;
    }

    @Bean
    public static PropertyPlaceholderConfigurer properties(@Qualifier("yamlProperties") Properties yamlProperties) {
        final PropertyPlaceholderConfigurer result = new PropertyPlaceholderConfigurer();

        // Cannot use #yamlProperties() method due to static, so use parameter injection
        result.setProperties(yamlProperties);

        return result;
    }
}
