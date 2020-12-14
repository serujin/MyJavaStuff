/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;

/**
 *
 * @author Seruji
 */
public class Client implements Serializable {
    private int age;

    public Client(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }        
}
