package kr.quizle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class QuizleCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizleCommonApplication.class, args);
    }

}
