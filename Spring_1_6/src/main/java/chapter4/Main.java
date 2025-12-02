package chapter4;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class)) {

            Comment comment = new Comment();
            comment.setAuthor("Laurentiu");
            comment.setText("Demo comment (Spring, abstractions)");

            CommentService commentService = context.getBean(CommentService.class);
            commentService.publishComment(comment);
        }
    }
}
