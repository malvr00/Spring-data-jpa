package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsername(String username);   // 메소드 이름으로 쿼리 생성

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query(name = "Member.findByUsername")  // 생략 가능
    List<Member> findByUsername2(@Param("username") String username);

    // 동적 쿼리는 QueryDsl 사용 하는게 좋음. ( 권장 )
    @Query("select m from Member m where m.username = :username and m.age = :age")  // NameQuery 보다 사용성이 편리함. NameQuery 처럼 컴파일 단계에서 오류를 잡아줌
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // Query Dto 조회
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    // 반환 타입 여러가지로 할 수 있도록 지원해줌
    List<Member> findListByUsername(String username);   // 컬랙션
    Member findMemberByUsername(String username);   // 단건
    Optional<Member> findOptionalByUsername(String username);   // 단건 Optional


}
