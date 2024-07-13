package com.ar.apimovies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {

    public Long insertarPelicula(Pelicula pelicula) {
        
        String insertPeliculaSql = "INSERT INTO pelicula (nombre, genero, duracion, clasificacion, valoracion, imagen) VALUES(?,?,?,?,?,?)";

        DataBasesConnection conexion = new DataBasesConnection();
		
		Statement stm = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

        Connection cn = conexion.conectar();

        try {
            pstm = cn.prepareStatement(insertPeliculaSql);    

            pstm.setString(1, pelicula.getNombre()); 
            pstm.setString(2, pelicula.getGenero()); 
            pstm.setString(3, pelicula.getDuracion()); 
            pstm.setString(4, pelicula.getClasificacion()); 
            pstm.setInt(5, pelicula.getValoracion());  
            pstm.setString(6, pelicula.getImagen()); 

            int result = pstm.executeUpdate();

            if (result > 0) {
                rs = pstm.getGeneratedKeys();

                if (rs.next()) {
                    System.err.println("Pelicula fue insertada correctamente");
                    return rs.getLong(1);
                }
                else {
                    System.out.println("Error al obtener el ID de la pelicula insertada");
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<Pelicula> getAllPeliculas() {

        String selectAllPeliculaSql = "SELECT * FROM pelicula";

        DataBasesConnection conexion = new DataBasesConnection();

        List<Pelicula> peliculas = new ArrayList<>();

        Statement stm = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Connection cn = conexion.conectar();

        try {
            
            pstm = cn.prepareStatement(selectAllPeliculaSql);
            // stm = cn.createStatement();
            
            rs = pstm.executeQuery();
            // rs = stm.executeQuery(selectAllPeliculaSql);

            while (rs.next()) {
                int idPelicula = rs.getInt("codPelicula");
                String nombre = rs.getString("nombre");
                String genero = rs.getString("genero");
                String duracion = rs.getString("duracion");
                String clasificacion = rs.getString("clasificacion");
                int valoracion = rs.getInt("valoracion");
                String imagen = rs.getString("imagen");

                Pelicula pelicula = new Pelicula(idPelicula, nombre, genero, duracion, clasificacion, valoracion, imagen);
                
                peliculas.add(pelicula);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return peliculas;
    }
}

