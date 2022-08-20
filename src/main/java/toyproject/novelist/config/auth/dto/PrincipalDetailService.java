package toyproject.novelist.config.auth.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User principal = userRepository.findByEmail(email).orElse(null);

        if (principal == null) {
            throw new UsernameNotFoundException(email);
        }

        UserDetails result = org.springframework.security.core.userdetails.User.builder()
                .username(principal.getEmail())
                .password("")
                .roles(principal.getRole().toString())
                .build();

        return result;
    }
}
