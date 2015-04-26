package com.palardnicolasgmail.virus;

import java.util.ArrayList;

/**
 * Created by Nicolas_2 on 26/04/2015.
 */
public class VMyCode {

    private String name;
    private ArrayList<GeneticStep> code;
    private int image;

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public ArrayList<GeneticStep> getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(ArrayList<GeneticStep> code) {
        this.code = code;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
