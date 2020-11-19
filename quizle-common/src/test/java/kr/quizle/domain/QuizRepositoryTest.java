package kr.quizle.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
class QuizRepositoryTest {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void 퀴즈생성() {
        //given
        String categoryName = "인물퀴즈";

        Category category = Category.builder()
                .name(categoryName)
                .build();

        categoryRepository.save(category);

        //when
        String name = "세종대왕";
        Quiz quiz = Quiz.builder()
                .category(category)
                .name(name)
                .build();

        quizRepository.save(quiz);

        //then
        Quiz selectedQuiz = quizRepository.findAll().get(0);

        assertThat(selectedQuiz.getName()).isEqualTo(name);
        assertThat(selectedQuiz.getCategory().getName()).isEqualTo(categoryName);
    }

}