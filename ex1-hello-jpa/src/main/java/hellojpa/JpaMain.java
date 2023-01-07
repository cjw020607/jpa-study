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

            Child child1=new Child();
            Child child2=new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            //원래 다 해줘야 함
//            em.persist(parent);
//            em.persist(child1);
//            em.persist(child2);

            //cascade 할 경우 parent만 persist 해도 child persist됨
            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent=em.find(Parent.class,parent.getId());

            //자식 엔티티를 컬렉션에서 제거, orphanRemoval이 true이므로 delete됨
            findParent.getChildList().remove(0);

            //----------
            //Parent 제거될 때 자식도 함게 delete 됨
//            em.remove(findParent); 

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


