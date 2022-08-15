package toyproject.novelist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.novelist.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
