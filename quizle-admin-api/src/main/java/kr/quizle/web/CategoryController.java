package kr.quizle.web;

import kr.quizle.web.dto.category.AddCategoryRequest;
import kr.quizle.web.dto.category.CategoryResponse;
import kr.quizle.web.dto.category.UpdateCategoryRequest;
import kr.quizle.web.dto.global.ListResult;
import kr.quizle.web.dto.global.PageMeta;
import kr.quizle.web.dto.global.PageRequest;
import kr.quizle.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /*
        todo
        - 등록 v
        - 리스트 가져오기
        - 변경
        - 삭제
        - 유효성 체크
     */

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid AddCategoryRequest resource) throws URISyntaxException {
        Long categoryId = categoryService.addCategory(resource);
        String url = "/v1/categories/" + categoryId;
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @GetMapping
    public ResponseEntity<ListResult<CategoryResponse>> getCategories(PageRequest pageRequest) {
        Page<CategoryResponse> categories = categoryService.getCategories(pageRequest.of());
        ListResult<CategoryResponse> response = new ListResult<>(categories.getContent(), new PageMeta(categories));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId,
                                         @RequestBody @Valid UpdateCategoryRequest resource) {
        categoryService.updateCategory(categoryId, resource);
        return ResponseEntity.ok("{}");
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("{}");
    }

}
