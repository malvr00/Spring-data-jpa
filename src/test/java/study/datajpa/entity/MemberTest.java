package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testEntity(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamA);
        Member member3 = new Member("member3", 10, teamB);
        Member member4 = new Member("member4", 10, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m join fetch m.team t", Member.class).getResultList();

        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println(" -> member,team = " + member.getTeam());

        }

    }

    @Test
    public void jpaEventBaseEntity() throws Exception{
        // given
        Member member1 = new Member("member1");
        memberRepository.save(member1); // @PrePersist

        Thread.sleep(100);
        member1.setUsername("member2");

        em.flush(); // @PreUpdate
        em.clear();

        // when
        Member findMember = memberRepository.findById(member1.getId()).get();

        // then
        System.out.println("findMember.createDate = " + findMember.getCreatedDate());
        System.out.println("findMember.updateDate = " + findMember.getLastModifiedDate());
        System.out.println("findMember.getCreateBy = " + findMember.getCreateBy());
        System.out.println("findMember.getLastModifiedBy = " + findMember.getLastModifiedBy());
    }
}