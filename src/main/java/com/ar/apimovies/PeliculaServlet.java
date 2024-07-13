package com.ar.apimovies;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

//Responder a la solicitud HTTP de la URL /pelicula
@WebServlet("/pelicula")
public class PeliculaServlet extends HttpServlet {

    // Generamos la instancia de las operaciones de la base de datos
    private PeliculaDAO peliculaDAO = new PeliculaDAO();

    // Generamos una instancia de un objeto que utiliza libreria JACKSON para
    // convertir un objeto en json y viceversa
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Configurar cabeceras CORS
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token");
        // Establecer la configuración de caracteres
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Leer JSON del cuerpo de la solicitud y convertirlo en un objeto Pelicula
        Pelicula pelicula = objectMapper.readValue(req.getInputStream(), Pelicula.class);

        // Insertar la película en la base de datos
        Long codPelicula = peliculaDAO.insertarPelicula(pelicula);

        // Convertir el id a JSON
        String jsonResponse = objectMapper.writeValueAsString(codPelicula);

        // Establecer el tipo de contenido de la respuesta a JSON
        resp.setContentType("application/json");

        // Escribir la respuesta JSON
        resp.getWriter().write(jsonResponse);

        // Establecer el estado de la respuestas a 201 (Creado)
        resp.setStatus(HttpServletResponse.SC_CREATED);

        // No es necesario llamar a super.doPost(req, resp); al final, ya que podría
        // causar una exceptción "Servlet has already been committed"
        // super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Configurar cabeceras CORS
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Establecer la configuración de caracteres
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            // Obtener las peliculas
            List<Pelicula> peliculas = peliculaDAO.getAllPeliculas();

            String jsonResponse = objectMapper.writeValueAsString(peliculas);
            // Establecer el tipo de contenido de la respuesta a JSON
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"ID inválido");
        }

    }
}
