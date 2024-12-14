package org.iesalandalus.programacion.matriculacion.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class Matricula {

    public static final int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    public static final String ER_CURSO_ACADEMICO = "^\\d{2}-\\d{2}$";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";

    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;

    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] asignaturas) throws OperationNotSupportedException {

        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(asignaturas);
    }

    public Matricula(Matricula otra) {
        if (otra == null) {
            throw new NullPointerException("ERROR: No es posible copiar una matrícula nula.");
        }
        this.idMatricula = otra.idMatricula;
        this.cursoAcademico = otra.cursoAcademico;
        this.fechaMatriculacion = otra.fechaMatriculacion;
        this.fechaAnulacion = otra.fechaAnulacion;
        this.alumno = otra.alumno;
        this.coleccionAsignaturas = Arrays.copyOf(otra.coleccionAsignaturas, otra.coleccionAsignaturas.length);
    }

    /*Metodos de acceso y modificación*/
    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        if (idMatricula <= 0) {
            throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        }
        this.idMatricula = idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico de una matrícula no puede ser nulo.");
        }
        if (cursoAcademico.isEmpty() || cursoAcademico.isBlank()) {
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");
        }
        if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        if (fechaMatriculacion == null) {
            throw new NullPointerException("ERROR: La fecha de matriculación de una mátricula no puede ser nula.");
        }
        if (fechaMatriculacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");
        }
        if (LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA).isAfter(fechaMatriculacion)) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion != null) {
            if (fechaAnulacion.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
            }
            if (fechaAnulacion.isBefore(fechaMatriculacion)) {
                throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
            }
            if (fechaMatriculacion.plusMonths(MAXIMO_MESES_ANTERIOR_ANULACION).isBefore(fechaAnulacion)) {
                throw new IllegalArgumentException("ERROR: La fecha de anulación no puede superar los " + MAXIMO_MESES_ANTERIOR_ANULACION + " meses.");
            }
        }
        this.fechaAnulacion = fechaAnulacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }
        this.alumno = alumno;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return Arrays.copyOf(coleccionAsignaturas, coleccionAsignaturas.length);
    }

    public void setColeccionAsignaturas(Asignatura[] asignaturas) throws OperationNotSupportedException {
        if (asignaturas == null) {
            throw new NullPointerException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        }
        if (asignaturas.length > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            throw new IllegalArgumentException("ERROR: El número máximo de asignaturas por matrícula es " + MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA + ".");
        }

        int totalHoras = 0;
        for (Asignatura asignatura : asignaturas) {
            if (asignatura != null) {
                totalHoras += asignatura.getHorasAnuales();
            }
        }

        if (totalHoras > MAXIMO_NUMERO_HORAS_MATRICULA) {
            throw new OperationNotSupportedException("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas (" + MAXIMO_NUMERO_HORAS_MATRICULA + " horas).");
        }

        this.coleccionAsignaturas = Arrays.copyOf(asignaturas, asignaturas.length);
    }

    /*Metodos auxiliares*/
    private boolean superaMaximoNumeroHorasMatricula(Asignatura[] asignaturas) {
        int totalHoras = 0;
        for (Asignatura asignatura : asignaturas) {
            totalHoras += asignatura.getHorasAnuales();
        }
        return totalHoras > MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    public String imprimir() {
        return String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, alumno={%s}",
                idMatricula, cursoAcademico,
                fechaMatriculacion.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)),
                alumno.imprimir());
    }

    @Override
    public String toString() {
        StringBuilder resultadoAsignaturas = new StringBuilder();
        for (Asignatura asignatura : coleccionAsignaturas) {
            if (asignatura != null) {
                resultadoAsignaturas.append(asignatura.imprimir()).append(" ");
            }
        }
        return String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s%s, alumno=%s, Asignaturas={ %s}",
                idMatricula, cursoAcademico,
                fechaMatriculacion.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)),
                (fechaAnulacion != null ? ", fecha anulación=" + fechaAnulacion.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) : ""),
                alumno.imprimir(), resultadoAsignaturas.toString().trim());
    }

    /*Métodos equals y hashCode*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matricula matricula = (Matricula) obj;
        return idMatricula == matricula.idMatricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatricula);
    }
}