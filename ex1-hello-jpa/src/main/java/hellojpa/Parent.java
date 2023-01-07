package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //cascade, orphanremoval 둘다 활성화 할 때 부모 엔티티를 통해서 자식의 생명 주기 관리
    @OneToMany(mappedBy="parent",cascade= CascadeType.ALL,orphanRemoval = true) //childList에서 빠진 것은 삭제(delete)
    private List<Child> childList=new ArrayList<>();

    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

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
}
