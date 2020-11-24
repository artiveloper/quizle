package kr.quizle.service;

import kr.quizle.domain.Category;
import kr.quizle.domain.CategoryRepository;
import kr.quizle.dto.category.AddCategoryDto;
import kr.quizle.dto.category.CategoryResponse;
import kr.quizle.dto.category.UpdateCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long addCategory(AddCategoryDto addCategoryDto) {
        Category category = addCategoryDto.toEntity();

        categoryRepository.save(category);

        return category.getId();
    }

    public Page<CategoryResponse> getCategories(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest)
                .map(CategoryResponse::new);
    }

    @Transactional
    public void updateCategory(Long categoryId, UpdateCategoryDto resource) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(categoryId + " not found."));

        category.updateCategory(resource.getName(), resource.getImageUrl());
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(categoryId + " not found."));

        categoryRepository.delete(category);
    }

}
