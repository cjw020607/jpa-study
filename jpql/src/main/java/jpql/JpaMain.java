package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;


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

                Member member = new Member();
                member.setUsername("member");
                member.setAge(10);

                member.setTeam(team);

                em.persist(member);

            em.flush();
            em.clear();

            //select절에서도 서브쿼리 가능(하이버네이트에서 지원)
            String query="select (select avg(m1.age) From Member m1) as avgAge from Member m left join Team t on m.username=t.name";

//            //from절의 서브 쿼리는 불가능 (조인으로 풀 수 있으면 풀어서 해결)
//            String query="select mm.age, mm.username "+
//                    " from (select m.age, m.username from Member m) as mm";


            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();


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


