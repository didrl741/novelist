package toyproject.novelist.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import toyproject.novelist.domain.user.Member;
import toyproject.novelist.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class PrincipleDetailService implements UserDetailsService {

    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email).orElse(null);

        if (member == null) {
            return null;
        }

        return new PrincipalDetails(member);
    }
}
