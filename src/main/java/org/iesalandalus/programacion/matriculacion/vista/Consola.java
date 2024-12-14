package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public final class Consola {

    private Consola() {
        throw new IllegalStateException("No se puede instanciar la clase de utilidades Consola.");
    }

    /* Metodo para mostrar el menú*/
    public static void mostrarMenu() {
        System.out.println("Menú de opciones: ");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    /*Metodo para elegir una opción*/
    public static Opcion elegirOpcion() {
        mostrarMenu();
        int opcionElegida;
        do {
            System.out.print("Elegir una opción: ");
            opcionElegida = Entrada.entero();
        } while (opcionElegida < 0 || opcionElegida >= Opcion.values().length);
        return Opcion.values()[opcionElegida];
    }

    /* Metodo para leer un alumno completo*/
    public static Alumno leerAlumno() {
        System.out.print("Escribe el nombre del alumno: ");
        String nombre = Entrada.cadena();
        System.out.print("Escribe el DNI del alumno: ");
        String dni = Entrada.cadena();
        System.out.print("Escribe el correo del alumno: ");
        String correo = Entrada.cadena();
        System.out.print("Escribe el teléfono del alumno: ");
        String telefono = Entrada.cadena();
        LocalDate fechaNacimiento = leerFecha("Escribe la fecha de nacimiento (dd/MM/YYYY): ");
        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
    }

    /* Metodo para obtener un alumno por DNI*/
    public static Alumno getAlumnoPorDni() {
        System.out.print("Escribe el DNI del alumno: ");
        String dni = Entrada.cadena();
        return new Alumno("Ficticio", dni, "ficticio@dominio.com", "600000000", LocalDate.of(2000, 1, 1));
    }

    /* Metodo para leer una fecha*/
    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                System.out.print(mensaje);
                String fechaTexto = Entrada.cadena();
                fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("dd/MM/YYYY"));
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Intenta nuevamente.");
            }
        }
        return fecha;
    }

    /* Metodo para leer un grado*/
    public static Grado leerGrado() {
        System.out.println("Grados disponibles:");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.ordinal() + " .- " + grado);
        }
        System.out.print("Escribe el número del grado: ");
        int opcion = Entrada.entero();
        return Grado.values()[opcion];
    }

    /* Metodo para leer un ciclo formativo*/
    public static CicloFormativo leerCicloFormativo() {
        System.out.print("Escribe el código del ciclo formativo: ");
        int codigo = Entrada.entero();
        System.out.print("Escribe el nombre del ciclo formativo: ");
        String nombre = Entrada.cadena();
        Grado grado = leerGrado();
        System.out.print("Escribe las horas del ciclo formativo: ");
        int horas = Entrada.entero();
        System.out.print("Escribe la familia profesional: ");
        String familiaProfesional = Entrada.cadena();
        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    /* Metodo para mostrar ciclos formativos*/
    public static void mostrarCiclosFormativos(CicloFormativo[] ciclos) {
        if (ciclos.length == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            for (CicloFormativo ciclo : ciclos) {
                if (ciclo != null) {
                    System.out.println(ciclo);
                }
            }
        }
    }

    /* Metodo para obtener un ciclo formativo por código*/
    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.print("Escribe el código del ciclo formativo: ");
        int codigo = Entrada.entero();
        return new CicloFormativo(codigo, "Ficticia", Grado.GDCFGM, "Ficticio", 1000);
    }

    /* Metodo para leer un curso*/
    public static Curso leerCurso() {
        System.out.println("Cursos:");
        for (Curso curso : Curso.values()) {
            System.out.println(curso);
        }
        Curso curso = null;
        do {
            try {
                System.out.print("Escribe un curso: ");
                String cursoTexto = Entrada.cadena().toUpperCase();
                curso = Curso.valueOf(cursoTexto);
            } catch (IllegalArgumentException e) {
                System.out.println("No existe ese curso. Inténtalo de nuevo.");
            }
        } while (curso == null);
        return curso;
    }

    /* Metodo para leer una especialidad del profesorado*/
    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("Especialidades disponibles:");
        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad);
        }
        EspecialidadProfesorado especialidad = null;
        do {
            try {
                System.out.print("Escribe una especialidad: ");
                String especialidadTexto = Entrada.cadena().toUpperCase();
                especialidad = EspecialidadProfesorado.valueOf(especialidadTexto);
            } catch (IllegalArgumentException e) {
                System.out.println("No existe esa especialidad. Inténtalo de nuevo.");
            }
        } while (especialidad == null);
        return especialidad;
    }

    /* Metodo para leer una asignatura*/
    public static Asignatura leerAsignatura(CicloFormativo ciclo) {
        System.out.print("Escribe el código de la asignatura: ");
        String codigo = Entrada.cadena();
        System.out.print("Escribe el nombre de la asignatura: ");
        String nombre = Entrada.cadena();
        System.out.print("Escribe las horas de la asignatura: ");
        int horas = Entrada.entero();
        Curso curso = leerCurso();
        System.out.print("Escribe las horas de desdoble: ");
        int horasDesdoble = Entrada.entero();
        EspecialidadProfesorado especialidad = leerEspecialidadProfesorado();
        return new Asignatura(codigo, nombre, horas, curso, horasDesdoble, especialidad, ciclo);
    }

    public static Asignatura getAsignaturaPorCodigo() {
        System.out.print("Escribe el código de la asignatura: ");
        String codigo = Entrada.cadena();
        return new Asignatura(codigo, "Nombre Ficticio", 100, Curso.PRIMERO, 0, EspecialidadProfesorado.INFORMATICA, new CicloFormativo(1, "Familia Ficticia", Grado.GDCFGS, "Nombre Ficticio", 1000));
    }

    /* Metodo para mostrar asignaturas*/
    public static void mostrarAsignaturas(Asignatura[] asignaturas) {
        for (Asignatura asignatura : asignaturas) {
            if (asignatura != null) {
                System.out.println(asignatura);
            }
        }
    }

    public static boolean asignaturaYaMatriculada(Asignatura[] asignaturas, Asignatura asignatura) {
        for (Asignatura a : asignaturas) {
            if (a != null && a.equals(asignatura)) {
                return true;
            }
        }
        return false;
    }

    /* Metodo para leer una matrícula*/
    public static Matricula leerMatricula(Alumno alumno, Asignatura[] asignaturas) throws OperationNotSupportedException {
        System.out.print("Identificador de la matrícula: ");
        int id = Entrada.entero();
        if (id <= 0) {
            throw new OperationNotSupportedException("El identificador de la matrícula no es válido.");
        }

        System.out.print("Curso académico: ");
        String cursoAcademico = Entrada.cadena();
        if (cursoAcademico.isEmpty()) {
            throw new OperationNotSupportedException("El curso académico no puede estar vacío.");
        }

        LocalDate fechaMatriculacion = leerFecha("Fecha de matriculación: ");
        if (fechaMatriculacion.isAfter(LocalDate.now())) {
            throw new OperationNotSupportedException("La fecha de matriculación no puede ser futura.");
        }

        Asignatura[] asignaturasSeleccionadas = new Asignatura[5];
        int contador = 0;

        for (int i = 0; i < asignaturas.length; i++) {
            mostrarAsignaturas(asignaturas);
            System.out.print("Elige código de una asignatura: ");
            String codigo = Entrada.cadena();

            Asignatura asignaturaSeleccionada = null;
            if (asignaturas[i] != null && asignaturas[i].getCodigo().equals(codigo)) {
                asignaturaSeleccionada = asignaturas[i];
            }

            if (asignaturaSeleccionada != null) {
                if (!asignaturaYaMatriculada(asignaturasSeleccionadas, asignaturaSeleccionada)) {
                    asignaturasSeleccionadas[contador++] = asignaturaSeleccionada;
                    if (contador == 5) {
                        System.out.println("Has seleccionado el máximo de 5 asignaturas.");
                        break;
                    }
                } else {
                    System.out.println("La asignatura ya está matriculada.");
                }
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        }

        if (contador == 0) {
            throw new OperationNotSupportedException("Debes seleccionar al menos una asignatura.");
        }

        Asignatura[] asignaturasFinales = Arrays.copyOf(asignaturasSeleccionadas, 5);

        return new Matricula(id, cursoAcademico, fechaMatriculacion, alumno, asignaturasFinales);
    }

    public static Matricula getMatriculaPorIdentificador() {
        System.out.print("Escribe el identificador de la matrícula: ");
        int id = Entrada.entero();
        try {
            return new Matricula(id, "24-25", LocalDate.now(), getAlumnoPorDni(), new Asignatura[0]);
        } catch (OperationNotSupportedException e) {
            System.out.println("Error al obtener la matrícula: " + e.getMessage());
            return null;
        }
    }
}

