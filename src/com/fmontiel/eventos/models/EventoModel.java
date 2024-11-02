/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fmontiel.eventos.models;

/**
 *
 * @author PC
 */
import java.util.Date;
import java.util.List;

public class EventoModel {
    private Long id;
    private String nombre;
    private String ubicacion;
    private String descripcion;
    private String organizador;
    private Date fechaEvento;
    private Date fechaCreacion;
    private List<PersonaModel> personas;

    public EventoModel() {
    }

    public EventoModel(Long id, String nombre, String ubicacion, String descripcion, String organizador, Date fechaEvento, List<PersonaModel> personas) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.organizador = organizador;
        this.fechaEvento = fechaEvento;
        this.fechaCreacion = new Date();
        this.personas = personas;
    }
    
    public EventoModel(Long id, String nombre, String ubicacion, String descripcion, String organizador, Date fechaEvento) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.organizador = organizador;
        this.fechaEvento = fechaEvento;
        this.fechaCreacion = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<PersonaModel> getPersonas() {
        return personas;
    }

    public void setPersonas(List<PersonaModel> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "Evento [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", descripcion=" + descripcion + 
               ", organizador=" + organizador + ", fechaEvento=" + fechaEvento + ", fechaCreacion=" + fechaCreacion + 
               ", personas=" + personas + "]";
    }
}
