package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Movie movie=new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            //영속성 컨텍스트에 있는 것을 다 제거하므로 1차캐시에 아무것도 남지 않음
            em.flush();
            em.clear();

            //조회할 때 movie와 item join해서 가져옴
            Movie findMovie=em.find(Movie.class,movie.getId());
            System.out.println("findMovie="+findMovie);

//            //명확하게 찔러서 조회할땐 괜찮은데 그 외에는 데이터가 있는지 없는지 테이블 다 찾아봐야함
//            Item item=em.find(Item.class,movie.getId());
//            System.out.println("Item="+item);


            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
        em.close();
       }
        emf.close();
    }
}


