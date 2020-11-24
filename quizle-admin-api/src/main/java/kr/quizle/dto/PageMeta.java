package kr.quizle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageMeta {

    private long totalCount;

    private int totalPage;

    @JsonProperty("isFirst")
    private boolean isFirst;

    @JsonProperty("isLast")
    private boolean isLast;

    public PageMeta(Page<?> page) {
        this.totalCount = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
    }

}
