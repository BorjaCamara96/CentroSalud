 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.centrosalud;

import java.io.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Estudiante
 */
public class Cita implements Comparable<Cita>{
    
    private static ArrayList <String> identOcup = new ArrayList<String>();
    private String identCita;
    private static final String meses[] = {" ", "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
    private Date fechaCita;
    private Time horaCita;
    private Paciente paciente;
    private Medico medico;
    
    public Cita(){
        
    }
    
    public Cita(Date a, Time b, Paciente c, Medico d){
        this.fechaCita = a;
        this.horaCita = b;
        this.paciente = c;
        this.medico = d;
        Random rm = new Random();
        String aux = "";
        char x, y, z;
        do{
            x = (char) rm.nextInt(65,90);
            y = (char) rm.nextInt(48,57);
            z = (char) rm.nextInt(65,90);
            StringBuilder sb = new StringBuilder();
            sb.append(x);
            sb.append(y);
            sb.append(z);
            aux = sb.toString();
        } while(!Cita.comprobarIdentCita(aux));
        Cita.identOcup.add(aux);
        this.identCita = aux;
    }
    
    public String getIdentCita(){
        return this.identCita;
    }
    
    public void setIdentCita(){
        Random rm = new Random();
        String aux = "";
        char x, y, z;
        do{
            x = (char) rm.nextInt(65,90);
            y = (char) rm.nextInt(48,57);
            z = (char) rm.nextInt(65,90);
            aux = Character.toString(x+y+z);
        } while(!this.comprobarIdentCita(aux));
        this.identOcup.add(aux);
    }
    
    public Date getFechaCita(){
        return this.fechaCita;
    }
    
    public void setFechaCita(Date x){
        this.fechaCita = x;
    }
    
    public Time getHoraCita(){
        return this.horaCita;
    }
    
    public void setHoraCita(Time x){
        this.horaCita = x;
    }
    
    public Paciente getPaciente(){
        return this.paciente;
    }
    
    public void setPaciente(Paciente x){
        this.paciente = x;
    }
    
    public Medico getMedico(){
        return this.medico;
    }
    
    public void setMedico(Medico x){
        this.medico = x;
    }
    
    public void crearTicket(){
        SimpleDateFormat dia = new SimpleDateFormat("dd");
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        String mesString = mes.format(this.fechaCita);
        int mesInt = Integer.parseInt(mesString);
        SimpleDateFormat anyo = new SimpleDateFormat("yyyy");
        SimpleDateFormat comp = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat ts = new SimpleDateFormat("HH:mm");
        File folderTickets = new File("Tickets");
        if(!folderTickets.exists()){
            folderTickets.mkdir();
        }
        File folderAnyo = new File("TICKETS/"+anyo.format(this.fechaCita));
        if(!folderAnyo.exists()){
            folderAnyo.mkdir();
        }
        File folderMoth = new File("TICKETS/"+anyo.format(this.fechaCita)+"/"+mes.format(this.fechaCita)+" - "+Cita.meses[mesInt]);
        if(!folderMoth.exists()){
            folderMoth.mkdir();
        }
        String fichero = "TICKETS/"+anyo.format(this.fechaCita)+"/"+mes.format(this.fechaCita)+" - "+Cita.meses[mesInt]+"/"+comp.format(this.fechaCita)+" "+this.getIdentCita()+" - "+this.paciente.getNombre()+".txt";
        try {
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            salida.print("####### "+this.getIdentCita()+" #######\n"
                    +"# FECHA: "+sd.format(this.fechaCita)+" #\n"
                    +"# HORA: "+ts.format(this.horaCita)+" #\n"         
                    +"# Nº PACIENTE: "+this.paciente.getNumSegSoc()+" #\n"
                    +"# NOMBRE PACIENTE: "+this.paciente.getNombre()+" #\n"
                    +"# Nº MEDICO: "+this.medico.getNumSegSoc()+" #\n"
                    +"# NOMBRE MEDICO: "+this.medico.getNombre()+" #\n"
                    + "###################");     
            salida.close();
        } catch (IOException ex) {
            Logger.getLogger(Cita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarTicket(){
        SimpleDateFormat dia = new SimpleDateFormat("dd");
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        String mesString = mes.format(this.fechaCita);
        int mesInt = Integer.parseInt(mesString);
        SimpleDateFormat anyo = new SimpleDateFormat("yyyy");
        SimpleDateFormat comp = new SimpleDateFormat("dd-MM-yyyy");
        String fichero = "TICKETS/"+anyo.format(this.fechaCita)+"/"+mes.format(this.fechaCita)+" - "+Cita.meses[mesInt]+"/"+comp.format(this.fechaCita)+" "+this.getIdentCita()+" - "+this.paciente.getNombre()+".txt";
        File file = new File(fichero);
        boolean status = file.delete();
        if(status){
            System.out.println("Ticket eliminado corretamente.");
        }
        else{
            System.out.println("Error al eliminar ticket.");
        }
        File folderMoth = new File("TICKETS/"+anyo.format(this.fechaCita)+"/"+mes.format(this.fechaCita)+" - "+Cita.meses[mesInt]);
        if(folderMoth.listFiles().length == 0){
            folderMoth.delete();
        }
        File folderAnyo = new File("TICKETS/"+anyo.format(this.fechaCita));
        if(folderAnyo.listFiles().length == 0){
            folderAnyo.delete();
        }
        
    }
    
    public static boolean comprobarIdentCita(String x){
        Boolean bool = true;
        for(String ident:identOcup){
            if(x.equals(ident)){
                bool = false;
            }
        }
        return bool;
    }

    @Override
    public int compareTo(Cita x) {
        return this.fechaCita.compareTo(x.getFechaCita());
    }
    
}
