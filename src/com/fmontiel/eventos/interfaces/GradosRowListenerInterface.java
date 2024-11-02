/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fmontiel.eventos.interfaces;

import org.json.JSONObject;

/**
 *
 * @author PC
 */
public interface GradosRowListenerInterface {
    void onFinish(JSONObject jo);

    void onVer(JSONObject jo);
}
