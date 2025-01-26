/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centrosalud;

import java.time.Year;

/**
 *
 * @author Estudiante
 */
public class Medico extends PerSegSocial{
    
    private static final String[] listaEspecialidades = {"Medico de familia", "Pediatra", "Geriatra"};
    private String especialidad;
    
    public Medico(){
    }
    
    public Medico(String x, int y, int z){
        super(x, y);
        this.especialidad = Medico.listaEspecialidades[z];
    }
    public String getEspecialidad(){
        return this.especialidad;
    }
    
    public void setEspecialidad(int x){
        if(x >= 0 && x < listaEspecialidades.length){
            this.especialidad = Medico.listaEspecialidades[x];
        }
        else{
            System.out.println("Ha introducido un valor fuera de rango.");
        }
    }
    
    public static String[] getListaEspecialidades(){
        return Medico.listaEspecialidades;
    }
   
}
