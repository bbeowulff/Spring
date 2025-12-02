package chapter5.Services;

import chapter5.Model.Comment;
import chapter5.Processor.CommentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CommentProcessorService {

    @Autowired
    private ApplicationContext context;

    public void handleComment(Comment comment) {
        CommentProcessor processor = context.getBean(CommentProcessor.class);
        processor.setComment(comment);
        processor.processComment();
        processor.validateComment();
        processor.persistProcessedComment();
    }
}
