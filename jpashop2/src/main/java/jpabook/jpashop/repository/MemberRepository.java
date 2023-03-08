package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //spring이 생성한 엔티티 매니저를 주입시킴
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    //단건조
    public Member findOne(Long id){
        return em.find(Member.class, id); //타입,pk
    }

    public List<Member> findAll(){
       return em.createQuery("select m from Member m",Member.class).getResultList();
    }

    //이름 통해 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
