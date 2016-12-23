
package model;

import java.util.Date;

public class Tweet {
    
    private int id;
    private String autor;
    private String text;
    private Date data;
    private double lati;
    private double longi;

    public Tweet() {
    }            
            
    public Tweet(String autor, String text, Date data, double lati, double longi) {
        this.autor = autor;
        this.text = text;
        this.data = data;
        this.lati = lati;
        this.longi = longi;
    }

    public Tweet(int id, String autor, String text, Date data, double lati, double longi) {
        this.id = id;
        this.autor = autor;
        this.text = text;
        this.data = data;
        this.lati = lati;
        this.longi = longi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Tweet) obj).id;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
