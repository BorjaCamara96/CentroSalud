/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centrosalud;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Estudiante
 */
public class Paciente extends PerSegSocial{
    
    private ArrayList <String> enfermedades = new ArrayList<>();
    private static String[] listadoEnfermedades = {"Alzheimer", "Artritis", "Asma",
        "Diabetes", "Endometriosis", "Cardiopatias", "Narcolepsia", "Parkinson", "Hipertensión"};
    private Medico medicoAsignado;
    
    public Paciente(){
        
    }
    
    public Paciente(String x, int y){
        super(x, y);
    }
    
    public Paciente(String x, int y, Medico z){
        super(x, y);
        this.medicoAsignado = z;
    } 
    
    public Medico getMedicoAsignado(){
        return this.medicoAsignado;
    }
    
    public void setMedicoAsignado(Medico x){
        this.medicoAsignado = x;
    }
    
    public void setMedicoAsignado(int x){
        this.medicoAsignado = null;
    }
    
    public void añadirEnfermedad(){
        Scanner sc = new Scanner(System.in);
        Boolean bool = true;
        for(int i=0;i<listadoEnfermedades.length;i++){
            System.out.println(i+") "+listadoEnfermedades[i]);
        }
        int option = sc.nextInt();
        if(option >= 0 && option < listadoEnfermedades.length){
            for(String enfermedad:enfermedades){
                if(enfermedad.equals(listadoEnfermedades[option])){
                    bool = false;
                }
            }
            if(bool){
                this.enfermedades.add(listadoEnfermedades[option]);
            }
            else{
                System.out.println("La enfermedad ya esta registrada en este paciente.");
            }
        }
        else{
            System.out.println("Ha introducido una opcion fuera del rango.");
        }
    }
    
    public void borrarEnfermedad(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce que enfermedad quiere eliminar:");
        for(String enfermedad:enfermedades){
            System.out.println(enfermedades.indexOf(enfermedad)+") "+enfermedad);
        }
        int option = sc.nextInt();
        if(option >= 0 && option < enfermedades.size()){
            this.enfermedades.remove(option);
        }
        else{
            System.out.println("Ha introducido una opcion fuera del rango.");
        }
    }
    
    public ArrayList<String> mostrarEnfermedades(){
        return this.enfermedades;
    }
    
}
