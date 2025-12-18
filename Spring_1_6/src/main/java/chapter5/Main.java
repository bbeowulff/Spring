package chapter5;

import chapter5.Config.ProjectConfiguration;
import chapter5.Model.Comment;
import chapter5.Services.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class)) {

            System.out.println("=== 1. Singleton scope with shared repository ===");
            CommentService commentService = context.getBean(CommentService.class);
            UserService userService = context.getBean(UserService.class);

            boolean sameRepo = commentService.getCommentRepository() == userService.getCommentRepository();
            System.out.println("CommentService and UserService share the same CommentRepository? " + sameRepo);

            System.out.println("\n=== 2. Eager vs Lazy singleton instantiation ===");
            // EagerCommentService created at context startup (message already printed)
            System.out.println("Requesting LazyCommentService bean...");
            LazyCommentService lazyService = context.getBean(LazyCommentService.class);
            lazyService.doWork();

            System.out.println("\n=== 3. Prototype bean via @Bean ===");
            PrototypeCommentService p1 = context.getBean(PrototypeCommentService.class);
            PrototypeCommentService p2 = context.getBean(PrototypeCommentService.class);
            System.out.println("PrototypeCommentService instances equal? " + (p1 == p2));

            System.out.println("\n=== 4. Prototype CommentProcessor used in CommentProcessorService ===");
            CommentProcessorService processorService = context.getBean(CommentProcessorService.class);
            Comment c1 = new Comment("Alice", "First comment");
            Comment c2 = new Comment("Bob", "Second comment");

            processorService.handleComment(c1);
            processorService.handleComment(c2);

            System.out.println("\nDone.");
        }
    }
}
