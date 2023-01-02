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


        //try catch하는게 정석, but 실제는 spring이 해줘서 다 없어짐
        try {
//        //-------------------------2-2------------------------------
//        Member member = new Member();
//        //추가
////        member.setId(1L);
////        member.setName("HelloA ");
////        em.persist(member);
//
////        Member findMember=em.find(Member.class,1L); //select 쿼리문이 나감
////        System.out.println("findMember.id= "+findMember.getId());
////        System.out.println("findMember.name= "+findMember.getName());
//
//        //삭제
////        em.remove(findMember); //delete 쿼리문이 나감
////        em.persist(member);
//
//        //수정
////        findMember.setName("HelloJPA");//em.persist(member)할 필요 없음 java collection을 다루는 것처럼 설계됐기 때문
////        //transaction을 commit하는 시점에 변경됐는지 안됐는지 체크함 뭔가 바뀌면 commit직전에 update 쿼리 날림
//
////        //JPQL
////        List<Member> result=em.createQuery("select m from Member as m",Member.class)
////                .setFirstResult(5)
////                .setMaxResults(8)//5번부터 8개 가져옴
////                .getResultList();
////        //테이블 대상이 아니라 member객체를 다 가져옴
////        for(Member mem : result){
////            System.out.println("member.name = "+mem.getName());
////        }
//
//        tx.commit();

            //-----------------------------3-1,3-2---------------------------------------
//            //(1)
//            //비영속상태(jpa와 관련없음 db에 들어가지 않음 아무상태도 아님)
//            Member member=new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

//            //영속 상태
//            em.persist(member);//엔티티 메니저 안에있는 연속성 컨텍스트라는 데를 통해 member가 관리됨
//            //이때 db에 저장되지 않고 여기서 쿼리 날라가지 않음 이 뒤에서 쿼리 날라감
//            //1차 캐시에 저장됨
//
//            Member findMember=em.find(Member.class, 101L);
//            System.out.println("findMember.id= "+findMember.getId());
//            System.out.println("findMember.name= "+findMember.getName());
//            //select 쿼리가 안날라감. db에서 가져온게 아니라 1차 캐시에 있는거를 조회함.

//            //(2)
//            Member findMember1=em.find(Member.class, 101L);//앞에 코드 때문에 101 이미 저장돼있음. select 쿼리 나감.
//            // jpa가 db에서 가져와서 영속성 컨텍스트에 올림
//
//            Member findMember2=em.find(Member.class, 101L);//1차 캐시에서 가져옴. 쿼리 안나감

//            //(3)
//            Member member1=new Member(150L,"A");
//            Member member2=new Member(160L,"B");
//
//            em.persist(member1);
//            em.persist(member2);//db에 저장되지 않고 영속성 컨텍스트에 차곡차곡 쌓임
//            System.out.println("=================");//이 뒤에서 쿼리 나감

//            //(4)
//            Member member=em.find(Member.class, 150L);//select 쿼리 나감
//            member.setName("zzzzz");//em.persist하면 안됨. 값만 바꿨는데 update쿼리 날라감

//            tx.commit();//transaction을 commit하는 시점에 영속성 컨텍스트에 있는 애가 db에 쿼리가 날라감

//            //----------------------------3-3---------------------------------
//            Member member=new Member(200L,"member200");
//            em.persist(member);//영속성 컨텍스트에 member가 담김
//            //transaction이 commit되기 전까지는 쿼리 볼 수 없음
//            //쿼리 날라가는것을 미리 보고 싶으면 em.flush 강제로 호출시킴
//            em.flush();
//
//            System.out.println("======================");//이 전에 insert 쿼리 날라감
//            tx.commit();

//            //---------------------------3-4--------------------
//            Member member=em.find(Member.class, 150L);
//            member.setName("AAAAA");//select 쿼리만 나가고 update쿼리는 나가지 않음
//
//            //em.detach(member);//영속성 컨텍스트에서 더이상 관리하지 않음(특정 엔티티만 준영속 상태로 전환)
//
//            //em.clear();//영속성 컨텍스트를 완전히 초기화
//            //Member member2=em.find(Member.class, 150L);//영속성 컨텍스트 완전히 초기화 돼있기 때문에 새로 영속성 컨텍스트에 올림(쿼리 다시 나감)
//
//            //em.close();//영속성 컨텍스트를 종료
//
//            System.out.println("==================");
//            tx.commit();
            //-----------------------------5-1----------------------------
//            //------객체지향적x--------
//            //팀 저장
//            Team team=new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            //회원 저장
//            Member member=new Member();
//            member.setUsername("member1");
//            member.setTeamId(team.getId());//외래키 식별자를 직접다룸
//            em.persist(member);
//
//            //조회(객체 지향적이지 않음)
//            Member findMember=em.find(Member.class,member.getId());
//            Long findTeamId=findMember.getTeamId();
//            //연관관계가 없음
//            Team findTeam=em.find(Team.class,findTeamId);
//            tx.commit();

//            //------객체지향적------
//            //저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member=new Member();
//            member.setUsername("member1");
//            member.setTeam(team);//jpa가 알아서 team에서 pk값을 꺼내서 foreignkey 값으로 사용
//            em.persist(member);
//
//            //조회
//            Member findMember=em.find(Member.class, member.getId());//1차 캐시에서 가져와서 쿼리문 안나감
//            Team findTeam=findMember.getTeam();
//            System.out.println("findTeam="+findTeam.getName());
//
//            tx.commit();

//            //---------------------------------5-2------------------------------------
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member=new Member();
//            member.setUsername("member1");
//            member.setTeam(team);//jpa가 알아서 team에서 pk값을 꺼내서 foreignkey 값으로 사용
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            //조회
//            Member findMember=em.find(Member.class, member.getId());//1차 캐시에서 가져와서 쿼리문 안나감
//            List<Member> members=findMember.getTeam().getMembers();
//
//            for(Member m: members){
//                System.out.println("m= "+m.getUsername());
//            }
            //---------------------------------5-3----------------------
//            //연관관계의 주인이 아닌것(역방향)에 값을 넣음 (member 테이블의 team id가 null이 됨)
//            Member member=new Member();
//            member.setUsername("member1");
//            em.persist(member);
//
//            Team team=new Team();
//            team.setName("TeamA");
//            team.getMembers().add(member);//<-
//            em.persist(team);

            //연관관계의 주인에만 값을 넣음
            Team team=new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member=new Member();
            member.setUsername("member1");
            //(1),(2) 둘 중 하나로

            //(1)member기준으로 team넣기
            //member.setTeam(team);//<-

            em.persist(member);

            //(2)team기준으로 member넣기
            team.addMember(member);

//            team.getMembers().add(member);//양방향 연관관계일때는 양쪽에 다 셋팅해주기

            em.flush();
            em.clear();

            Team findTeam=em.find(Team.class,team.getId());//1차캐시에 있음
            List<Member> members=findTeam.getMembers();

            //team.getMembers().add(member),em.flush(),em.clear()가 주석일때 member1이 print되지 않음
            //team이 그대로 영속성 컨텍스트에 들어가 있음. team에 컬렉션이 아무것도 없음. 메모리에서도 아무것도 없이 가져옴
            System.out.println("===============");
            for(Member m:members){
                System.out.println("m= "+m.getUsername());
            }
            System.out.println("===============");

            tx.commit();

        }catch(Exception e){
            tx.rollback();
        }finally{
        em.close();
       }
        emf.close();
    }
}


