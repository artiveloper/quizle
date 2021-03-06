package kr.quizle.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name", "imageUrl"}, callSuper = false)
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Builder
    public Category(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void updateCategory(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

}
