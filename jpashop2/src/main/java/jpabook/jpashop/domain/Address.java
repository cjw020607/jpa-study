package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

//값 타입은 변경 불가능하게 설계해야 함
@Embeddable
@Getter
public class Address {
    
    private String city;
    private String street;
    private String zipcode;

    protected Address(){
    }

    public Address(String city, String street, String zipcode){
        this.city=city;
        this.street=street;
        this.zipcode=zipcode;
    }
}
