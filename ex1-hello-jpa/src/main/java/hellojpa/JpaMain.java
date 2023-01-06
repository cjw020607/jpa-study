package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member member=new Member();
            member.setUsername("member");

            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember=em.find(Member.class,member.getId());
//            System.out.println("findMember.id="+findMember.getId());
//            System.out.println("findMember.username="+findMember.getUsername());

            //--------------
            //hibernate가 만들어낸 가짜 class(proxy class)
//            Member findMember=em.getReference(Member.class,member.getId());//이것만 돌리면 쿼리가 안나감(findMember가 실제 사용되는 시점에 쿼리 나감)
//            //id 값이 있기 때문에 쿼리 없이 그냥 가져올 수 있음
//            System.out.println("findMember.id="+findMember.getId());
//            //프록시 객체가 실제 엔티티로 바뀌는 것이 아님
//            System.out.println("before findMember="+findMember.getClass());
//            //username은 db에 있기 때문에 이 시점에 쿼리 날림
//            System.out.println("findMember.username="+findMember.getUsername());
//            System.out.println("after findMember="+findMember.getClass());

            //--------------
            //영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 프록시가 아닌 실제 엔티티가 반환됨
//            Member m1=em.find(Member.class,member.getId());
//            System.out.println("m1="+m1.getClass());
//
//            Member reference=em.getReference(Member.class,member.getId());
//            System.out.println("reference="+reference.getClass());
//
//            System.out.println("a==a:"+(m1==reference));

            //---------------
            //한번 프록시 조회되면 em.find해도 프록시 반환함
//            Member refMember=em.getReference(Member.class,member.getId());
//            System.out.println("refMember="+refMember.getClass()); //proxy
//
//            Member findMem=em.find(Member.class,member.getId());
//            System.out.println("findMem="+findMem.getClass());
//
//            System.out.println("refMember==findMem:"+(refMember==findMem));

            //--------------
//            Member refMember=em.getReference(Member.class,member.getId());
//            System.out.println("refMember="+refMember.getClass());
//
//            //영속성 컨텍스트 더 이상 관리 안함
//            em.detach(refMember);
//            //em.close();
//            //em.clear();
//
//            refMember.getUsername();//could not initialize proxy(오류)

            //-----------
            Member refMember=em.getReference(Member.class,member.getId());
            System.out.println("refMember="+refMember.getClass());
            //refMember.getUsername();//강제 초기화

            //초기화 여부 확인
            //System.out.println("isLoaded:"+emf.getPersistenceUnitUtil().isLoaded(refMember));

            //강제 초기화
            //Hibernate.initialize(refMember);


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


