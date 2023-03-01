package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    //x to one 의 기본형은 EAGER
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();

    //cascade 없을 때
    //persist(orderItemA)
    //persist(orderItemB)
    //persist(order)

    //cascade 있을 때
    //persist(order)만 하면 됨 (cascade가 persist를 전파함)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //order만 persist하면 delivery도 같이 persist됨
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;//주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [order, cancel]

    //연관관계 편의 메서드(양방향일때 쓰면 좋음)
    public void setMember(Member member){
        this.member=member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery=delivery;
        delivery.setOrder(this);
    }

//    public static void main(String[] args){
//        Member member=new Member();
//        Order order = new Order();
//
//        //연관관계 편의 매서드 쓰면 이 한줄이 줄어듦
//        //member.getOrders().add(order);
//        order.setMember(member);
//    }
}
