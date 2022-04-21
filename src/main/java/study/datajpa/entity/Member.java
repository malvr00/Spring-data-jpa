package study.datajpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data   // 실무에서 Setter 사용 비추 ( 연습이라 Setter 사용 )
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;

    // 엔티티를 만들 때 기본 생성자 필요해서 사용. jpa 에서 proxy 사용 할때 필요함. ( private 은 사용 못함 )
    protected Member() {}

    public Member(String username) {
        this.username = username;
    }
}
