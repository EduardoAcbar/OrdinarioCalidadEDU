	package com.anahuac.calidad.doublesDAO;

public class FakeDAO implements AlumnoDAO {

    @Override
    public boolean Alumno_add (Alumno a) {
        return false;
    }

    @Override
    public boolean Alumno_delete(Alumno a) {
        return false;
    }

    @Override
    public boolean Email_update (Alumno a){
        return false;
    }

    @Override
    public Alumno Alumno_retreive (String ID) {
        return null;
    }
}
