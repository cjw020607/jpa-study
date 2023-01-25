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
                member.setUsername("teamA");
                member.setAge(10);
                member.setType(MemberType.ADMIN);

                member.setTeam(team);

                em.persist(member);

            em.flush();
            em.clear();

            //패키지명 포함해야함
            String query= "select m.username, 'HELLO', true From Member m "+
                    "where m.type=jpql.MemberType.ADMIN";

            List<Object[]> result = em.createQuery(query)
                    .getResultList();

            //-------------
            //패키지명 포함안하고
//            String query= "select m.username, 'HELLO', true From Member m "+
//                    "where m.type=:userType";
//
//            List<Object[]> result = em.createQuery(query)
//                    .setParameter("userType",MemberType.ADMIN)
//                    .getResultList();

            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
            }

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


