package kr.quizle.dto.category;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdateCategoryDto {

    @NotBlank
    private String name;

    private String imageUrl;

    @Builder
    public UpdateCategoryDto(@NotBlank String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

}
