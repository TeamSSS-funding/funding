package io.github.mygoodsupporter.security;

import io.github.mygoodsupporter.dao.MemberDAO;
import io.github.mygoodsupporter.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    private MemberDAO memberdao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberdao.getMemberById(username);
        UserDetails userDetails = new MemberDetails(member);
        return userDetails;
    }
}

