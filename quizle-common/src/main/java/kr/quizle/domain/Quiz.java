package kr.quizle.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "QUIZ")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"id", "name"})
public class Quiz extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUIZ_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Builder
    public Quiz(String name, Category category) {
        this.name = name;
        this.category = category;
    }

}
