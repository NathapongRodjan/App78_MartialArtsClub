package com.example.nathapong.app78_martialartsclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nathapong.app78_martialartsclub.Model.DatabaseHandler;
import com.example.nathapong.app78_martialartsclub.Model.MartialArt;

public class AddMatialArtActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtName, edtPrice, edtColor;
    Button btnAddMartialArt, btnGoBack;

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matial_art);

        edtName = (EditText)findViewById(R.id.edtName);
        edtPrice = (EditText)findViewById(R.id.edtPrice);
        edtColor = (EditText)findViewById(R.id.edtColor);
        btnAddMartialArt = (Button)findViewById(R.id.btnAddMartialArt);
        btnGoBack = (Button)findViewById(R.id.btnGoBack);

        databaseHandler = new DatabaseHandler(AddMatialArtActivity.this);

        btnAddMartialArt.setOnClickListener(AddMatialArtActivity.this);
        btnGoBack.setOnClickListener(AddMatialArtActivity.this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnAddMartialArt :
                addMartialObjectToDatabase();
                break;

            case R.id.btnGoBack :
                this.finish();
                break;
        }
    }


    private void addMartialObjectToDatabase(){

        String nameValue = edtName.getText().toString();
        String priceValue = edtPrice.getText().toString();
        String colorValue = edtColor.getText().toString();

        try{

            double priceDoubleValue = Double.parseDouble(priceValue);

            MartialArt martialArtObject = new MartialArt(0, nameValue, priceDoubleValue, colorValue);

            databaseHandler.addMartialArt(martialArtObject);

            Toast.makeText(AddMatialArtActivity.this,
                    martialArtObject.toString() + " Martial Art Object is added to Database",
                    Toast.LENGTH_SHORT).show();

            edtName.setText("");
            edtPrice.setText("");
            edtColor.setText("");

        }catch (Exception e){

            e.printStackTrace();
        }
    }


}
