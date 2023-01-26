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
                member.setUsername(null);
                //member.setUsername("관리자");
                member.setAge(10);
                member.setType(MemberType.ADMIN);

                member.setTeam(team);

                em.persist(member);

            em.flush();
            em.clear();

            //기본 case 식
            String query=
                    "select "+
                            "case when m.age<=10 then '학생요금' "+
                            "     when m.age>=60 then '경로요금' "+
                            "     else '일반요금' "+
                            "end "+
                    "from Member  m";
            //------
            //coalesece: 하나씩 조회해서 null이 아니면 반환
//            String query="select coalesce(m.username,'이름 없는 회원') as username " +
//                    "from Member m";
            //-------
            //nullif: 두 값이 같으면 null 반환, 다르면 첫번째 값 반환
            //username이 관리자일때
//            String query="select nullif(m.username,'관리자') as username "+
//                    "from Member m";

            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
                
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


