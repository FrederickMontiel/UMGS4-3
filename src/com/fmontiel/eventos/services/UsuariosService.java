/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.eventos.services;

/**
 *
 * @author PC
 */
import com.fmontiel.eventos.models.UsuarioModel;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.json.JSONObject;
import java.io.IOException;

public class UsuariosService {

    private static final String BASE_URL = "http://localhost:8080/api";

    public UsuarioModel login(String email, String password) throws IOException {
        JSONObject loginData = new JSONObject();
        loginData.put("email", email);
        loginData.put("password", password);

        Connection.Response response = Jsoup.connect(BASE_URL + "/user/login")
                .ignoreContentType(true)
                .header("Content-Type", "application/json")
                .requestBody(loginData.toString())
                .method(Connection.Method.POST)
                .execute();

        JSONObject jsonResponse = new JSONObject(response.body());
        return parseUsuario(jsonResponse);
    }

    public UsuarioModel register(String nombre, String email, String password, String telefono) throws IOException {
        JSONObject registerData = new JSONObject();
        registerData.put("nombre", nombre);
        registerData.put("email", email);
        registerData.put("password", password);
        registerData.put("telefono", telefono);

        Connection.Response response = Jsoup.connect(BASE_URL + "/user/register")
                .ignoreContentType(true)
                .header("Content-Type", "application/json")
                .requestBody(registerData.toString())
                .method(Connection.Method.POST)
                .execute();

        JSONObject jsonResponse = new JSONObject(response.body());
        return parseUsuario(jsonResponse);
    }

    private UsuarioModel parseUsuario(JSONObject jsonObject) {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(jsonObject.getLong("id"));
        usuario.setNombre(jsonObject.getString("nombre"));
        usuario.setEmail(jsonObject.getString("email"));
        usuario.setTelefono(jsonObject.optString("telefono", null));
        return usuario;
    }
}