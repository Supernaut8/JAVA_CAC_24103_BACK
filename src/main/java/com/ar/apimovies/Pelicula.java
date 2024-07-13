package com.ar.apimovies;

public class Pelicula {
    
    private int codPelicula;
    private String nombre;
    private String genero;
    private String duracion;
    private String clasificacion;
    private int valoracion;
    private String imagen;

    public Pelicula(int codPelicula, String nombre, String genero, String duracion, String clasificacion,
            int valoracion, String imagen) {
        this.codPelicula = codPelicula;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.valoracion = valoracion;
        this.imagen = imagen;
    }
    public Pelicula() {
    }

    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Pelicula [codPelicula=" + codPelicula + ", nombre=" + nombre + ", genero=" + genero + ", duracion="
                + duracion + ", clasificacion=" + clasificacion + ", valoracion=" + valoracion + ", imagen=" + imagen
                + "]";
    }

    // public void add(Pelicula pelicula) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'add'");
    // }
    
}
