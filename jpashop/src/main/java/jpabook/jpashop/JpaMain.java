package jpabook.jpashop;

//import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();

        tx.begin();
        try {

            Order order=new Order();
            //(1)
            order.addOrderItem(new OrderItem());
            em.persist(order);

//            //(2)
//            OrderItem orderItem=new OrderItem();
//            orderItem.setOrder(order);
//            em.persist(orderItem);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}