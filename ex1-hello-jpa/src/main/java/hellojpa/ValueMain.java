package hellojpa;

public class ValueMain {
    public static void main(String[] args){

        int a=10;
        int b=10;

        System.out.println("a==b: "+(a==b)); //true

        Address address1= new Address("city","street","1000");
        Address address2= new Address("city","street","1000");

        System.out.println("adress1==address2: " +(address1==address2)); //false (참조값이 다름)
        System.out.println("adress1 equals address2: " +(address1.equals(address2))); //override 하면 true


    }
}

