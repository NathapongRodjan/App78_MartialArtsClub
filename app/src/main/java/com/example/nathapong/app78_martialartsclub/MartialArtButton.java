package com.example.nathapong.app78_martialartsclub;


import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import com.example.nathapong.app78_martialartsclub.Model.MartialArt;

public class MartialArtButton extends AppCompatButton{

    private MartialArt martialArtObject;




    public MartialArtButton(Context context, MartialArt martialArt) {
        super(context);
        martialArtObject = martialArt;
    }


    public String getMartialArtColor(){
        return martialArtObject.getMartialArtColor();
    }

    public double getMartialArtPrice(){
        return martialArtObject.getMartialArtPrice();
    }
}
