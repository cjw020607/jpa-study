package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

//            //엔티티 객체를 대상으로 쿼리
//            List<Member> result=em.createQuery(
//                    "select m From Member m where m.username like '%kim%'",
//                    Member.class
//            ).getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member);
//
//            }

            //--------
            //Criteria 사용 준비
//            CriteriaBuilder cb=em.getCriteriaBuilder();
//            CriteriaQuery<Member> query=cb.createQuery(Member.class);
//
//            Root<Member> m=query.from(Member.class);

////            CriteriaQuery<Member> cq=query.select(m).where(cb.equal(m.get("username"),"kim"));
////            List<Member> resultList=em.createQuery(cq).getResultList();

//            //동적 쿼리 깔끔함
//            CriteriaQuery<Member> cq=query.select(m);
//            String username="dsafas";
//            if(username!=null){
//                cq=cq.where(cb.equal(m.get("username"),"kim"));
//            }
//            List<Member> resultList=em.createQuery(cq).getResultList();

            //--------
//            //네이티브 sql
//            em.createNativeQuery("select MEMBER_ID,city,street,zipcode,USERNAME from MEMBER").getResultList();
//            tx.commit();

            Member member=new Member();
            member.setUsername("member1");
            em.persist(member);

            //flush->commit,query

            //jpa 관련 (flush 됨)
            List<Member>resultList=em.createNativeQuery("select MEMBER_ID,city,street,zipcode,USERNAME from MEMBER").getResultList();

            // em.flush();
            // jpa와 관련 없기 때문에 강제 플러시 필요
            // 결과 0
            //dbconn.executeQuery("select * from member");

            for (Member member1 : resultList) {
                System.out.println("member1="+member1);

            }
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
        em.close();
       }
        emf.close();
    }
}


