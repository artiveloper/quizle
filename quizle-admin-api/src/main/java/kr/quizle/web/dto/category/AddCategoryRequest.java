package kr.quizle.web.dto.category;

import kr.quizle.domain.Category;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AddCategoryRequest {

    @NotBlank(message = "{categoryName.notBlank}")
    private String name;

    private String imageUrl;

    @Builder
    public AddCategoryRequest(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Category toEntity() {
        return Category.builder()
                .name(this.name)
                .imageUrl(this.imageUrl)
                .build();
    }

}
