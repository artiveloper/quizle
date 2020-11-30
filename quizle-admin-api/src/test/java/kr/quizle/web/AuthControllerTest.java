package kr.quizle.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.quizle.MockMvcTest;
import kr.quizle.domain.User;
import kr.quizle.domain.UserRepository;
import kr.quizle.security.JwtTokenProvider;
import kr.quizle.web.dto.auth.SignInRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("1. 로그인에 성공한다")
    @Test
    void 로그인() throws Exception {
        //given
        String username = "admin";
        String password = "password";

        User admin = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(admin);

        SignInRequest signInRequest = SignInRequest.builder()
                .username(username)
                .password(password)
                .build();

        //when, then
        mockMvc.perform(
                post("/v1/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andDo(print());

    }

}