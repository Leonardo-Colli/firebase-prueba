package com.example.firebase_prueba;

public class Citas {
    private String Titulo;
    private String Hora;
    public Citas(){

    }
    public Citas(String Titulo, String Hora) {
        this.Titulo = Titulo;
        this.Hora = Hora;
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
