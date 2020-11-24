package kr.quizle.dto.category;

import kr.quizle.domain.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResponse {

    private Long id;

    private String name;

    private String imageUrl;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.imageUrl = category.getImageUrl();
    }

}
