package chapter2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Parrot x = new Parrot();
        x.setName("Macaw");
        Supplier<Parrot> parrotSupplier = () -> x;
        context.registerBean("last_parrot",Parrot.class, parrotSupplier);

          Parrot p = context.getBean( Parrot.class);
//          System.out.println(p);
          System.out.println(p.getName());
//
//        String s = context.getBean(String.class);
//        System.out.println(s);
//
//        Integer num = context.getBean(Integer.class);
//        System.out.println(num);

    }
}
