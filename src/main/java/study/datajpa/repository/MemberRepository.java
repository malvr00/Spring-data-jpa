package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsername(String username);   // 메소드 이름으로 쿼리 생성

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

}
