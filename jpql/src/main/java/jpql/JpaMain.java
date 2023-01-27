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

            Team team=new Team();
            em.persist(team);

            Member member1=new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2=new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            em.persist(member2);

            em.flush();
            em.clear();

            //상태 필드 (경로 탐색의 끝이고 더이상 탐색 불가능)
            String query="select m.username From Member m";
            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
            }

            //-------
            //단일 값 연관 경로 (묵시적 내부 조인 발생, 탐색 가능 ex) m.team.name)
//            String query="select m.team From Member m";
//
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//
//            for (Team s : result) {
//                System.out.println("s = " + s);
//            }

            //컬렉션 값 연관 경로 (t.members.size까진 가능)
//            String query="select t.members From Team t";
//            //ex)t.members.username이 불가능 하기 때문에 원하는 속성 가져올 때 명시적 조인 사용
//            // query="select m.username From Team t join t.members m";
//
//            Collection result = em.createQuery(query, Collection.class)
//                    .getResultList();
//
//            for (Object o : result) {
//                System.out.println("o = " + o);
//            }

            //-------
            //t.members.size
//            String query="select t.members.size from Team t";
//
//            Integer result=em.createQuery(query, Integer.class)
//                            .getSingleResult();
//
//            System.out.println("result = " + result);

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


