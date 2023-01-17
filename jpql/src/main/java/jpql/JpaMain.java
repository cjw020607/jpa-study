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

//            //반환 타입 명확할 떄
//            TypedQuery<Member> query1=em.createQuery("select m from Member m",Member.class);
//            TypedQuery<String> query2=em.createQuery("select m.username from Member m",String.class);
//            //반환 타입 명확하지 않을 때
//            Query query3=em.createQuery("select m.username, m.age from Member m");

            //----------
//            TypedQuery<Member> query=em.createQuery("select m from Member m",Member.class);
//            //결과가 하나 이상일 때
//            List<Member> resultList = query.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }
////            //결과가 정확히 하나만 있어야 함
////            Member result=query.getSingleResult();
////            System.out.println("result= "+result);
            //----------
            TypedQuery<Member> query=em.createQuery("select m from Member m where m.username=:username",Member.class);
            query.setParameter("username","member1");
            Member singleResult = query.getSingleResult();
            System.out.println("singleResult= "+singleResult.getUsername());


        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
        emf.close();
    }
}


