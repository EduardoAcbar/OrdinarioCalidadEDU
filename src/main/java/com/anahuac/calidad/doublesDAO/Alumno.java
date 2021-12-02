package com.anahuac.calidad.doublesDAO;

public class Alumno {

    private String  ID,nombre,email;
    private int edad;

    public Alumno() {}

    public Alumno( String ID, String nombre, int edad, String email) {
  
    	this.ID = ID;
    	this.nombre = nombre;
        this.email  = email;
        this.edad   = edad;
    }

    //Metodos para id
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    //Metodos para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodos para edad
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    //Metodos pa mail
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
