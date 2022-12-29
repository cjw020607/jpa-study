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
            //-----------------------4-5------------------
            tx.commit();
//            Order order=em.find(Order.class,1L);
//            Long memberId=order.getMemberId();
//            Member member=em.find(Member.class, memberId);
//
//            Member findMember=order.getMember();//위 세줄을 한번에


//            Order order=new Order();
//            order.addOrderItem(new OrderItem());

//            Book book=new Book();
//            book.setName("JPA");
//            book.setAuthor("김영한");
//            em.persist(book);

        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}