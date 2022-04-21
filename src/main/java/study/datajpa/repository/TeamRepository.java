package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public Team save(Team team){
        em.persist(team);
        return team;
    }

    // 삭제
    public void delete(Team team){
        em.remove(team);
    }

    // 전체 찾기
    public List<Team> findAll(){
        return em.createQuery("select t from Team t", Team.class)
                .getResultList();
    }

    // 단일 검색
    public Optional<Team> findById(Long id){
        Team team = em.find(Team.class, id);
        return Optional.ofNullable(team);
    }

    // 팀 수
    public long count(){
        return em.createQuery("select count(t) from Team t", Long.class)
                .getSingleResult();
    }

}
