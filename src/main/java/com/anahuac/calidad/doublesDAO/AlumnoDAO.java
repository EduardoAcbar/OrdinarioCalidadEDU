package com.anahuac.calidad.doublesDAO;

public interface AlumnoDAO {
    public boolean Alumno_add(Alumno a);
    public boolean Alumno_delete(Alumno a);
    public boolean Email_update(Alumno a);
    public Alumno Alumno_retreive(String ID);

}
//EAB