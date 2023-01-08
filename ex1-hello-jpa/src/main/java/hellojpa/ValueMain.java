package hellojpa;

public class ValueMain {
    public static void main(String[] args){
        //기본 타입
//        int a=10;
//        int b=a;//a 복사, 저장공간 따로 (기본 타입은 공유가 안됨)
//
//        a=20;
//
//        System.out.println("a="+a);
//        System.out.println("b="+b);
//        //결과 a=20, b=10

        //래퍼 클래스, 특수 클래스는 공유 가능하지만 변경x

        Integer a= 10;
        Integer b=a; //복사x 주소값이 넘어감

        //만약 a를 20으로 바꿀 수 있다면 a,b 둘 다 20 출력
        //하지만 변경 자체가 안되기 떄문에 부작용이 안일어남

        System.out.println("a="+a);
        System.out.println("b="+b);


    }
}

