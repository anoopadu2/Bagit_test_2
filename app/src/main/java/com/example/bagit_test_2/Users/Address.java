package com.example.bagit_test_2.Users;

public class Address {
    public String add_line_1;
    public String add_line_2;
    public String City;
    public String State;
    public String PinCode;

    public void Show_Address(){
        System.out.println("USER ADDRESS : ");
        System.out.println(this.add_line_1 + " , " + this.add_line_2);
        System.out.println(this.City);
        System.out.println(this.State);
        System.out.println(this.PinCode);
        System.out.println();

    }

    public String get_address(){
        String add = add_line_1 + ", " + add_line_2 + ", " + City + ", " + State +", " +  PinCode ;
        return add;
    }


}
