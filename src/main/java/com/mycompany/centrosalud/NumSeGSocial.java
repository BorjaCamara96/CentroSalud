/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centrosalud;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Estudiante
 */
public class NumSeGSocial {
    
    private static ArrayList<Long> numerosOcupados = new ArrayList<Long>();
    private long numeroSegSoc;
    
    public NumSeGSocial(){
    } 
    
    public NumSeGSocial (Boolean x){
        Random rm = new Random();
        long aux;
        if(x == true){
            do{
                aux = rm.nextLong(0,999999);
            } while(!this.comprobarNumeros(aux));
            this.numeroSegSoc = aux;
            NumSeGSocial.numerosOcupados.add(this.numeroSegSoc);
        }
        else if(x == false){
            do{
                aux = rm.nextLong(0,99999);
            } while(!this.comprobarNumeros(aux));
            this.numeroSegSoc = aux;
            NumSeGSocial.numerosOcupados.add(this.numeroSegSoc);
        }
    }
        
    public long getNumSegSoc(){
        return numeroSegSoc;
    }

    public void setNumSegSoc(Boolean x) {
        int index = 0;
        for(Long numero:numerosOcupados){
            if(this.numeroSegSoc == numero){
                index = NumSeGSocial.numerosOcupados.indexOf(numero);
            }
        }
        NumSeGSocial.numerosOcupados.remove(index);
        Random rm = new Random();
        long aux = 0;
        if(x == true){
            do{
                aux = rm.nextLong(0,999999);
            } while(!this.comprobarNumeros(aux));
        }
        else if(x == false){
            do{
                aux = rm.nextLong(0,99999);
            } while(!this.comprobarNumeros(aux));
        }
        this.numeroSegSoc = aux;
        NumSeGSocial.numerosOcupados.add(this.numeroSegSoc);
    }     
    
    public final boolean comprobarNumeros(long x){
        Boolean bool = true;
        for(Long numero:numerosOcupados){
            if(x == numero){
                bool = false;
            }
        }
        return bool;
    }
}
