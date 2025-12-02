package chapter5.Services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class LazyCommentService {

    public LazyCommentService() {
        System.out.println("LazyCommentService instance created!");
    }

    public void doWork() {
        System.out.println("LazyCommentService doing work");
    }
}
