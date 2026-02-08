package org.example.classes;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;



public class personInvocationHandler implements InvocationHandler
{
    private person person;

    public personInvocationHandler(person person){
        this.person=person;

    }

    @override
    public Object invoke(Object proxy , Method method , Object[] args ){
        System.out.println("hi");
        return null;



    }
}
