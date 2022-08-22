package toyproject.novelist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User duplicateName(String name) {

        return userRepository.findByName(name).orElse(null);
    }

    public User duplicateEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
    }

    public void join(User user) {

        userRepository.save(user);
    }
}
