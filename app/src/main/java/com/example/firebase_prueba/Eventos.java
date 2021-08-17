package com.example.firebase_prueba;

public class Eventos {
    private String Titulo;
    private String Hora;
    private int lunes,martes,miercoles,jueves,viernes,sabado,domingo;
    public Eventos(){

    }
    public Eventos(String Titulo, String Hora, int Lunes, int Martes, int Miercoles, int Jueves, int Viernes, int Sabado, int Domingo) {
        this.Titulo = Titulo;
        this.Hora = Hora;
        this.lunes = Lunes;
        this.martes = Martes;
        this.miercoles = Miercoles;
        this.jueves = Jueves;
        this.viernes = Viernes;
        this.sabado = Sabado;
        this.domingo = Domingo;
    }

    public int getLunes() {
        return lunes;
    }

    public void setLunes(int lunes) {
        this.lunes = lunes;
    }

    public int getMartes() {
        return martes;
    }

    public void setMartes(int martes) {
        this.martes = martes;
    }

    public int getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(int miercoles) {
        this.miercoles = miercoles;
    }

    public int getJueves() {
        return jueves;
    }

    public void setJueves(int jueves) {
        this.jueves = jueves;
    }

    public int getViernes() {
        return viernes;
    }

    public void setViernes(int viernes) {
        this.viernes = viernes;
    }

    public int getSabado() {
        return sabado;
    }

    public void setSabado(int sabado) {
        this.sabado = sabado;
    }

    public int getDomingo() {
        return domingo;
    }

    public void setDomingo(int domingo) {
        this.domingo = domingo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

}
