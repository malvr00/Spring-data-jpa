package study.datajpa.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data   // 실무에서 Setter 사용 비추 ( 연습이라 Setter 사용 )
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username","age"})
@NamedQuery(
        name = "Member.findByUsername2",
        query="select m from Member m where m.username = :username"
)
@NamedEntityGraph(  // JPA 표준 스팩의 EntityGraph
        name = "Member.all",
        attributeNodes = @NamedAttributeNode("team")
)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    // 엔티티를 만들 때 기본 생성자 필요해서 사용. jpa 에서 proxy 사용 할때 필요함. ( private 은 사용 못함 )
//    protected Member() {}

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
