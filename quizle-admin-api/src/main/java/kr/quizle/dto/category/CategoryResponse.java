package kr.quizle.dto.category;

import kr.quizle.domain.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CategoryResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.imageUrl = category.getImageUrl();
        this.createdDate = category.getCreatedDate();
        this.lastModifiedDate = category.getLastModifiedDate();
    }

}
