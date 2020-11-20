package kr.quizle.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageMeta {

    private long totalCount;

    private int totalPage;

    private boolean isFirst;

    private boolean isLast;

    public PageMeta(Page<?> page) {
        this.totalCount = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
    }

}
