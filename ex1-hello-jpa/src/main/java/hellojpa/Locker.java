package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {
    @Id @GeneratedValue
    private Long id;

    private String name;

    //양방향(읽기전용)
    @OneToOne(mappedBy="locker") //member에 있는 locker
    private Member member;
}
