package com.demo.examen4.entity;

public class Gato {
    private int id;
    private String imagen;
    private String nombre;
    private String raza;
    private String edad;
    private String comida;
    private String pais;
    private String favorito;

    public Gato() {
    }

    public Gato(int id, String imagen, String nombre, String raza, String edad, String comida, String pais, String favorito) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.comida = comida;
        this.pais = pais;
        this.favorito = favorito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFavorito() {
        return favorito;
    }

    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }
}
