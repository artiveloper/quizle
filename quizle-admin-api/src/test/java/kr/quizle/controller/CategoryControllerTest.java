package kr.quizle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.quizle.CategoryHelper;
import kr.quizle.dto.category.AddCategoryDto;
import kr.quizle.dto.category.CategoryResponse;
import kr.quizle.dto.category.UpdateCategoryDto;
import kr.quizle.global.dto.PageRequest;
import kr.quizle.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("1. 카테고리를 추가한다")
    @Test
    void createCategoryTest() throws Exception {
        //given
        String url = "/v1/categories";

        String categoryName = "인물";

        AddCategoryDto resources = AddCategoryDto
                .builder()
                .name(categoryName)
                .build();

        given(categoryService.addCategory(resources)).willReturn(1L);

        //when, then
        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resources)))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl(url + "/1"));

        verify(categoryService).addCategory(resources);
    }

    @DisplayName("2. 카테고리 목록을 가져온다")
    @Test
    void getCategoriesTest() throws Exception {
        //given
        String url = "/v1/categories";

        String page = "1";
        String size = "10";

        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(Integer.parseInt(page));
        pageRequest.setSize(Integer.parseInt(size));

        Page<CategoryResponse> mockCategories = new CategoryHelper().generateMockCategoriesWithPage(15, pageRequest).map(CategoryResponse::new);

        given(categoryService.getCategories(pageRequest.of())).willReturn(mockCategories);

        //when, then
        this.mockMvc.perform(get(url)
                .param("page", page)
                .param("size", size))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(15))
                .andExpect(jsonPath("$.data[0].id").exists())
                .andExpect(jsonPath("$.data[0].name").exists())
                .andExpect(jsonPath("$.data[0].imageUrl").exists())
                .andExpect(jsonPath("$.pageMeta.totalCount").value(15))
                .andExpect(jsonPath("$.pageMeta.totalPage").value(2))
                .andExpect(jsonPath("$.pageMeta.isFirst").value(true))
                .andExpect(jsonPath("$.pageMeta.isLast").value(false));
    }

    @DisplayName("3. 카테고리를 수정한다")
    @Test
    void updateCategoryTest() throws Exception {
        //given
        String categoryId = "1";
        String url = "/v1/categories/" + categoryId;

        UpdateCategoryDto resource = UpdateCategoryDto
                .builder()
                .name("인물퀴즈 수정")
                .build();

        //when
        this.mockMvc.perform(patch(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isOk());

        //then
        verify(categoryService).updateCategory(Long.parseLong(categoryId), resource);
    }

    @DisplayName("4. 카테고리를 삭제한다")
    @Test
    void deleteCategoryTest() throws Exception {
        //given
        String categoryId = "1";
        String url = "/v1/categories/" + categoryId;

        //when
        this.mockMvc.perform(delete(url))
                .andExpect(status().isOk());

        //then
        verify(categoryService).deleteCategory(Long.parseLong(categoryId));
    }

}