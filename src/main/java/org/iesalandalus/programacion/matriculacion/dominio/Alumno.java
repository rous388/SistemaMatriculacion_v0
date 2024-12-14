package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {
    public static final String ER_TELEFONO = "^\\d{9}$";
    public static final String ER_CORREO = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String ER_DNI = "^(\\d{8})([A-Z])$";
    public static final String FORMATO_FECHA = "dd/MM/YYYY";
    public static final String ER_NIA = "^[a-z]{4}\\d{3}$";
    public static final int MIN_EDAD_ALUMNADO = 16;

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;

    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        calcularNia();
    }

    public Alumno(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
        }
        this.nombre = alumno.nombre;
        this.dni = alumno.dni;
        this.correo = alumno.correo;
        this.telefono = alumno.telefono;
        this.fechaNacimiento = alumno.fechaNacimiento;
        this.nia = alumno.nia;
    }

    /*Métodos de acceso y modificación*/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        }
        String nombreFormateado = nombre.trim().replaceAll("\\s+", " ");
        if (nombreFormateado.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
        }
        this.nombre = formatearNombre(nombreFormateado);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");
        }
        Matcher matcher = Pattern.compile(ER_DNI).matcher(dni);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if (!comprobarLetraDni(matcher)) {
            throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");
        }
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        }
        if (correo.isBlank() || correo.isEmpty()) {
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        }
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un alumno no puede ser nula.");
        }
        if (LocalDate.now().minusYears(MIN_EDAD_ALUMNADO).isBefore(fechaNacimiento)) {
            throw new IllegalArgumentException("ERROR: La edad del alumno debe ser mayor o igual a 16 años.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNia() {
        return nia;
    }

    private void calcularNia() {
        String nombreBase = nombre.toLowerCase().substring(0, 4);
        String dniBase = dni.substring(5, 8);
        this.nia = nombreBase + dniBase;
    }

    /*Métodos auxiliares*/
    private String formatearNombre(String nombre) {
        String[] palabras = nombre.split(" ");
        StringBuilder formateado = new StringBuilder();
        for (String palabra : palabras) {
            formateado.append(palabra.substring(0, 1).toUpperCase()).append(palabra.substring(1).toLowerCase()).append(" ");
        }
        return formateado.toString().trim();
    }

    private boolean comprobarLetraDni(Matcher matcher) {
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        int numero = Integer.parseInt(matcher.group(1));
        String letra = matcher.group(2);
        return letra.equals(letras[numero % 23]);
    }

    /*Metodo toString*/
    @Override
    public String toString() {
        return "Número de Identificación del Alumnado (NIA)="+nia+" nombre="+nombre+" ("+getIniciales()+"), DNI="+dni+", correo="+correo+", teléfono="+telefono+", fecha nacimiento="+fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
    }

    /*Metodo imprimir*/
    public String imprimir() {
        return String.format("NIA=%s, nombre =%s, DNI=%s", nia, nombre, dni);

    }

    private String getIniciales() {
        String[] palabras = nombre.split(" ");
        StringBuilder iniciales = new StringBuilder();
        for (String palabra : palabras) {
            iniciales.append(palabra.charAt(0));
        }
        return iniciales.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Alumno alumno = (Alumno) obj;
        return dni.equals(alumno.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}