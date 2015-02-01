package by.dev.madhead.yamlprops;

import by.dev.madhead.yamlprops.config.Config;
import by.dev.madhead.yamlprops.services.ShowcaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        final ShowcaseService service = context.getBean(ShowcaseService.class);

        service.showcase();
    }
}
