//package hellojpa;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
////다대다 한계 극복
//@Entity
//public class MemberProduct {
//
//    @Id @GeneratedValue
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name="MEMBER_ID")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name="PRODUCT_ID")
//    private Product product;
//
//    private int count;
//    private int price;
//
//    private LocalDateTime orderDateTime;
//
//}
