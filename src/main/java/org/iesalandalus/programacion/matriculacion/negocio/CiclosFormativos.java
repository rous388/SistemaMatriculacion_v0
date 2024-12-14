package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class CiclosFormativos {

    private int capacidad;
    private int tamano;
    private CicloFormativo[] coleccionCiclosFormativos;

    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    /* Metodo para obtener una copia profunda de la colección*/
    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos() {
        CicloFormativo[] copia = new CicloFormativo[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copia;
    }

    /* Metodo para insertar un ciclo formativo*/
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }
        if (buscar(cicloFormativo) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
        coleccionCiclosFormativos[tamano] = new CicloFormativo(cicloFormativo);
        tamano++;
    }

    /* Metodo para buscar un ciclo formativo*/
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            return null;
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return new CicloFormativo(coleccionCiclosFormativos[i]);
            }
        }
        return null;
    }

    /* Metodo para borrar un ciclo formativo*/
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        desplazarIzquierda(indice);
        tamano--;
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
        }
        coleccionCiclosFormativos[tamano - 1] = null;
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
        return "CiclosFormativos{" + "capacidad=" + capacidad + ", tamaño=" + tamano + ", coleccionCiclosFormativos=" + Arrays.toString(copiaProfundaCiclosFormativos()) + '}';
    }
}
