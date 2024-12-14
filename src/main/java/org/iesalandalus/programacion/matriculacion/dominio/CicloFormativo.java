package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class CicloFormativo {

    public static final int MAXIMO_NUMERO_HORAS = 2000;

    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;


    /*Métodos de acceso y modificación*/
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo < 1000 || codigo > 9999) {
            throw new IllegalArgumentException("El código debe ser un número de cuatro dígitos.");
        }
        this.codigo = codigo;
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        if (familiaProfesional == null) {
            throw new NullPointerException("ERROR: La familia profesional de un ciclo formativo no puede ser nula.");
        }
        if (familiaProfesional.isBlank() || familiaProfesional.isEmpty()) {
            throw new IllegalArgumentException("ERROR: La familia profesional no puede estar vacía.");
        }

        this.familiaProfesional = familiaProfesional;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        if (grado == null) {
            throw new NullPointerException("ERROR: El grado de un ciclo formativo no puede ser nulo.");
        }
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un ciclo formativo no puede ser nulo.");
        }
        if (nombre.isBlank() || nombre.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un ciclo formativo no puede estar vacío.");
        }

        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if (horas <= 0 || horas > MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException("ERROR: El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a " + MAXIMO_NUMERO_HORAS + ".");
        }
        this.horas = horas;
    }

    public CicloFormativo(int codigo, String familiaProfesional, Grado grado, String nombre, int horas) {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    public CicloFormativo(CicloFormativo otro) {
        if (otro == null) {
            throw new NullPointerException("ERROR: No es posible copiar un ciclo formativo nulo.");
        }
        this.codigo = otro.codigo;
        this.familiaProfesional = otro.familiaProfesional;
        this.grado = otro.grado;
        this.nombre = otro.nombre;
        this.horas = otro.horas;
    }

    /*Métodos equals y hashCode*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloFormativo that = (CicloFormativo) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    /* Metodo toString*/
    @Override
    public String toString() {
        return String.format("Código ciclo formativo="+codigo+", familia profesional="+familiaProfesional+", grado="+grado+", nombre ciclo formativo="+nombre+", horas="+horas);
    }

    /* Metodo imprimir*/
    public String imprimir() {
        return String.format("Código ciclo formativo="+codigo+", nombre ciclo formativo="+nombre);
    }
}
