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

            Member member1=new Member();
            member1.setUsername("관리자1");
            em.persist(member1);

            Member member2=new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            em.flush();
            em.clear();

            //concat
            //String query="select concat('a','b') From Member m";
            //String query="select 'a' || 'b' From Member m";

//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            //-------
            //substring (몇 번째부터 몇 개 잘라냄)
//            String query="select substring(m.username,2,3) From Member m";
//
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            //-------
            //locate (abcdefg에서 de 찾음)
            //String query="select locate('de','abcdefg') From Member m";
//            List<Integer> result = em.createQuery(query, Integer.class)
//                    .getResultList();
//
//            for (Integer s : result) {
//                System.out.println("s = " + s);
//            }

            //-------
            //size (콜렉션의 크기)
            //String query="select size(t.members) From Team t";
//            List<Integer> result = em.createQuery(query, Integer.class)
//                    .getResultList();
//
//            for (Integer s : result) {
//                System.out.println("s = " + s);
//            }

            //---------
            //사용자 정의 함수 호출
            String query="select function('group_concat',m.username) From Member m";

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


