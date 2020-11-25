package kr.quizle;

import kr.quizle.domain.Category;
import kr.quizle.global.dto.PageRequest;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoryHelper {

    public List<Category> generateMockCategories(int size) {
        List<Category> categories = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            categories.add(
                    Category.builder()
                            .id((long) i)
                            .name("카테고리 이름 " + i)
                            .imageUrl("카테고리 이미지 주소 " + i)
                            .build()
            );
        }
        return categories;
    }

    public PageImpl<Category> generateMockCategoriesWithPage(int contentSize, PageRequest pageRequest) {
        List<Category> contents = new ArrayList<>();
        for (int i = 1; i <= contentSize; i++) {
            contents.add(
                    Category.builder()
                            .id((long) i)
                            .name("카테고리 이름 " + i)
                            .imageUrl("카테고리 이미지 주소 " + i)
                            .build()
            );
        }

        return new PageImpl<>(contents, pageRequest.of(), contents.size());
    }

}
