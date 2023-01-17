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

            Member member=new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            //엔티티 프로젝션
//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();
//
//            Member findMember=result.get(0);
//            findMember.setAge(20);

            //---------
            //엔티티 프로젝션
//            //예측 어려움
//            List<Team> result=em.createQuery("select m.team from Member m", Team.class)
//                    .getResultList();
            //예측 가능(join 명시하는 것이 좋음)
//            List<Team> result=em.createQuery("select t from Member m join m.team t", Team.class)
//                            .getResultList();
            //----------
//            //임베디드 타입 프로젝션(어디 소속인지 엔티티를 정해줘야 함)
//            em.createQuery("select o.address from Order o",Address.class)
//                            .getResultList();
            //---------
//            //스칼라 타입 프로젝션
//            em.createQuery("select m.username, m.age from Member m")
//                    .getResultList();
            //----------
            //Query 타입으로 조회
//            List resultList = em.createQuery("select m.username,m.age from Member m", Member.class)
//                      .getResultList();
//            Object o=resultList.get(0);
//            Object[] result=(Object[]) o;
//            System.out.println("username="+result[0]);
//            System.out.println("age="+result[1]);
            //---------
            //Object[] 타입으로 조회
//            List<Object[]> resultList = em.createQuery("select m.username,m.age from Member m")
//                        .getResultList();
//
//            Object[] result=resultList.get(0);
//            System.out.println("username="+result[0]);
//            System.out.println("age="+result[1]);
            //-------
            //new 명령어로 조회
            //마치 생성자 호출 하듯
//            List<MemberDTO> result= em.createQuery("select new jpql.MemberDTO(m.username,m.age) from Member m",MemberDTO.class)
//                          .getResultList();
//
//            MemberDTO memberDTO =result.get(0);
//            System.out.println("memberDTO="+memberDTO.getUsername());
//            System.out.println("memberDTO="+memberDTO.getAge());

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


