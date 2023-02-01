package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Team teamA=new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB=new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1=new Member();
            member1.setUsername("회원1");
            member1.setAge(0);
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2=new Member();
            member2.setUsername("회원2");
            member2.setAge(0);
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3=new Member();
            member3.setUsername("회원3");
            member3.setAge(0);
            member3.setTeam(teamB);
            em.persist(member3);

            //flush 자동 호출
            //벌크 연산은 db에만 반영함
            //영속성 컨텍스트에는 age 0인데 db는 age 20임
            int resultCount=em.createQuery("update Member m set m.age=20")
                            .executeUpdate();

            System.out.println("resultCount = " + resultCount);

            System.out.println("member1.getAge() = "+member1.getAge());
            System.out.println("member2.getAge() = "+member2.getAge());
            System.out.println("member3.getAge() = "+member3.getAge());

            //영속성 컨텍스트 초기화 후 db에서 깔끔하게 새로 가져옴
//            em.clear();
//            Member findMember=em.find(Member.class, member1.getId());
//            System.out.println("findMember.getAge() = " + findMember.getAge());

            tx.commit();

        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
        emf.close();
    }
}


