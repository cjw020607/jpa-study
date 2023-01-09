package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Address address=new Address("city","street","1000");

            //member1, member2 같은 주소 사용
            Member member= new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            //부작용
//            Member member2= new Member();
//            member2.setUsername("member2");
//            member2.setHomeAddress(address);
//            em.persist(member2);
//
//            //member1만 바꾸고 싶은데 결과는 member2도 바뀜
//            member.getHomeAddress().setCity("newCity");

            //---------
            //부작용을 해결하려면 값을 복사해서 사용해야 함
//            Address copyAddress=new Address(address.getCity(), address.getStreet(), address.getZipcode());
//
//            Member member2= new Member();
//            member2.setUsername("member2");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);
//
//            //member1만 바뀜
//            member.getHomeAddress().setCity("newCity");

            //---------
            //불변 객체 만들기 위해 setter 없다는 가정 하에 값 바꾸기 (값을 통으로 바꿈)
            Address newAddress=new Address("NewCity", address.getStreet(), address.getZipcode());
            member.setHomeAddress(newAddress);

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


