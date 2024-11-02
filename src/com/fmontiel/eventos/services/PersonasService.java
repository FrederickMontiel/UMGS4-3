/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.eventos.services;

/**
 *
 * @author PC
 */
import com.fmontiel.eventos.models.EventoModel;
import com.fmontiel.eventos.models.PersonaModel;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonasService {

    private static final String BASE_URL = "http://localhost:8080/api";

    public PersonaModel addPersonaToEvento(Long eventoId, Long cui, String nombre, String email, String telefono) throws IOException {
        JSONObject personaData = new JSONObject();
        personaData.put("id", cui);
        personaData.put("nombre", nombre);
        personaData.put("email", email);
        personaData.put("telefono", telefono);

        Connection.Response response = Jsoup.connect(BASE_URL + "/eventos/" + eventoId + "/personas")
                .ignoreContentType(true)
                .header("Content-Type", "application/json")
                .requestBody(personaData.toString())
                .method(Connection.Method.POST)
                .execute();

        JSONObject jsonResponse = new JSONObject(response.body());
        return parsePersona(jsonResponse);
    }

    public List<PersonaModel> getPersonasByEvento(Long eventoId) throws IOException {
        Connection.Response response = Jsoup.connect(BASE_URL + "/eventos/" + eventoId + "/personas")
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .execute();

        JSONArray jsonArray = new JSONArray(response.body());
        List<PersonaModel> personas = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            personas.add(parsePersona(jsonArray.getJSONObject(i)));
        }
        return personas;
    }

    private PersonaModel parsePersona(JSONObject jsonObject) {
        PersonaModel persona = new PersonaModel();
        persona.setId(jsonObject.getLong("id"));
        persona.setNombre(jsonObject.getString("nombre"));
        persona.setEmail(jsonObject.getString("email"));
        persona.setTelefono(jsonObject.getString("telefono"));

        if (jsonObject.has("evento")) {
            JSONObject eventoJson = jsonObject.getJSONObject("evento");
            EventoModel evento = new EventoModel();
            evento.setId(eventoJson.getLong("id"));
            evento.setNombre(eventoJson.getString("nombre"));
            evento.setUbicacion(eventoJson.getString("ubicacion"));
            evento.setDescripcion(eventoJson.getString("descripcion"));
            evento.setOrganizador(eventoJson.getString("organizador"));
            evento.setFechaEvento(new Date(eventoJson.getString("fechaEvento")));
            persona.setEvento(evento);
        }

        return persona;
    }
    
    public PersonaModel updatePersonaInEvento(Long eventoId, Long personaId, PersonaModel persona) throws IOException {
        JSONObject personaData = new JSONObject();
        personaData.put("nombre", persona.getNombre());
        personaData.put("email", persona.getEmail());
        personaData.put("telefono", persona.getTelefono());

        Connection.Response response = Jsoup.connect(BASE_URL + "/eventos/" + eventoId + "/personas/" + personaId)
                .ignoreContentType(true)
                .header("Content-Type", "application/json")
                .requestBody(personaData.toString())
                .method(Connection.Method.PUT)
                .execute();

        JSONObject jsonResponse = new JSONObject(response.body());
        return parsePersona(jsonResponse);
    }

    public boolean removePersonaFromEvento(Long idEvento, long personaId) throws IOException {
        Connection.Response response = Jsoup.connect(BASE_URL + "/eventos/" + idEvento + "/personas/" + personaId)
                .ignoreContentType(true)
                .method(Connection.Method.DELETE)
                .execute();

        return response.statusCode() == 200;
    }
}