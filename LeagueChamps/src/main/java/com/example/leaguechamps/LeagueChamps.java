package com.example.leaguechamps;

public class LeagueChamps {
    private String campeao;
    private int maestria;
    private String cidade;


    public LeagueChamps(String campeao, int maestria, String cidade) {
        this.campeao = campeao;
        this.maestria = maestria;
        this.cidade = cidade;
    }

    // Getters e Setters
    public String getCampeao() {
        return campeao;
    }

    public void setCampeao(String campeao) {
        this.campeao = campeao;
    }

    public int getMaestria() {
        return maestria;
    }

    public void setMaestria(int Maestria) {
        this.maestria = maestria;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}



