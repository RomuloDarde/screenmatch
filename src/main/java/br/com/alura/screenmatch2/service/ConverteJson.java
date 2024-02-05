package br.com.alura.screenmatch2.service;

import com.google.gson.Gson;

public class ConverteJson {
    public static <T> T obterDados (String json, Class<T> classe) {
        return new Gson().fromJson(json, classe);
    }
}
