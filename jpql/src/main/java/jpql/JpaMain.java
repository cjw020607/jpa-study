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

//            String query="select m From Member m";
//
//            //fetch join
//            //String query="select m From Member m join fetch m.team";
//            //영속성 컨텍스트에 이미 다 있음. proxy가 아닌 실제 데이터
//
//
//            List<Member> result=em.createQuery(query,Member.class)
//                            .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member.getUsername()+", "+member.getTeam().getName());
//                //회원1, 팀A(SQL) 팀A가 영속성 컨텍스트에 없기 때문에 회원1 전에 쿼리 나감
//                //회원2, 팀A(1차캐시) 팀A가 영속성 컨텍스트에 있기 때문에 쿼리 안나감
//                //회원3, 팀B(SQL) 팀B가 영속성 컨텍스트에 없기 때문에 쿼리 나감
//
//                //회원 100명 -> N + 1 문제 =>join petch로 해결
//            }

            //---------------
            //컬렉션 fetch join(연관된 엔티티도 함께 조회(즉시 로딩))
            String query="select t From Team t join fetch t.members";

            //중복 제거 (distinct)
            //String query="select distinct t From Team t join fetch t.members";

            //일반 조인 (연관된 엔티티 함께 조회하지 않음 (팀만 조회하고 회원 엔티티는 조회x))
            //String query="select t From Team t join t.members m";

            List<Team> result=em.createQuery(query, Team.class)
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


