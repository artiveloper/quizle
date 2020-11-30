package kr.quizle.web.dto.auth;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInRequest {

    private String username;

    private String password;

    @Builder
    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
