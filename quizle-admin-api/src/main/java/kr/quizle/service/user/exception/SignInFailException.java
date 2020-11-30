package kr.quizle.service.user.exception;

import kr.quizle.global.error.BusinessException;

public class SignInFailException extends BusinessException {

    public SignInFailException() {
        super("signInFail");
    }

}
