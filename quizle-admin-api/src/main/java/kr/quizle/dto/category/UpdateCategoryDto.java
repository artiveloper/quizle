package kr.quizle.dto.category;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdateCategoryDto {

    @NotBlank(message = "{categoryName.notBlank}")
    private String name;

    private String imageUrl;

    @Builder
    public UpdateCategoryDto(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

}
