package chapter3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "chapter3")
public class ProjectConfig {

    @Bean
    public Parrot parrot() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }
    /// setter injection
//    @Bean
//    public Person person() {
//        Person p = new Person();
//        p.setName("Ella");
//        p.setParrot(parrot());
//        return p;
//    }
//    @Bean
//    public Person person(Parrot parrot) { // Spring injects this bean
//        Person p = new Person();
//        p.setName("Ella");
//        p.setParrot(parrot);
//        return p;
//    }
}