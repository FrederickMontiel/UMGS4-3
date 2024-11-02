/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.eventos.services;


import com.fmontiel.eventos.models.EventoModel;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventosService {

    private static final String BASE_URL = "https://rocky-island-06241-a53a9407b1c6.herokuapp.com/api";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public List<EventoModel> getAllEventos() throws IOException {
        Connection.Response response = Jsoup.connect(BASE_URL + "/eventos")
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .execute();

        JSONArray jsonArray = new JSONArray(response.body());
        List<EventoModel> eventos = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            eventos.add(parseEvento(jsonArray.getJSONObject(i)));
        }
        return eventos;
    }

    public EventoModel getEventoById(Long id) throws IOException {
        Connection.Response response = Jsoup.connect(BASE_URL + "/eventos/" + id)
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .execute();

        JSONObject jsonResponse = new JSONObject(response.body());
        return parseEvento(jsonResponse);
    }

    public EventoModel createEvento(String nombre, String ubicacion, String descripcion, String organizador, Date fechaEvento) throws IOException {
        JSONObject eventoData = new JSONObject();
        eventoData.put("nombre", nombre);
        eventoData.put("ubicacion", ubicacion);
        eventoData.put("descripcion", descripcion);
        eventoData.put("organizador", organizador);
        eventoData.put("fechaEvento", DATE_FORMAT.format(fechaEvento));

        Connection.Response response = Jsoup.connect(BASE_URL + "/eventos")
                .ignoreContentType(true)
                .header("Content-Type", "application/json")
                .requestBody(eventoData.toString())
                .method(Connection.Method.POST)
                .execute();

        JSONObject jsonResponse = new JSONObject(response.body());
        return parseEvento(jsonResponse);
    }

    public EventoModel updateEvento(Long id, String nombre, String ubicacion, String descripcion, String organizador, Date fechaEvento) throws IOException {
        JSONObject eventoData = new JSONObject();
        eventoData.put("nombre", nombre);
        eventoData.put("ubicacion", ubicacion);
        eventoData.put("descripcion", descripcion);
        eventoData.put("organizador", organizador);
        eventoData.put("fechaEvento", DATE_FORMAT.format(fechaEvento));

        Connection.Response response = Jsoup.connect(BASE_URL + "/eventos/" + id)
                .ignoreContentType(true)
                .header("Content-Type", "application/json")
                .requestBody(eventoData.toString())
                .method(Connection.Method.PUT)
                .execute();

        JSONObject jsonResponse = new JSONObject(response.body());
        return parseEvento(jsonResponse);
    }

    public boolean deleteEvento(Long id) throws IOException {
        Connection.Response response = Jsoup.connect(BASE_URL + "/eventos/" + id)
                .ignoreContentType(true)
                .method(Connection.Method.DELETE)
                .execute();
        
        return response.statusCode() == 200;
    }

    private EventoModel parseEvento(JSONObject jsonObject) {
        EventoModel evento = new EventoModel();
        evento.setId(jsonObject.getLong("id"));
        evento.setNombre(jsonObject.getString("nombre"));
        evento.setUbicacion(jsonObject.getString("ubicacion"));
        evento.setDescripcion(jsonObject.getString("descripcion"));
        evento.setOrganizador(jsonObject.getString("organizador"));

        try {
            evento.setFechaEvento(DATE_FORMAT.parse(jsonObject.getString("fechaEvento")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return evento;
    }
}
