package kr.quizle.service.category;

import kr.quizle.domain.Category;
import kr.quizle.domain.CategoryRepository;
import kr.quizle.web.dto.category.AddCategoryRequest;
import kr.quizle.web.dto.category.CategoryResponse;
import kr.quizle.web.dto.category.UpdateCategoryRequest;
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
    public Long addCategory(AddCategoryRequest addCategoryRequest) {
        Category category = addCategoryRequest.toEntity();

        categoryRepository.save(category);

        return category.getId();
    }

    public Page<CategoryResponse> getCategories(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest)
                .map(CategoryResponse::new);
    }

    @Transactional
    public void updateCategory(Long categoryId, UpdateCategoryRequest resource) {
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
