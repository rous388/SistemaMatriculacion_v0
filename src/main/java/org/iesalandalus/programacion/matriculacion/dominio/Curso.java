package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {
    PRIMERO("Primero"),
    SEGUNDO("Segundo");

    private final String cadenaAMostrar;

    Curso(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    /*Metodo imprimir*/
    public String imprimir() {
        return this.ordinal() + ".-" + this.cadenaAMostrar;
    }

    /*Metodo toString*/
    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
