package kr.quizle.web.dto.global;

import lombok.Data;

@Data
public class SingleResult<T> {

    T data;

    public SingleResult(T data) {
        this.data = data;
    }

}
