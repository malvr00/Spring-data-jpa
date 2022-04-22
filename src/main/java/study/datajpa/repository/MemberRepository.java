package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsername(String username);   // 메소드 이름으로 쿼리 생성

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query(name = "Member.findByUsername")  // 생략 가능
    List<Member> findByUsername2(@Param("username") String username);

    // 동적 쿼리는 QueryDsl 사용 하는게 좋음. ( 권장 )
    @Query("select m from Member m where m.username = :username and m.age = :age")  // NameQuery 보다 사용성이 편리함. NameQuery 처럼 컴파일 단계에서 오류를 잡아줌
    List<Member> findUser(@Param("username") String username, @Param("age") int age);
}
