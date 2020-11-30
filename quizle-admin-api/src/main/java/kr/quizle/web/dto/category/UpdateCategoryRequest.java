package kr.quizle.web.dto.category;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdateCategoryRequest {

    @NotBlank(message = "{categoryName.notBlank}")
    private String name;

    private String imageUrl;

    @Builder
    public UpdateCategoryRequest(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

}
