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

            System.out.println("m="+m.getTeam().getClass());//프록시

            System.out.println("=============");
            m.getTeam().getName();//team의 속성을 사용하는 시점(getName())에 프록시 객체 초기화, db에서 값 가져옴
            System.out.println("=============");




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


