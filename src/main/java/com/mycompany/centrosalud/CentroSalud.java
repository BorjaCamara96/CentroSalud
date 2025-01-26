/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.centrosalud;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Estudiante
 */
public class CentroSalud {
    
    SimpleDateFormat ts = new SimpleDateFormat("HH:mm");
    SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
    LocalDate dt = LocalDate.now();
    private int anyoActual = Integer.parseInt(formatter.format(dt));
    //private static ArrayList<CentroSalud> centros = new ArrayList<CentroSalud>();
    private static ArrayList<Cita> citaciones = new ArrayList<Cita>();
    private static ArrayList<Medico> medicos = new ArrayList<Medico>();
    private static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    private String nombreCentro;
    private int anyoGestion;
    
    
    public CentroSalud(){
        
    }
    
    public CentroSalud(String x, int y){
        this.nombreCentro = x;
        this.anyoGestion = y;
    }
    
    public String getNombreCentro(){
        return this.nombreCentro;
    }
    
    public void setNombreCentro(String x){
        this.nombreCentro = x;
    }
    
    public int getAnyo(){
        return this.anyoGestion;
    }
    
    public void setAnyo(int x){
        this.anyoGestion = x;
    }
    
    public ArrayList<Cita> getCitaciones(){
        return this.citaciones;
    } 
    
    public ArrayList<Medico> getMedicos(){
        return this.medicos;
    }
    
    public ArrayList<Paciente> getPacientes(){
        return this.pacientes;
    }
    public void crearPaciente(){
        if(!medicos.isEmpty()){
            Scanner sc = new Scanner(System.in);
            String nombre;
            int anyo;
            Boolean bool;
            do{
                System.out.println("Introduce un nombre para el paciente: ");
                nombre = sc.nextLine();
                if(nombre.matches("^[a-zA-Z\\s]*$") && !nombre.isBlank()) bool = true;
                else{
                    System.out.println("Ha introducido caracteres no validos para nombre. Solo letras.");
                    bool = false;
                }
            } while(!bool);
            bool = false;
            do{
                System.out.println("Introduce un anyo de nacimiento: ");
                anyo = sc.nextInt();
                if(anyo > 1920 && anyo <= anyoActual) bool = true;
                else if(anyo < 1920 || anyo > anyoActual) System.out.println("Anyo de nacimiento erroneo. Introduzca correctamente el dato.");
            } while(!bool);
            int option = 0;
            do{
                System.out.println("¿Quiere asignar un medico?\n0) YES\n1) NO");
                option = sc.nextInt();
                if(option != 0 && option != 1) System.out.println("Opcion fuera de rango.");
            } while (option != 0 && option != 1);
            switch (option){
                case 0:
                    bool = false;
                    int indexMedico = 0;
                    do{
                        System.out.println("Selecciona que medico quiere asignar:\n"
                            + "Nº MEDICO | NOMBRE MEDICO | ESPECIALIDAD");
                        for(Medico x:medicos){
                            System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                        }
                        long numeroMedico = sc.nextLong();
                        for(Medico x:medicos){
                            if(x.getNumSegSoc() == numeroMedico){
                                bool = true;
                                indexMedico = medicos.indexOf(x);
                            }
                        }
                        if(!bool) System.out.println("El numero de medico introducido no esta dado de alta.");
                    } while(!bool);         
                    pacientes.add(new Paciente(nombre, anyo, medicos.get(indexMedico)));
                    break;
                case 1:
                    pacientes.add(new Paciente(nombre, anyo));
                    break;            
            }
            System.out.println("Paciente creado.");
        }
        else{
            System.out.println("No hay medicos registrados. No se puede crear un paciente.");
        }
    }
    
    public void borrarPaciente(){
        if(!pacientes.isEmpty()){
            Scanner sc = new Scanner(System.in);
            Boolean bool = false;
            int indexPaciente = 0;
            do{
                System.out.println("Introduce numero de paciente que quiere eliminar:\n"
                        + "NUMERO SS | NOMBRE");
                for(Paciente x:pacientes){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre());
                }
                long numeroPaciente = sc.nextLong();
                for(Paciente x:pacientes){
                    if(x.getNumSegSoc() == numeroPaciente){
                        bool = true;
                        indexPaciente = pacientes.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de paciente introducido no esta dado de alta.");
            }while(!bool);
            pacientes.remove(indexPaciente);
            System.out.println("Paciente eliminado.");
        }
        else{
            System.out.println("No hay ningun paciente registrado.");
        }
    }
    
    public void modificarPaciente(){
        if(!pacientes.isEmpty()){
            Scanner sc = new Scanner(System.in);
            Boolean bool = false;
            int indexPaciente = 0;
            do{
                System.out.println("Introduce numero de paciente que quiere modificar:\n"
                        + "NUMERO SS | NOMBRE | ANYO");
                for(Paciente x:pacientes){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getAnyoNac());
                }
                long numeroPaciente = sc.nextLong();
                for(Paciente x:pacientes){
                    if(x.getNumSegSoc() == numeroPaciente){
                        bool = true;
                        indexPaciente = pacientes.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de paciente introducido no esta dado de alta.");
            }while(!bool);
            int option;
            do{
                System.out.println("Introduce que quiere cambiar de paciente seleccionado:\n"
                        + "0) Asignar nuevo numero de Seguridad Social.\n"
                        + "1) Modificar nombre de paciente.\n"
                        + "2) Modificar anyo de nacimiento de paciente.\n"
                        + "3) Modificar medico asignado.\n"
                        + "4) Añadir o eliminar enfermedades del historial de paciente");
                option = sc.nextInt();
                if(option <= 0 && option >=3) System.out.println("Ha introducido una opcion fuera de rango.");
            } while(option <= 0 && option >=3);
            switch (option){
                case 0:
                    pacientes.get(indexPaciente).setNumSegSoc();
                    System.out.println("El numero nuevo es: "+pacientes.get(indexPaciente).getNumSegSoc());
                    break;
                case 1:
                    String nombre;
                    do{
                        sc.nextLine();
                        System.out.println("Introduzca un nombre nuevo para paciente:");
                        nombre = sc.nextLine();
                        if(nombre.matches("^[a-zA-Z\\s]*$") && !nombre.isBlank()) bool = true;
                        else{
                            System.out.println("Ha introducido caracteres no validos para nombre. Solo letras.");
                            bool = false;
                        }
                    } while(!bool);
                    pacientes.get(indexPaciente).setNombre(nombre);
                    System.out.println("El nombre nuevo es "+pacientes.get(indexPaciente).getNombre());
                    break;
                case 2:
                    System.out.println("Introduce un anyo nuevo para paciente:");
                    int anyoNuevo = sc.nextInt();
                    pacientes.get(indexPaciente).setAnyoNac(anyoNuevo);
                    System.out.println("El anyo nuevo es: "+pacientes.get(indexPaciente).getAnyoNac());
                    break;
                case 3:
                    bool = false;
                    int indexMedico = 0;
                    do{
                        System.out.println("Introduce un numero de medico para asignar a paciente:\n"
                                + "NUMERO SS | NOMBRE MEDICO | ESPECIALIDAD");
                        for(Medico x:medicos){
                            System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                        }
                        System.out.println("Seleccione '0' para no seleccionar ningun medico.");
                        long optionMedico = sc.nextLong();
                        for(Medico x:medicos){
                            if(optionMedico == x.getNumSegSoc()){
                                bool = true;
                                indexMedico = medicos.indexOf(x);
                            }
                        }
                        if(indexMedico == 0) bool = true;
                        if(!bool) System.out.println("El numero de medico introducido no esta dado de alta.");
                    } while(!bool);
                    if(indexMedico == 0){
                        pacientes.get(indexPaciente).setMedicoAsignado(indexMedico);
                    }
                    else{
                        pacientes.get(indexPaciente).setMedicoAsignado(medicos.get(indexMedico));
                    }
                    break;
                case 4:
                    int optionEnfer = 0;
                    do{
                        pacientes.get(indexPaciente).mostrarEnfermedades();
                        System.out.println("¿Que quiere hacer?\n"
                                + "0) Añadir enfermedad:\n"
                                + "1) Eliminar enfermedad");
                        optionEnfer = sc.nextInt();
                        if(optionEnfer != 0 && optionEnfer != 1) System.out.println("Ha introducido una opcion fuera de rango.");
                    } while(optionEnfer != 0 && optionEnfer != 1);
                    switch (optionEnfer){
                        case 0:
                            pacientes.get(indexPaciente).añadirEnfermedad();
                            System.out.println("Enfermedad registrada en historial.");
                            break;
                        case 1:
                            if(!pacientes.get(indexPaciente).mostrarEnfermedades().isEmpty()){
                                pacientes.get(indexPaciente).borrarEnfermedad();
                                System.out.println("Enfermedad eliminada de historial");
                            }
                            else{
                                System.out.println("El paciente no tiene enfermedades cronicas registradas.");
                            }
                            break;
                    } 
            }
        } 
        else{
            System.out.println("No hay ningun paciente registrado.");
        }
    }
    
    public void crearMedico(){
        Scanner sc = new Scanner(System.in);
        String nombre;
        Boolean bool;
        do{
            System.out.println("Introduce un nombre para el medico: ");
            nombre = sc.nextLine();
            if(nombre.matches("^[a-zA-Z\\s]*$") && !nombre.isBlank()) bool = true;
            else{
                System.out.println("Ha introducido caracteres no validos para nombre. Solo letras.");
                bool = false;
            }
        } while(!bool);
        int anyo;
        do{
            System.out.println("Introduce un anyo de nacimiento:");
            anyo = sc.nextInt();
            if(anyo < 1940 || anyo > this.anyoGestion-18) System.out.println("Anyo de nacimiento erroneo. Introduzca correctamente el dato.");
        }while(anyo < 1940 || anyo > this.anyoGestion-18);
        int option = 0;
        do{
            System.out.println("Introduce una especialidad para el medico:");
            for(int i=0;i<Medico.getListaEspecialidades().length;i++){
                System.out.println(i+") "+Medico.getListaEspecialidades()[i]);
            }
            option = sc.nextInt();
            if(option < 0 || option > Medico.getListaEspecialidades().length-1) System.out.println("Ha introducido un valor fuera de rango.");
        } while(option < 0 || option > Medico.getListaEspecialidades().length-1);
        medicos.add(new Medico(nombre, anyo, option));
        System.out.println("Medico creado.");
    }
    
    public void borrarMedico(){
        if(!medicos.isEmpty()){
            Scanner sc = new Scanner(System.in);
            Boolean bool = false;
            int indexMedico = 0;
            do{
                System.out.println("Introduce numero de medico que quiere eliminar:"
                        + "NUMERO SS | NOMBRE | ESPECIALIDAD");
                for(Medico x:medicos){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                }
                long numeroMedico = sc.nextLong();
                for(Medico x:medicos){
                    if(x.getNumSegSoc() == numeroMedico){
                        bool = true;
                        indexMedico = medicos.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de medico introducido no esta dado de alta.");
            }while(!bool);
            pacientes.remove(indexMedico);
            System.out.println("Medico eliminado.");
        }
        else{
            System.out.println("No hay medicos registrados.");
        }
    }
    
    public void modificarMedico(){
        if(!medicos.isEmpty()){
            Scanner sc = new Scanner(System.in);
            Boolean bool = false;
            int indexMedico = 0;
            do{
                System.out.println("Introduce numero de medico que quiere modificar:"
                        + "NUMERO SS | NOMBRE | ANYO | ESPECIALIDAD");
                for(Medico x:medicos){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getAnyoNac()+" | "+x.getEspecialidad());
                }
                System.out.println();
                long numeroMedico = sc.nextLong();
                for(Medico x:medicos){
                    if(x.getNumSegSoc() == numeroMedico){
                        bool = true;
                        indexMedico = medicos.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de medico introducido no esta dado de alta.");
            }while(!bool);
            int option;
            do{
                System.out.println("Introduce que quiere cambiar de medico seleccionado:\n"
                        + "0) Asignar nuevo numero de Seguridad Social.\n"
                        + "1) Modificar nombre de medico.\n"
                        + "2) Modificar anyo de nacimiento de medico.\n"
                        + "3) Modificar especialidad de medico");
                option = sc.nextInt();
                if(option <= 0 && option >=3) System.out.println("Ha introducido una opcion fuera de rango.");
            } while(option <= 0 && option >=3);
            switch (option){
                case 0:
                    medicos.get(indexMedico).setNumSegSoc();
                    System.out.println("El numero nuevo es: "+medicos.get(indexMedico).getNumSegSoc());
                    break;
                case 1:
                    String nombre;
                    do{
                        sc.nextLine();
                        System.out.println("Introduce un nombre para el medico: ");
                        nombre = sc.nextLine();
                        if(nombre.matches("^[a-zA-Z\\s]*$") && !nombre.isBlank()) bool = true;
                        else{
                            System.out.println("Ha introducido caracteres no validos para nombre. Solo letras.");
                            bool = false;
                        }
                    } while(!bool);
                    medicos.get(indexMedico).setNombre(nombre);
                    System.out.println("El nombre nuevo es "+medicos.get(indexMedico).getNombre());
                    break;
                case 2:
                    int anyoNuevo;
                    do{
                        System.out.println("Introduce un anyo de nacimiento:");
                        anyoNuevo = sc.nextInt();
                        if(anyoNuevo < 1940 || anyoNuevo > this.anyoGestion-18) System.out.println("Anyo de nacimiento erroneo. Introduzca correctamente el dato.");
                    }while(anyoNuevo < 1940 || anyoNuevo > this.anyoGestion-18);
                    medicos.get(indexMedico).setAnyoNac(anyoNuevo);
                    System.out.println("El anyo nuevo es: "+medicos.get(indexMedico).getAnyoNac());
                    break;
                case 3:
                    Medico.getListaEspecialidades();
                    System.out.println("Introduzca una nueva especialidad:");
                    int optionEspecialidad = sc.nextInt();
                    medicos.get(indexMedico).setEspecialidad(optionEspecialidad);
                    break;
            }
        }
        else{
            System.out.println("No hay medicos registrados.");
        }
    }
    
    public void crearCita(){
        if(!pacientes.isEmpty() && !medicos.isEmpty()){
            Scanner sc = new Scanner(System.in);
            Boolean bool = false;
            int indexPaciente = 0;
            int indexMedico = 0;
            do{
                System.out.println("Introduce un paciente para crear la cita:\n"
                        + "NUMERO SS | NOMBRE");
                for(Paciente x:pacientes){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre());
                }
                long numPaciente = sc.nextLong();
                for(Paciente x:pacientes){
                    if(numPaciente == x.getNumSegSoc()){
                        bool = true;
                        indexPaciente = pacientes.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de paciente introducido no esta dado de alta.");
            } while (!bool);
            bool = false;
            do{
                System.out.println("Introduce un medico que quiere que atienda la cita medica:\n"
                        + "NUMERO SS | NOMBRE | ESPECIALIDAD");
                if(pacientes.get(indexPaciente).getMedicoAsignado() != null && pacientes.size() == 1){
                    System.out.println("MEDICO ASIGNADO:");
                    System.out.println(pacientes.get(indexPaciente).getMedicoAsignado().getNumSegSoc()+" | "
                            +pacientes.get(indexPaciente).getMedicoAsignado().getNombre()+" | "
                            +pacientes.get(indexPaciente).getMedicoAsignado().getEspecialidad());
                }
                else if(pacientes.get(indexPaciente).getMedicoAsignado() != null && pacientes.size() > 0){
                    System.out.println("MEDICO ASIGNADO:");
                    System.out.println(pacientes.get(indexPaciente).getMedicoAsignado().getNumSegSoc()+" | "
                            +pacientes.get(indexPaciente).getMedicoAsignado().getNombre()+" | "
                            +pacientes.get(indexPaciente).getMedicoAsignado().getEspecialidad());
                    System.out.println("MEDICOS DISPONIBLES:");
                    for(Medico x:medicos){
                        if(pacientes.get(indexPaciente).getMedicoAsignado() != null){
                            if(pacientes.get(indexPaciente).getMedicoAsignado().getNumSegSoc() != x.getNumSegSoc()){
                                System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                            }   
                        }
                        else{
                            System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                        }
                    }
                }
                else if(pacientes.get(indexPaciente).getMedicoAsignado() == null && pacientes.size() > 0){
                    System.out.println("MEDICOS DISPONIBLES:");
                    for(Medico x:medicos){
                        if(pacientes.get(indexPaciente).getMedicoAsignado() != null){
                            if(pacientes.get(indexPaciente).getMedicoAsignado().getNumSegSoc() != x.getNumSegSoc()){
                                System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                            }   
                        }
                        else{
                            System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                        }
                    }
                }
                long numMedico = sc.nextLong();
                for(Medico x:medicos){
                    if(x.getNumSegSoc() == numMedico){
                        bool = true;
                        indexMedico = medicos.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de medico introducido no esta dado de alta.");
            } while(!bool);
            Boolean controlCita = true;
            Date dt;
            Time tm;
            do{
                controlCita = true;
                int dia = 0, mes = 0, anyo = anyoActual, optionAnyo;
                do{
                    System.out.println("Introduzca año actual o posterior:\n"
                            + "0) "+this.anyoActual+"\n"
                            + "1) "+(this.anyoActual+1));
                    optionAnyo = sc.nextInt();
                    if(optionAnyo == 0) anyo = this.anyoActual;
                    else if (optionAnyo == 1) anyo = this.anyoActual++;
                    if(optionAnyo != 0 || optionAnyo ==1) System.out.println("Ha introducido valor no valido.");
                } while(optionAnyo != 0 || optionAnyo ==1);
                do{
                    System.out.println("Introduce un mes:");
                    mes = sc.nextInt();
                    if(mes < 1 || mes > 12) System.out.println("Introduce un mes valido.");
                } while(mes < 1 || mes > 12);
                do{
                    bool = false;
                    System.out.println("Introduce un dia:");
                    dia = sc.nextInt();
                    switch (mes){
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                            if(dia >= 1 && dia <= 31) bool = true;
                            break;
                        case 2:
                            if(anyo == 2024 || anyo == 2028 || anyo == 2032 || anyo == 2036 || anyo == 2040){
                                if(dia >= 1 && dia <= 29) bool = true;
                            }
                            else{
                               if(dia >= 1 && dia <= 28) bool = true;
                            }
                            break;
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            if(dia >= 1 && dia <= 30) bool = true;
                            break;
                    }
                    if(!bool) System.out.println("Ha introducido un dia no valido.");
                } while(!bool);
                int hora = 0, minuto = 0;
                do{
                    System.out.println("Introduce una hora de entre las 8 y las 18");
                    hora = sc.nextInt();
                    if(hora < 8 || hora > 18) System.out.println("Ha introducido una hora no valida.");
                } while(hora < 8 || hora > 18);
                do{
                    bool = false;
                    System.out.println("Introduce minutos 00 o 30:");
                    minuto = sc.nextInt();
                    if(minuto == 00 || minuto == 30){
                        bool = true;
                    }
                    else{
                        System.out.println("Valor no valido");
                    }
                } while (!bool);
                mes--;
                dt = new Date(anyo-1900,mes,dia);
                tm = new Time(hora, minuto,0);
                System.out.println("Fecha: "+sd.format(dt)+"\nHora: "+ts.format(tm)+"\n¿Es correcto?\n0) SI\n1) NO");
                int option = sc.nextInt();
                if(option == 1) controlCita = false;
                Date fechaActual = new Date(System.currentTimeMillis());
                if(dt.before(fechaActual)){
                    System.out.println("La fecha es anterior o igual a la actual. Ponga una fecha posterior.");
                    controlCita = false;
                }
                for(Cita x:citaciones){
                    if(medicos.get(indexMedico) == x.getMedico()){
                        if(dt == x.getFechaCita() && tm ==x.getHoraCita()){
                            controlCita = false; 
                        }
                    }
                }
            } while(!controlCita);
            if(controlCita){
                int indexCita = 0;
                citaciones.add(new Cita(dt, tm, pacientes.get(indexPaciente), medicos.get(indexMedico)));
                for(Cita x:citaciones){
                    if(x.getFechaCita() == dt && x.getHoraCita() == tm && x.getPaciente() == pacientes.get(indexPaciente) && x.getMedico() == medicos.get(indexMedico)){
                        indexCita = citaciones.indexOf(x);
                    }
                }
                citaciones.get(indexCita).crearTicket();
                System.out.println("Cita creada con exito.");
            }
        }
        else if(pacientes.isEmpty()){
            System.out.println("No hay medicos registrados.");
        }
        else if(medicos.isEmpty()){
            System.out.println("No hay pacientes registrados.");
        }
    }
    
    public void borrarCita(){
        if(!citaciones.isEmpty()){
            Scanner sc = new Scanner(System.in);
            Boolean bool = false;
            Boolean remove = null;
            int indexPaciente = 0;
             do{
                System.out.println("Introduce un paciente para crear la cita:\n"
                        + "NUMERO SS | NOMBRE");
                for(Paciente x:pacientes){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre());
                }
                System.out.println();
                long numPaciente = sc.nextLong();
                for(Paciente x:pacientes){
                    if(numPaciente == x.getNumSegSoc()){
                        bool = true;
                        indexPaciente = pacientes.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de paciente introducido no esta dado de alta.");
            } while (!bool);
            int option = 0;
            Boolean pacienteCita = false;
            for(Cita x:citaciones){
                if(x.getPaciente() == pacientes.get(indexPaciente)){
                    pacienteCita = true;
                }
            }
            if(!pacienteCita) System.out.println("El paciente seleccionado no tiene ninguna cita registrada.");
            if(pacienteCita){
                do{
                    bool = false;
                    System.out.println("Seleccione una opcion:\n"
                            + "0) Borrar todas las citas del paciente.\n"
                            + "1) Borrar una cita en concreto.");
                    System.out.println();
                    option = sc.nextInt();
                    if(option == 0 || option == 1) {
                        bool = true;
                    }
                    else{
                        System.out.println("Ha introducido un opcion no valida.");
                    }
                } while(!bool);
                if(option == 0){
                    ArrayList<Integer> citasBorrar = new ArrayList<Integer>();
                    for(Cita x:citaciones){
                        if(pacientes.get(indexPaciente) == x.getPaciente()){
                            citasBorrar.add(citaciones.indexOf(x));
                        }
                    }
                    for(Integer x:citasBorrar){
                        citaciones.get(x).borrarTicket();
                        remove = citaciones.remove(citaciones.get(x));
                    }
                    if(remove){
                        System.out.println("Se eliminaron todas las citas para el paciente seleccionado.");
                    }
                    else{
                        System.out.println("Error eliminando las citas.");
                    }
                }   
                else if(option == 1){
                    Boolean controlCita = true;
                    int indexCita = 0;
                    Date dt;
                    Time tm;
                    do{
                        controlCita = true;
                        int dia = 0, mes = 0, anyo = anyoGestion - 1900, optionAnyo;
                        System.out.println("### CITACIONES ###:\n"
                                + "FECHA CITA | HORA CITA | Nº PACIENTE | NOMBRE PACIENTE | Nº MEDICO | NOMBRE MEDICO");
                        for(Cita x:citaciones){
                                System.out.println(sd.format(x.getFechaCita())+" | "+ts.format(x.getHoraCita())+" | "+x.getPaciente().getNumSegSoc()+" | "+x.getPaciente().getNombre()+" | "+x.getMedico().getNumSegSoc()+" | "+x.getMedico().getNombre());      
                        }
                        System.out.println();
                        do{
                            System.out.println("Introduzca año actual o posterior:\n"
                            + "0) "+this.anyoActual+"\n"
                            + "1) "+(this.anyoActual+1));
                            optionAnyo = sc.nextInt();
                            if(optionAnyo == 0) anyo = this.anyoActual;
                            else if (optionAnyo == 1) anyo = this.anyoActual++;
                            if(optionAnyo != 0 || optionAnyo ==1) System.out.println("Ha introducido valor no valido.");
                        } while(optionAnyo != 0 || optionAnyo ==1);
                        do{
                            System.out.println("Introduce un mes:");
                            mes = sc.nextInt();
                            if(mes < 1 || mes > 12) System.out.println("Introduce un mes valido.");
                        } while(mes < 1 || mes > 12);
                        do{
                            bool = false;
                            System.out.println("Introduce un dia:");
                            dia = sc.nextInt();
                            switch (mes){
                                case 1:
                                case 3:
                                case 5:
                                case 7:
                                case 8:
                                case 10:
                                case 12:
                                    if(dia >= 1 && dia <= 31) bool = true;
                                    break;
                                case 2:
                                    if(anyo == 2024 || anyo == 2028 || anyo == 2032 || anyo == 2036 || anyo == 2040){
                                        if(dia >= 1 && dia <= 29) bool = true;
                                    }
                                    else{
                                       if(dia >= 1 && dia <= 28) bool = true;
                                    }
                                    break;
                                case 4:
                                case 6:
                                case 9:
                                case 11:
                                    if(dia >= 1 && dia <= 30) bool = true;
                                    break;
                            }
                            if(!bool) System.out.println("Ha introducido un dia no valido.");
                        } while(!bool);     
                        int hora = 0, minuto = 0;
                        do{
                            System.out.println("Introduce una hora de entre las 8 y las 18");
                            hora = sc.nextInt();
                            if(hora < 8 || hora > 18) System.out.println("Ha introducido una hora no valida.");
                        } while(hora < 8 || hora > 18);
                        do{
                            bool = false;
                            System.out.println("Introduce minutos 00 o 30:");
                            minuto = sc.nextInt();
                            if(minuto == 0 || minuto == 30) {
                                bool = true;
                            }
                            else{
                                System.out.println("Ha introducido minutos no validos.");
                            }
                            dt = new Date(anyo,mes,dia);
                            tm = new Time(hora, minuto,0);
                            System.out.println("Fecha: "+sd.format(dt)+"\nHora: "+ts.format(tm)+"\n¿Es correcto?\n0) SI\n1) NO");
                            option = sc.nextInt();
                            if(option == 0) bool = true;
                            else bool = false;
                        } while (!bool);
                        for(Cita x:citaciones){
                            if(dt == x.getFechaCita() && tm == x.getHoraCita()){
                                controlCita = true;
                                indexCita = citaciones.indexOf(x);
                            }
                        }
                    } while(!controlCita);
                    citaciones.get(indexCita).borrarTicket();
                    remove = citaciones.remove(citaciones.get(indexCita));
                    if(remove){
                        System.out.println("Se eliminaron todas las citas para el paciente seleccionado.");
                    }
                    else{
                        System.out.println("Error eliminando las citas.");
                    }
                }
            }
        }
        else{
            System.out.println("No hay citas registradas.");
        }
    }
    
    public void listarCitas(){
        if(!citaciones.isEmpty()){
            Collections.sort(citaciones);
            Date fechaActual = new Date(System.currentTimeMillis());
            System.out.println("### CITACIONES ###:\n"
                                + "FECHA CITA | HORA CITA | Nº PACIENTE | NOMBRE PACIENTE | Nº MEDICO | NOMBRE MEDICO");
                for(Cita x:citaciones){
                    if(x.getFechaCita().before(fechaActual)){
                        System.out.println(sd.format(x.getFechaCita())+" | "+ts.format(x.getHoraCita())+" | "+x.getPaciente().getNumSegSoc()+" | "+x.getPaciente().getNombre()+" | "+x.getMedico().getNumSegSoc()+" | "+x.getMedico().getNombre());      
                    }
                }
        }
        else{
            System.out.println("No hay citas registradas.");
        }
    }
    
    public void listarCitasFecha(){
        Scanner sc = new Scanner(System.in);
        if(!citaciones.isEmpty()){
            Date dt = new Date();
            Boolean bool;
            int optionAnyo, anyo = anyoActual, mes, dia, cont = 0;
            do{
                System.out.println("Introduzca año actual o posterior:\n"
                        + "0) "+this.anyoActual+"\n"
                        + "1) "+(this.anyoActual+1));
                optionAnyo = sc.nextInt();
                if(optionAnyo == 0) anyo = this.anyoActual;
                else if(optionAnyo == 1) anyo = this.anyoActual++;
                if(optionAnyo != 0 || optionAnyo ==1) System.out.println("Ha introducido valor no valido.");
            } while(optionAnyo != 0 || optionAnyo ==1);
            do{
                System.out.println("Introduce un mes:");
                mes = sc.nextInt();
                if(mes < 1 || mes > 12) System.out.println("Introduce un mes valido.");
            } while(mes < 1 || mes > 12);
            bool = false;
            do{
                System.out.println("Introduce un dia:");
                dia = sc.nextInt();
                switch (mes){
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if(dia >= 1 && dia <= 31) bool = true;
                        break;
                    case 2:
                        if(anyo == 124 || anyo == 128 || anyo == 132 || anyo == 136 || anyo == 140){
                            if(dia >= 1 && dia <= 29) bool = true;
                        }
                        else{
                           if(dia >= 1 && dia <= 28) bool = true;
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if(dia >= 1 && dia <= 30) bool = true;
                        break;
                }
                if(!bool) System.out.println("Ha introducido un dia no valido.");
                dt = new Date(anyo-1900,mes,dia);
                System.out.println("Fecha: "+sd.format(dt)+"\n0) SI\n1) NO");
                int option = sc.nextInt();
                if(option == 0) bool = true;
                else bool = false;
            } while(!bool);     
            for(Cita x:citaciones){
                if(x.getFechaCita() == dt){
                    cont++;
                }
            }
            if(cont > 0){
                System.out.println("### CITACIONES ###:\n"
                                + "FECHA CITA | HORA CITA | Nº PACIENTE | NOMBRE PACIENTE | Nº MEDICO | NOMBRE MEDICO");
                for(Cita x:citaciones){
                    if(x.getFechaCita() == dt){
                        System.out.println(sd.format(x.getFechaCita())+" | "+ts.format(x.getHoraCita())+" | "+x.getPaciente().getNumSegSoc()+" | "+x.getPaciente().getNombre()+" | "+x.getMedico().getNumSegSoc()+" | "+x.getMedico().getNombre());      
                    }
                }
            }
            else{
                System.out.println("No hay citas para ese dia indicado.");
            }
        }
        else{
            System.out.println("No hay citas registradas.");
        }
    }
    
    public void listarPersonas(){
        if(!medicos.isEmpty() || !pacientes.isEmpty()){
            if(!pacientes.isEmpty()){
                System.out.println("### PACIENTES ###\n"
                        + "NUMERO SS | NOMBRE");
                for(Paciente x:pacientes){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre());
                }
            }
            if(!medicos.isEmpty()){
                System.out.println("### MEDICOS ###\n"
                        + "NUMERO SS | NOMBRE | ESPECIALIDAD");
                for(Medico x:medicos){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                }
            }
        }
        else{
            System.out.println("No hay personas registradas.");
        }
    }
    
    public void listasDatosPaciente(){
        Scanner sc = new Scanner(System.in);
        if(!pacientes.isEmpty()){
            Boolean bool = false;
            int indexPaciente = 0;
            do{
                System.out.println("Introduce numero de paciente que quiere ver:\n"
                        + "NUMERO SS | NOMBRE | ANYO");
                for(Paciente x:pacientes){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getAnyoNac());
                }
                long numeroPaciente = sc.nextLong();
                for(Paciente x:pacientes){
                    if(x.getNumSegSoc() == numeroPaciente){
                        bool = true;
                        indexPaciente = pacientes.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de paciente introducido no esta dado de alta.");
            }while(!bool);        
            if(pacientes.get(indexPaciente).getMedicoAsignado() == null){
                System.out.println("NUMERO SS: "+pacientes.get(indexPaciente).getNumSegSoc()+"\n"
                    + "NOMBRE: "+pacientes.get(indexPaciente).getNombre()+"\n"
                    + "ANYO NACIMIENTO: "+pacientes.get(indexPaciente).getAnyoNac());
                if(!pacientes.get(indexPaciente).mostrarEnfermedades().isEmpty()){
                    System.out.println("LISTADO DE ENFERMEDADES DIAGNOSTICADAS:");
                    for(String x:pacientes.get(indexPaciente).mostrarEnfermedades()){
                        System.out.println("- "+x);
                    }
                }
            }
            else if(pacientes.get(indexPaciente).getMedicoAsignado() != null){
                System.out.println("NUMERO SS: "+pacientes.get(indexPaciente).getNumSegSoc()+"\n"
                    + "NOMBRE: "+pacientes.get(indexPaciente).getNombre()+"\n"
                    + "ANYO NACIMIENTO: "+pacientes.get(indexPaciente).getAnyoNac()+"\n"
                    + "NUMERO SS MEDICO ASIGNADO: "+pacientes.get(indexPaciente).getMedicoAsignado().getNumSegSoc()+"\n"
                    + "NOMBRE MEDICO ASIGNADO: "+pacientes.get(indexPaciente).getMedicoAsignado().getNombre());
                if(!pacientes.get(indexPaciente).mostrarEnfermedades().isEmpty()){
                    System.out.println("LISTADO DE ENFERMEDADES DIAGNOSTICADAS:");
                    for(String x:pacientes.get(indexPaciente).mostrarEnfermedades()){
                        System.out.println("- "+x);
                    }
                }
            }
        }
        else{
            System.out.println("No hay pacientes registrados.");
        }
    }
    
    public void listarMedicosEspecialidad(){
        Scanner sc = new Scanner(System.in);
        if(!medicos.isEmpty()){
            int option = 0;
            do{
                System.out.println("Selecciona una especialidad:");
                for(int i=0;i<Medico.getListaEspecialidades().length;i++){
                    System.out.println(i+") "+Medico.getListaEspecialidades()[i]);
                }
                option = sc.nextInt();
                if(option < 0 || option > Medico.getListaEspecialidades().length) System.out.println("Ha introducido un valor fuera de rango.");
            }while(option < 0 || option > Medico.getListaEspecialidades().length);
            System.out.println("### MEDICOS ###\n"
                        + "NUMERO SS | NOMBRE | ESPECIALIDAD");
            for(Medico x:medicos){
                if(x.getEspecialidad().equals(Medico.getListaEspecialidades()[option])){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                }
            }
        }
        else{
            System.out.println("No hay medicos registrados.");
        }
    }
    
    public void listarPacientesMedico(){
        Scanner sc = new Scanner(System.in);
        if(!pacientes.isEmpty()){
            Boolean bool = null;
            int indexMedico = 0;
            do{
                System.out.println("Selecciona que medico quiere asignar:\n"
                    + "Nº MEDICO | NOMBRE MEDICO | ESPECIALIDAD");
                for(Medico x:medicos){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre()+" | "+x.getEspecialidad());
                }
                long numeroMedico = sc.nextLong();
                for(Medico x:medicos){
                    if(x.getNumSegSoc() == numeroMedico){
                        bool = true;
                        indexMedico = medicos.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de medico introducido no esta dado de alta.");
            } while(!bool);      
            if(bool){
                System.out.println("### PACIENTES ###\n"
                        + "NUMERO SS | NOMBRE");
                for(Paciente x:pacientes){
                    if(x.getMedicoAsignado() == medicos.get(indexMedico)){
                        System.out.println(x.getNumSegSoc()+" | "+x.getNombre());
                    }
                }
            }
        }
        
        else{
            System.out.println("No hay pacientes registrados.");
        }
    }
    public void contarCitasPaciente(){
        if(!pacientes.isEmpty()){
            Scanner sc = new Scanner(System.in);
            Boolean bool = false;
            int indexPaciente = 0;
            int contPasadas = 0, contFuturas = 0;
            Date fechaActual = new Date(System.currentTimeMillis());
            do{
                System.out.println("Introduce numero de paciente que quiere eliminar:\n"
                        + "NUMERO SS | NOMBRE");
                for(Paciente x:pacientes){
                    System.out.println(x.getNumSegSoc()+" | "+x.getNombre());
                }
                long numeroPaciente = sc.nextLong();
                for(Paciente x:pacientes){
                    if(x.getNumSegSoc() == numeroPaciente){
                        bool = true;
                        indexPaciente = pacientes.indexOf(x);
                    }
                }
                if(!bool) System.out.println("El numero de paciente introducido no esta dado de alta.");
            }while(!bool);
            if(bool){
                for(Cita x:citaciones){
                    if(x.getPaciente() == pacientes.get(indexPaciente)){
                        if(x.getFechaCita().before(fechaActual)){
                            contPasadas++;
                        }
                        else if(x.getFechaCita().after(fechaActual)){
                            contFuturas++;
                        }
                    }
                }
                System.out.println("NUMERO SS: "+pacientes.get(indexPaciente).getNumSegSoc()+"\n"
                        + "NOMBRE: "+pacientes.get(indexPaciente).getNombre()+"\n"
                        + "CITAS ATENDIDAS: "+contPasadas+"\n"
                        + "CITAS PENDIENTES: "+contFuturas);
            }
        }
        else{
            System.out.println("No hay ningun paciente registrado.");
        }
    }
}   

