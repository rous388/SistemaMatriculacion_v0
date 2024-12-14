package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Asignaturas {

    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAsignaturas = new Asignatura[capacidad];
    }

    /* Metodo para obtener una copia profunda de la colección*/
    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copia = new Asignatura[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copia;
    }

    /* Metodo para insertar una asignatura*/
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }
        if (buscar(asignatura) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
        coleccionAsignaturas[tamano] = new Asignatura(asignatura);
        tamano++;
    }

    /* Metodo para buscar una asignatura*/
    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            return null;
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
                return new Asignatura(coleccionAsignaturas[i]);
            }
        }
        return null;
    }

    /* Metodo para borrar una asignatura*/
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        desplazarIzquierda(indice);
        tamano--;
    }

    private int buscarIndice(Asignatura asignatura) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[tamano - 1] = null;
    }

    /* Metodo para obtener el tamaño actual de la colección*/
    public int getTamano() {
        return tamano;
    }

    /* Metodo para obtener la capacidad máxima de la colección*/
    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Asignaturas{" + "capacidad=" + capacidad + ", tamaño=" + tamano + ", coleccionAsignaturas=" + Arrays.toString(copiaProfundaAsignaturas()) + '}';
    }
}

