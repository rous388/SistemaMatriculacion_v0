package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;

import java.time.LocalDate;

public class MainApp {
    public static final int CAPACIDAD = 3;
    private static final Alumnos alumnos = new Alumnos(CAPACIDAD);
    private static final Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
    private static final CiclosFormativos ciclosFormativos = new CiclosFormativos(CAPACIDAD);
    private static final Matriculas matriculas = new Matriculas(CAPACIDAD);

    private static void ejecutarOpcion(Opcion opcionSeleccionada) {
        switch (opcionSeleccionada) {
            case INSERTAR_ALUMNO: insertarAlumno(); break;
            case BUSCAR_ALUMNO: buscarAlumno(); break;
            case BORRAR_ALUMNO: borrarAlumno(); break;
            case MOSTRAR_ALUMNOS: mostrarAlumnos(); break;
            case INSERTAR_ASIGNATURA: insertarAsignatura(); break;
            case BUSCAR_ASIGNATURA: buscarAsignatura(); break;
            case BORRAR_ASIGNATURA: borrarAsignatura(); break;
            case MOSTRAR_ASIGNATURAS: mostrarAsignaturas(); break;
            case INSERTAR_CICLO_FORMATIVO: insertarCicloFormativo(); break;
            case BUSCAR_CICLO_FORMATIVO: buscarCicloFormativo(); break;
            case BORRAR_CICLO_FORMATIVO: borrarCicloFormativo(); break;
            case MOSTRAR_CICLOS_FORMATIVOS: mostrarCiclosFormativos(); break;
            case INSERTAR_MATRICULA: insertarMatricula(); break;
            case BUSCAR_MATRICULA: buscarMatricula(); break;
            case ANULAR_MATRICULA: anularMatricula(); break;
            case MOSTRAR_MATRICULAS: mostrarMatriculas(); break;
            case MOSTRAR_MATRICULAS_ALUMNO: mostrarMatriculasPorAlumno(); break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO: mostrarMatriculasPorCicloFormativo(); break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO: mostrarMatriculasPorCursoAcademico(); break;
            case SALIR: System.out.println("Gracias por usar la aplicación. ¡Hasta pronto!"); break;
        }
    }

    private static void insertarAlumno() {
        try {
            Alumno nuevoAlumno = Consola.leerAlumno();
            alumnos.insertar(nuevoAlumno);
            System.out.println("Alumno insertado.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarAlumno() {
        try {
            Alumno alumnoBuscado = Consola.getAlumnoPorDni();
            Alumno encontrado = alumnos.buscar(alumnoBuscado);
            System.out.println((encontrado != null) ? encontrado : "Alumno no encontrado.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarAlumno() {
        try {
            Alumno alumnoAEliminar = Consola.getAlumnoPorDni();
            alumnos.borrar(alumnoAEliminar);
            System.out.println("Alumno eliminado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarAlumnos() {
        Alumno[] listaAlumnos = alumnos.get();
        if (listaAlumnos.length == 0) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : listaAlumnos) {
                if (alumno != null) {
                    System.out.println(alumno);
                }
            }
        }
    }

    private static void insertarAsignatura() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            Asignatura nuevaAsignatura = Consola.leerAsignatura(cicloFormativo);
            asignaturas.insertar(nuevaAsignatura);
            System.out.println("Asignatura insertada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarAsignatura() {
        try {
            Asignatura asignaturaBuscada = Consola.getAsignaturaPorCodigo();
            Asignatura encontrada = asignaturas.buscar(asignaturaBuscada);
            System.out.println((encontrada != null) ? encontrada : "Asignatura no encontrada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarAsignatura() {
        try {
            Asignatura asignaturaABorrar = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignaturaABorrar);
            System.out.println("Asignatura eliminada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarAsignaturas() {
        Asignatura[] listaAsignaturas = asignaturas.get();
        if (listaAsignaturas.length == 0) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            for (Asignatura asignatura : listaAsignaturas) {
                if (asignatura != null) {
                    System.out.println(asignatura);
                }
            }
        }
    }

    private static void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            ciclosFormativos.insertar(ciclo);
            System.out.println("Ciclo formativo agregado.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarCicloFormativo() {
        try {
            CicloFormativo cicloBuscado = Consola.getCicloFormativoPorCodigo();
            CicloFormativo encontrado = ciclosFormativos.buscar(cicloBuscado);
            System.out.println((encontrado != null) ? encontrado : "Ciclo formativo no encontrado.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarCicloFormativo() {
        try {
            CicloFormativo cicloABorrar = Consola.getCicloFormativoPorCodigo();
            ciclosFormativos.borrar(cicloABorrar);
            System.out.println("Ciclo formativo eliminado.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarCiclosFormativos() {
        CicloFormativo[] listaCiclos = ciclosFormativos.get();
        if (listaCiclos.length == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            for (CicloFormativo ciclo : listaCiclos) {
                if (ciclo != null) {
                    System.out.println(ciclo);
                }
            }
        }
    }

    private static void insertarMatricula() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Matricula matriculaNueva = Consola.leerMatricula(alumno, asignaturas.get());
            matriculas.insertar(matriculaNueva);
            System.out.println("Matrícula insertada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarMatricula() {
        try {
            Matricula matriculaBuscada = Consola.getMatriculaPorIdentificador();
            Matricula encontrada = matriculas.buscar(matriculaBuscada);
            System.out.println((encontrada != null) ? encontrada : "Matrícula no encontrada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void anularMatricula() {
        try {
            mostrarMatriculas();
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            matriculas.borrar(matricula);
            System.out.println("Matrícula anulada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarMatriculas() {
        Matricula[] listaMatriculas = matriculas.get();
        if (listaMatriculas.length == 0) {
            System.out.println("No hay matrículas registradas.");
        } else {
            for (Matricula matricula : listaMatriculas) {
                if (matricula != null) {
                    System.out.println(matricula);
                }
            }
        }
    }

    private static void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Matricula[] matriculasAlumno = matriculas.get(alumno);
            if (matriculasAlumno.length == 0) {
                System.out.println("Este alumno no tiene matrículas.");
            } else {
                for (Matricula matricula : matriculasAlumno) {
                    if (matricula != null) {
                        System.out.println(matricula);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarMatriculasPorCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloFormativoPorCodigo();
            Matricula[] matriculasCiclo = matriculas.get(ciclo);
            if (matriculasCiclo.length == 0) {
                System.out.println("Este ciclo no tiene matrículas.");
            } else {
                for (Matricula matricula : matriculasCiclo) {
                    if (matricula != null) {
                        System.out.println(matricula);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarMatriculasPorCursoAcademico() {
        System.out.print("Introduce el curso académico (ejemplo: 24-25): ");
        String cursoAcademico = Consola.leerCurso().toString();
        Matricula[] matriculasCurso = matriculas.get(cursoAcademico);
        if (matriculasCurso.length == 0) {
            System.out.println("No hay matrículas para este curso académico.");
        } else {
            for (Matricula matricula : matriculasCurso) {
                if (matricula != null) {
                    System.out.println(matricula);
                }
            }
        }
    }

    public static void main(String[] args) {
        Opcion opcion;
        do {
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }
}