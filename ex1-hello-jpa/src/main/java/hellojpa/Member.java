package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
public class Member {
//    @Id
//    private Long id;
//    private String name;
//
//    public Member(){
//    }
//    public Member(Long id, String name){
//        this.id=id;
//        this.name=name;
//    }
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
    //---------------5-1--------------------
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    //객체지향적X
//    @Column(name="TEAM_ID")
//    private Long teamId;
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }

    //객체지향적
    @ManyToOne //member입장에서 member가 N team이 1
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

//    public void setTeam(Team team) {
//        this.team = team;
//    }
   // -------------------5-3------------------------
    //5-2에서 setTeam만 바꾸기
    //team.getMembers().add(member)를 까먹는 경우가 생기기 때문에 jpamain에서 안 쓸 경우 setTeam에 넣어주기
    public void setTeam(Team team){
        this.team=team;
        team.getMembers().add(this);
    }
}
