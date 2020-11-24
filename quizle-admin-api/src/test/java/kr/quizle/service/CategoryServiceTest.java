package kr.quizle.service;

import kr.quizle.CategoryHelper;
import kr.quizle.domain.Category;
import kr.quizle.domain.CategoryRepository;
import kr.quizle.dto.PageRequest;
import kr.quizle.dto.category.AddCategoryDto;
import kr.quizle.dto.category.CategoryResponse;
import kr.quizle.dto.category.UpdateCategoryDto;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("1. 카테고리를 추가한다")
    @Test
    void addCategoryTest() {
        //given
        String categoryName = "인물퀴즈";

        AddCategoryDto resource = AddCategoryDto.builder()
                .name(categoryName)
                .build();

        Category mockCategory = Category.builder()
                .id(1L)
                .name(categoryName)
                .build();

        given(categoryRepository.save(resource.toEntity())).willReturn(mockCategory);

        //when
        categoryService.addCategory(resource);

        //then
        verify(categoryRepository).save(resource.toEntity());
    }

    @DisplayName("2. 카테고리 목록을 가져온다")
    @Test
    public void getCategoriesTest() {
        //given
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(1);
        pageRequest.setSize(10);

        PageImpl<Category> mockCategories = new CategoryHelper().generateMockCategoriesWithPage(15, pageRequest);

        given(categoryRepository.findAll(pageRequest.of())).willReturn(mockCategories);

        //when
        Page<CategoryResponse> categories = categoryService.getCategories(pageRequest.of());

        //then
        assertThat(categories.getTotalPages()).isEqualTo(2);
        assertThat(categories.getTotalElements()).isEqualTo(15);
        assertThat(categories.isFirst()).isTrue();
        assertThat(categories.isLast()).isFalse();
        verify(categoryRepository).findAll(pageRequest.of());
    }

    @DisplayName("3. 카테고리를 수정한다")
    @Test
    void updateCategoryTest() {
        //given
        Category category = Category.builder()
                .id(1L)
                .name("인물퀴즈")
                .imageUrl("인물퀴즈 이미지 주소")
                .build();

        UpdateCategoryDto expectedCategory = UpdateCategoryDto.builder()
                .name("인물퀴즈(해외)")
                .imageUrl("인물퀴즈 이미지 주소 변경 후")
                .build();

        given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));

        //when
        categoryService.updateCategory(category.getId(), expectedCategory);

        //then
        assertThat(category.getName()).isEqualTo(expectedCategory.getName());
        assertThat(category.getImageUrl()).isEqualTo(expectedCategory.getImageUrl());
    }

}