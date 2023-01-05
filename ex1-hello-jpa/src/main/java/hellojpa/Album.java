package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") //default는 entity(class)명
public class Album extends Item{

    private String artist;
}
