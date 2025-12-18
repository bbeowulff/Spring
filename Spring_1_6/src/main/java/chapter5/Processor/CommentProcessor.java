package chapter5.Processor;

import chapter5.Model.Comment;
import chapter5.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommentProcessor {

    @Autowired
    private CommentRepository commentRepository;

    private Comment comment;

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void processComment() {
        System.out.println("Processing comment: " + comment);
        // simulate: modify comment, add flags, etc.
    }

    public void validateComment() {
        System.out.println("Validating comment: " + comment);
        // simulate validation and changes
    }

    public void persistProcessedComment() {
        commentRepository.storeComment(comment);
    }
}
