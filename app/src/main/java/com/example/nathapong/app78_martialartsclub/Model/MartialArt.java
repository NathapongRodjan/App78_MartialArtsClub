package com.example.nathapong.app78_martialartsclub.Model;



public class MartialArt {

    private int martialArtID;
    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;


    public MartialArt(int id, String name, double price, String color) {

        setMartialArtID(id);
        setMartialArtName(name);
        setMartialArtPrice(price);
        setMartialArtColor(color);
    }

    public int getMartialArtID() {
        return martialArtID;
    }

    public void setMartialArtID(int martialArtID) {
        this.martialArtID = martialArtID;
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public void setMartialArtName(String martialName) {
        this.martialArtName = martialName;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public void setMartialArtPrice(double martialPrice) {
        this.martialArtPrice = martialPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }

    @Override
    public String toString() {

        return getMartialArtName() + "\n" +
                getMartialArtPrice() + "\n" + getMartialArtColor() + "\n";
    }

    public String returnMartialArtList() {

        return "    ["+getMartialArtID()+"]  " + " " + getMartialArtName() + " / " +
                getMartialArtPrice() + " / " + getMartialArtColor();
    }
}
