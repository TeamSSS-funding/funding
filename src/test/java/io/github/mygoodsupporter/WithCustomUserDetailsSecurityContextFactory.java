package io.github.mygoodsupporter;


import io.github.mygoodsupporter.domain.user.Authority;
import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.security.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;

public class WithCustomUserDetailsSecurityContextFactory
implements WithSecurityContextFactory<WithCustomUserDetails> {

    @Override
    public SecurityContext createSecurityContext(WithCustomUserDetails customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = new User();
        user.setId(customUser.id());
        user.setUsername(customUser.name());
        user.setEmail(customUser.email());
        user.setAuthorities(Collections.singletonList(new Authority(customUser.username(), "ROLE_USER")));

        UserDetails principal = new UserDetails(user);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
