package kr.quizle.security;

import kr.quizle.service.user.UserService;
import kr.quizle.service.user.exception.SignInFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(username);

            boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
            if (!matches) {
                throw new BadCredentialsException("비밀번호를 오류");
            }

        } catch (Exception e) {
            throw new SignInFailException();
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
