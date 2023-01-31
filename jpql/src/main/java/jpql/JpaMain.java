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
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2=new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3=new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            //컬렉션을 페치 조인할 때 페이징 하면 안됨
            //String query="select t From Team t join fetch t.members";

            //다대일은 페이징의 문제 없음
            String query="select m From Member m join fetch m.team t";

            List<Team> result=em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(1)
                            .getResultList();


            for (Team team : result) {
                System.out.println("team = " + team.getName()+"|members="+team.getMembers().size());
                for( Member member: team.getMembers()){
                    System.out.println("->member = " + member);
                }
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


