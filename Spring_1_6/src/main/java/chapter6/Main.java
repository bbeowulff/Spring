package chapter6;

import chapter6.Model.Comment;
import chapter6.Services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        CommentService service = context.getBean(CommentService.class);

        Comment comment = new Comment();
        comment.setText("Demo comment");
        comment.setAuthor("Natasha");

        String result = service.publishComment(comment);
        logger.info("Result seen in main(): " + result);

        // Just to show multiple methods:
        service.deleteComment(comment);
        service.editComment(comment);

        context.close();
    }
}
