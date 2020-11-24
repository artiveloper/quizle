package kr.quizle.dto;

import lombok.Data;

@Data
public class SingleResult<T> {

    T data;

    public SingleResult(T data) {
        this.data = data;
    }

}
