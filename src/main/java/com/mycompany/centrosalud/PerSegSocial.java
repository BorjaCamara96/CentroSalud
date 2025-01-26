/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centrosalud;

/**
 *
 * @author Estudiante
 */
public class PerSegSocial {
    
    protected String nombre;
    protected int anyoNacimiento;    
    protected NumSeGSocial numSegSoc;
    
    public PerSegSocial(){
        
    }
    
    public PerSegSocial(String x, int y){
        if(this instanceof Medico){
            this.nombre = x;
            this.anyoNacimiento = y;
            numSegSoc = new NumSeGSocial(true);
        }
        else if(this instanceof Paciente){
            this.nombre = x;
            this.anyoNacimiento = y;
            numSegSoc = new NumSeGSocial(false);
        }
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String x){
        this.nombre = x;
    }
    
    public int getAnyoNac(){
        return this.anyoNacimiento;
    }
    
    public void setAnyoNac(int x){
        this.anyoNacimiento = x;
    }
    
    public long getNumSegSoc(){
        return this.numSegSoc.getNumSegSoc();
    }
    
    public void setNumSegSoc(){
        if(this instanceof Medico){
            this.numSegSoc.setNumSegSoc(true);
        }
        else if(this instanceof Paciente){
            this.numSegSoc.setNumSegSoc(false);
        }
    }   
}
