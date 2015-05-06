package com.example.nicolas.model;

import java.util.ArrayList;

/**
 * Created by Nicolas on 02/05/2015.
 */
public class Club {
    private int id;
    private String nom;
    private String local;
    private int icon;
    private String website;

    public static ArrayList<Club> CLUBLIST = new ArrayList<Club>();

    public Club(){}

    public Club(String nom, String local, Integer icon, String website)
    {
        this.nom = nom;
        this.local = local;
        this.icon = icon;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
