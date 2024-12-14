package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Matriculas {

    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionMatriculas = new Matricula[capacidad];
    }

    public Matricula[] get() {
        return copiaProfundaMatriculas();
    }
/*Metodo copia profunda matrículas*/
    private Matricula[] copiaProfundaMatriculas() {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copia;
    }
/*Metodo insertar matrícula no null*/
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        if (buscar(matricula) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
        coleccionMatriculas[tamano] = new Matricula(matricula);
        tamano++;
    }
/*Metodo buscar matricula*/
    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            return null;
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
                return new Matricula(coleccionMatriculas[i]);
            }
        }
        return null;
    }
/*metodo borrar matricula*/
    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        desplazarIzquierda(indice);
        tamano--;
    }
/*Metodo buscar indice*/
    private int buscarIndice(Matricula matricula) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
                return i;
            }
        }
        return -1;
    }
/*Metodo desplazar a la izquierda*/
    private void desplazarIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        coleccionMatriculas[tamano - 1] = null;
    }

    public Matricula[] get(Alumno alumno) {
        return Arrays.stream(coleccionMatriculas)
                .filter(m -> m != null && m.getAlumno().equals(alumno))
                .toArray(Matricula[]::new);
    }

    public Matricula[] get(String cursoAcademico) {
        return Arrays.stream(coleccionMatriculas)
                .filter(m -> m != null && m.getCursoAcademico().equals(cursoAcademico))
                .toArray(Matricula[]::new);
    }

    public Matricula[] get(CicloFormativo cicloFormativo) {
        return Arrays.stream(coleccionMatriculas)
                .filter(m -> m != null && Arrays.stream(m.getColeccionAsignaturas())
                        .anyMatch(a -> a.getCicloFormativo().equals(cicloFormativo)))
                .toArray(Matricula[]::new);
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Matriculas{" + "capacidad=" + capacidad + ", tamaño=" + tamano + ", coleccionMatriculas=" + Arrays.toString(copiaProfundaMatriculas()) + '}';
    }
}


