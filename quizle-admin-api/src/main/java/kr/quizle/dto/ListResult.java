package kr.quizle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ListResult<T> {

    private List<T> data;
    private PageMeta pageMeta;

}
