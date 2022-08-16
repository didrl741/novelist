package toyproject.novelist.config.auth.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.user.Member;
import toyproject.novelist.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member principal = memberRepository.findByEmail(email).orElse(null);

        if (principal == null) {
            throw new UsernameNotFoundException(email);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(principal.getEmail())
                .password("")
                .roles(principal.getRole().toString())
                .build();
    }
}
