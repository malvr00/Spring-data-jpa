package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    // 하나의 규칙있음.
    // Impl 을 꼭 붙여야한다. 그리고 interface 의 이름과 같아야함. ex ) MemberRepositoryImpl
    // 그럼 자동으로 spring data jpa 에서 찾아서 매핑 해줌

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
