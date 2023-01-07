package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team=new Team();
            team.setName("teamA");
            em.persist(team);

            Member member=new Member();
            member.setUsername("member");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member m=em.find(Member.class,member.getId());

            System.out.println("m="+m.getTeam().getClass()); //프록시 아닌 진짜

            System.out.println("=============");
            System.out.println("teamName="+m.getTeam().getName());//초기화 하지 않고 진짜 값 출력
            System.out.println("=============");

            //jpql에서 문제

            //sql:select * from Member가 먼저 db에 나감
            //member를 보니 team이 eager여서 sql: select * from Team where TEAM_ID=xxxx 나감
//            List<Member> members=em.createQuery("select m from Member m",Member.class).getResultList();

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


