package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext // Spring 컨테이너가 EntityManager 주입
    private EntityManager em;

    // 회원가입
    public Member save(Member member){
        em.persist(member);
        return member;
    }

    // 삭제
    public void delete(Member member){
        em.remove(member);
    }

    // 전체 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 회원 수
    public long count(){
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    // 회원 단일 조회 2
    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member); // member 값이 Null 일 수도 있다고 선언. ( Optional = null 도 값이 들어감 )
    }

    // 회원 단일 조회
    public Member find(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findByUsernameAndAgeGreaterThen(String username, int age){
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }
}
