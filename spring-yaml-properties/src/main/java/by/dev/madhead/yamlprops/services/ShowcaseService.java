package by.dev.madhead.yamlprops.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShowcaseService {
    @Value("${environments.dev.http.host}")
    private String devHost;
    @Value("${greetings.oneline}")
    private String onelineGreeting;
    @Value("${greetings.multiline}")
    private String multilineGreeting;

    public void showcase() {
        System.out.println(devHost); // dev.theawesome.com
        System.out.println(onelineGreeting); // I have come here to chew bubblegum and kick ass... and I'm all out of bubblegum.
        System.out.println(multilineGreeting); // The same as above, but in two lines
    }
}
