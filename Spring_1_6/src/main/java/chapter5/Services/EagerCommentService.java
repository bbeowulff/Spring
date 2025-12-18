package chapter5.Services;

import org.springframework.stereotype.Service;

@Service
public class EagerCommentService {

    public EagerCommentService() {
        System.out.println("EagerCommentService instance created!");
    }

    public void doWork() {
        System.out.println("EagerCommentService doing work");
    }
}
