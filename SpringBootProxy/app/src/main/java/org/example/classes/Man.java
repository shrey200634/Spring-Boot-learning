package org.example.classes;

public class Man implements person{
    private String name ;
    private int age ;
    private String city;
    private String country;

    public Man (String name , int age , String city , String country){
        this.name =name ;
        this.age =age;
        this.city=city;
        this.country=country;

    }
    public String getName( ){
        return this.name ;
    }

    @Override
    public void introduce(String name) {
        System.out.println("my name is " + this.name );

    }

    @Override
    public void sayAge(int age) {
        System.out.println("my age is " + this.age );

    }

    @Override
    public void sayWhereRFrom(String city, String country) {
        System.out.println("my city is " +this.city);
        System.out.println("my country is " + this.country);

    }
}
