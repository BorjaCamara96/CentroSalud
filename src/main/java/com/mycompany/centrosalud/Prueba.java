/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centrosalud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Estudiante
 */
public class Prueba {
    
    static ArrayList<CentroSalud> centros = new ArrayList<CentroSalud>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int optionCentro, option, optionSecun, indexCentro;
        Boolean bool = null;
        do{
            System.out.println("""
                Introduce una opción del menú:
                1) Crear un centro.
                2) Modificar un centro.
                3) Eliminar un centro.
                4) Ver lista de centros.
                5) Acceder a centro.
                0) Salir de aplicacion.       
                """);
            optionCentro = sc.nextInt();
            switch (optionCentro){
                case 1:
                    crearCentro();
                    break;
                case 2:
                    modificarCentro();
                    break;
                case 3:
                    borrarCentro();
                    break;
                case 4:
                    listarCentros();
                    break;
                case 5:
                    if(!centros.isEmpty()){
                        do{
                            //bool = true;
                            System.out.println("Introduce que centro quiere acceder:");
                            listarCentros();
                            indexCentro = sc.nextInt();
                            if(indexCentro < 0 || indexCentro >= centros.size()){
                                System.out.println("Ha introducido una opcion fuera de rango.");
                                bool = false;
                            }
                            else if(indexCentro >=0 && indexCentro < centros.size()){
                                bool = true;
                            }
                        } while(!bool);
                        do{
                            System.out.println("\n### "+centros.get(indexCentro).getNombreCentro()+" - "+centros.get(indexCentro).getAnyo()+" ###\n");
                            System.out.println("""
                                Introduce una opción del menú:
                                1) Registrar.
                                2) Modificar.
                                3) Eliminar.
                                4) Listar.
                                0) Salir de centro.
                                """);
                            option = sc.nextInt();
                            switch (option){
                                case 1:
                                    System.out.println("""
                                    Introduce una opción del menú:
                                    1) Registrar paciente:
                                    2) Registrar medico:
                                    3) Registrar cita:
                                    0) Salir:
                                    """);
                                    optionSecun = sc.nextInt();
                                    switch (optionSecun){
                                        case 1:
                                            centros.get(indexCentro).crearPaciente();
                                            break;
                                        case 2:
                                            centros.get(indexCentro).crearMedico();
                                            break;
                                        case 3:
                                            centros.get(indexCentro).crearCita();
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Ha introducido un valor fuera de rango.");
                                            break;
                                    }
                                    break;
                                case 2:
                                    System.out.println("""
                                    Introduce una opción del menú:
                                    1) Modifica paciente:
                                    2) Modifica medico:
                                    0) Salir:
                                    """);
                                    optionSecun = sc.nextInt();
                                    switch (optionSecun){
                                        case 1:
                                            centros.get(indexCentro).modificarPaciente();
                                            break;
                                        case 2:
                                            centros.get(indexCentro).modificarMedico();
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Ha introducido un valor fuera de rango.");
                                            break;
                                    }
                                    break;
                                case 3:
                                    System.out.println("""
                                    Introduce una opción del menú:
                                    1) Elimina paciente:
                                    2) Elimina medico:
                                    3) Elimina cita:
                                    0) Salir:
                                    """);
                                    optionSecun = sc.nextInt();
                                    switch (optionSecun){
                                        case 1:
                                            centros.get(indexCentro).borrarPaciente();
                                            break;
                                        case 2:
                                            centros.get(indexCentro).borrarMedico();
                                            break;
                                        case 3:
                                            centros.get(indexCentro).borrarCita();
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Ha introducido un valor fuera de rango.");
                                            break;
                                    }
                                    break;
                                case 4:
                                    System.out.println("""
                                    Introduce una opción del menú:
                                    1) Listar citas segun fecha.
                                    2) Listas personas dadas de alta en centro.
                                    3) Detallar datos de paciente.
                                    4) Listar medicos de una especialidad.
                                    5) Listas pacientes con un medico asignado.
                                    6) Citas atendidas y pendientes de paciente.
                                    7) Listar citas pendientes.
                                    0) Salir:
                                    """);
                                    optionSecun = sc.nextInt();
                                    switch (optionSecun){
                                        case 1:
                                            centros.get(indexCentro).listarCitasFecha();
                                            break;
                                        case 2:
                                            centros.get(indexCentro).listarPersonas();
                                            break;
                                        case 3:
                                            centros.get(indexCentro).listasDatosPaciente();
                                            break;
                                        case 4:
                                            centros.get(indexCentro).listarMedicosEspecialidad();
                                            break;
                                        case 5:
                                            centros.get(indexCentro).listarPacientesMedico();
                                            break;
                                        case 6:
                                            centros.get(indexCentro).contarCitasPaciente();
                                            break;
                                        case 7:
                                            centros.get(indexCentro).listarCitas();
                                            break;
                                        case 0:
                                            break;
                                        default:
                                        System.out.println("Ha introducido un valor fuera de rango.");
                                        break;
                                    }
                                    break;
                            }
                        } while(option != 0);
                    }
                    else{
                        System.out.println("No hay centros creados.");
                    }
                    break; 
                case 0: 
                break;
                default:
                    System.out.println("Ha introducido un valor fuera de rango.");
                    break;
            }
        }while(optionCentro != 0);
    }
    
    public static void crearCentro(){
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        LocalDate dt = LocalDate.now();
        int anyoActual = Integer.parseInt(formatter.format(dt));
        int optionCentro = 0, option = 0, optionSecun = 0, optionAnyo = 0, indexCentro;
        String nombreCentro;
        int anyoGestion = anyoActual;
        Boolean bool;
        do{
            System.out.println("Introduce un nombre para centro:");
            nombreCentro = sc.nextLine();
            if(nombreCentro.matches("^[a-zA-Z\\s]*$") && !nombreCentro.isBlank()) bool = true;
            else{
                System.out.println("Ha introducido caracteres no validos para nombre. Solo letras.");
                bool = false;
            }
        } while(!bool);
        do{
            System.out.println("Introduce anyo actual o posterior de la gestion:\n"
                    + "0) "+anyoActual+"\n"
                    + "1) "+(anyoActual+1));
            optionAnyo = sc.nextInt();
            if(optionAnyo == 0 || optionAnyo == 1){
                if(optionAnyo == 0) anyoGestion = anyoActual;
                else if(optionAnyo == 1) anyoGestion = (anyoActual +1);
                bool = true;
                for(CentroSalud x:centros){
                    if(x.getNombreCentro().equals(nombreCentro) && x.getAnyo() == anyoGestion){
                        bool = false;
                        System.out.println("Ya hay un centro con el mismo nombre y mismo anyo de gestion.");
                    }
                }   
            }
            else{
                System.out.println("Ha introducido una opcion fuera del rango.");
                bool = false;
            }
        } while(!bool);
        centros.add(new CentroSalud(nombreCentro, anyoGestion));
    }
    public static void modificarCentro(){
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        LocalDate dt = LocalDate.now();
        int anyoActual = Integer.parseInt(formatter.format(dt));
        int optionAnyo = 0, indexCentro;
        String nombreCentro;
        int anyoGestion = anyoActual;
        Boolean bool;
        if(!centros.isEmpty()){
            int optionModCentro = 0;
            do{
                System.out.println("Introduce que centro quiere modificar:\n"
                        + "Nº CENTRO | NOMBRE CENTRO | ANYO GESTION");
                for(CentroSalud x:centros){
                    System.out.println(centros.indexOf(x)+") "+x.getNombreCentro()+" - "+x.getAnyo());
                }
                indexCentro = sc.nextInt();
                if(indexCentro >= 0 || indexCentro >= centros.size()){
                    bool = true;
                    System.out.println("Introduce una opcion:\n"
                    + "1) Modificar nombre.\n"
                    + "2) Modificar anyo.\n"
                    + "0) Salir de esta opcion.");
                    optionModCentro = sc.nextInt();
                    if(optionModCentro < 0 || optionModCentro > 2){
                        System.out.println("Ha introducido una opcion fuera de rango.");
                        bool = false;
                    }
                    else{
                        bool = true;
                    }
                }
                else{
                    System.out.println("Ha introducido una opcion fuera de rango.");
                    bool = false;
                }
            } while(!bool);
            switch (optionModCentro){
                case 1:
                    do{
                        System.out.println("Introduce un nuevo nombre para centro:");
                        nombreCentro = sc.next();
                        if(nombreCentro.matches("[a-zA-Z]*")) bool = true;
                        else{
                            System.out.println("Ha introducido caracteres no validos para nombre. Solo letras.");
                            bool = false;
                        }
                    } while(!bool);
                    centros.get(indexCentro).setNombreCentro(nombreCentro);
                    System.out.println("Nombre cambiado correctamente.");
                    break;
                case 2:
                    do{
                        System.out.println("Introduce anyo actual o posterior de la gestion:\n"
                                + "1) "+anyoActual+"\n"
                                + "2) "+(anyoActual+1));
                        optionAnyo = sc.nextInt();
                        if(optionAnyo == 0 || optionAnyo == 1){
                            if(optionAnyo == 0) anyoGestion = anyoActual;
                            else if(optionAnyo == 1) anyoGestion = (anyoActual +1);
                            bool = true;
                            centros.get(indexCentro).setAnyo(anyoGestion);
                        }
                        else{
                            System.out.println("Ha introducido una opcion fuera del rango.");
                            bool = false;
                        }
                    } while(!bool);     
                    System.out.println("Anyo de gestion cambiado correctamente.");
                    break;
                case 0:
                    break;
            }
                    
        }
        else{
            System.out.println("No hay centros creados.");
        }
    }
    
    public static void borrarCentro(){
        Scanner sc = new Scanner(System.in);
        int indexCentro;
        Boolean bool;
        if(!centros.isEmpty()){
            do{
                System.out.println("Introduce que centro quiere elminar:\n"
                        + "Nº CENTRO | NOMBRE CENTRO | ANYO GESTION");
                for(CentroSalud x:centros){
                    System.out.println(centros.indexOf(x)+") "+x.getNombreCentro()+" - "+x.getAnyo());
                }
                indexCentro = sc.nextInt();
                if(indexCentro >= 0 || indexCentro >= centros.size()){
                    bool = true;
                    centros.remove(indexCentro);
                    System.out.println("Centro eliminado correctamente.");
                }
                else{
                    System.out.println("Ha introducido una opcion fuera de rango.");
                    bool = false;
                }
            } while(!bool);
        }
        else{
            System.out.println("No hay centros creados.");
        }
    }
    
    public static void listarCentros(){
        if(!centros.isEmpty()){
            System.out.println("Nº CENTRO | NOMBRE CENTRO | ANYO GESTION");
            for(CentroSalud x:centros){
                System.out.println(centros.indexOf(x)+") "+x.getNombreCentro()+" - "+x.getAnyo());
            }
        }
        else{
            System.out.println("No hay centros creados.");
        }
    }
    
    public static void accederCentro(){
    }
}
