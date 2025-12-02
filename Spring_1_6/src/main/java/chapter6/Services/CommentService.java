package chapter6.Services;

import chapter6.Annotations.ToLog;
import chapter6.Model.Comment;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CommentService {

    private final Logger logger = Logger.getLogger(CommentService.class.getName());

    // ─────────────────────────────────────────────────────────────────────────
    // STEP 1: Simple void method (example 1)
    // public void publishComment(Comment comment) {
    //     logger.info("Publishing comment: " + comment.getText());
    // }
    // ─────────────────────────────────────────────────────────────────────────

    // ─────────────────────────────────────────────────────────────────────────
    // STEP 2–3: Method returns a value so aspect can log/alter it (examples 2–3)
    // public String publishComment(Comment comment) {
    //     logger.info("Publishing comment: " + comment.getText());
    //     return "SUCCESS";
    // }
    // ─────────────────────────────────────────────────────────────────────────

    // ─────────────────────────────────────────────────────────────────────────
    // FINAL VERSION (active):
    // Used by:
    //  - Example 2/3 (around advice with parameters & return)
    //  - Example 4 (annotated methods)
    //  - Example 5 (multiple aspects + @Order)
    // ─────────────────────────────────────────────────────────────────────────
    @ToLog
    public String publishComment(Comment comment) {
        logger.info("Publishing comment: " + comment.getText());
        return "SUCCESS";
    }

    // Used in the "intercept annotated methods" example:
    public void publishCommentNoLogAnnotation(Comment comment) {
        logger.info("Publishing (NO @ToLog): " + comment.getText());
    }

    @ToLog
    public void deleteComment(Comment comment) {
        logger.info("Deleting comment: " + comment.getText());
    }

    public void editComment(Comment comment) {
        logger.info("Editing comment: " + comment.getText());
    }
}
