package toyproject.novelist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.novelist.domain.user.Member;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByName(String name);
}
