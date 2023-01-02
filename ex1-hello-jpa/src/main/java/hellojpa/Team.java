//------------------5-1-------------------
package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
//    @Id @GeneratedValue
//    @Column(name="TEAM_ID")
//    private Long id;
//    private String name;
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
    //-----------------5-2----------------------
    //양방향
    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;

    //team에서 member로는 1대 N
    @OneToMany(mappedBy="team")//member의 팀에 걸려있음(반대쪽)
    private List<Member> members=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
    //--------------------------------5-3-------------------

    public void addMember(Member member){
        member.setTeam(this);
        members.add(member);
    }
}
