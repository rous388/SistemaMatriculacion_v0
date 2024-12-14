package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Alumnos {

    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;

    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAlumnos = new Alumno[capacidad];
    }

    /* Metodo para obtener una copia profunda de la colección*/
    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copia = new Alumno[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copia;
    }

    /*Metodo para insertar un alumno*/
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }
        if (buscar(alumno) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
        coleccionAlumnos[tamano] = new Alumno(alumno);
        tamano++;
    }

    /*Metodo para buscar un alumno*/
    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            return null;
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                return new Alumno(coleccionAlumnos[i]);
            }
        }
        return null;
    }

    /* Metodo para borrar un alumno*/
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
        desplazarIzquierda(indice);
        tamano--;
    }

    private int buscarIndice(Alumno alumno) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[tamano - 1] = null;
    }

    /*Metodo para obtener el tamano actual de la colección*/
    public int getTamano() {
        return tamano;
    }

    /*Metodo para obtener la capacidad máxima de la colección*/
    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Alumnos{" + "capacidad=" + capacidad +", tamaño=" + tamano + ", coleccionAlumnos=" + Arrays.toString(copiaProfundaAlumnos()) + '}';
    }
}
