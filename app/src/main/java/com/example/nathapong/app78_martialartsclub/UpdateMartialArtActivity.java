package com.example.nathapong.app78_martialartsclub;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nathapong.app78_martialartsclub.Model.DatabaseHandler;
import com.example.nathapong.app78_martialartsclub.Model.MartialArt;

import java.util.ArrayList;

public class UpdateMartialArtActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_art);

        databaseHandler = new DatabaseHandler(UpdateMartialArtActivity.this);

        modifyUserInterface();
    }

    private void modifyUserInterface(){

        ArrayList<MartialArt> martialArtObjects = databaseHandler.returnAllMartialArtObjects();

        if (martialArtObjects.size() > 0){

            ScrollView scrollView = new ScrollView(UpdateMartialArtActivity.this);
            GridLayout gridLayout = new GridLayout(UpdateMartialArtActivity.this);
            gridLayout.setRowCount(martialArtObjects.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[martialArtObjects.size()];
            EditText[][] edtNamePricesAndColor = new EditText[martialArtObjects.size()][3];
            Button[] modifyButton = new Button[martialArtObjects.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWidth = screenSize.x;
            int index = 0;

            for (MartialArt martialArtObject : martialArtObjects){

                idTextViews[index] = new TextView(UpdateMartialArtActivity.this);
                idTextViews[index].setGravity(Gravity.CENTER);
                idTextViews[index].setText(martialArtObject.getMartialArtID() + "");

                edtNamePricesAndColor[index][0] = new EditText(UpdateMartialArtActivity.this);
                edtNamePricesAndColor[index][1] = new EditText(UpdateMartialArtActivity.this);
                edtNamePricesAndColor[index][2] = new EditText(UpdateMartialArtActivity.this);

                edtNamePricesAndColor[index][0].setText(martialArtObject.getMartialArtName());
                edtNamePricesAndColor[index][1].setText(martialArtObject.getMartialArtPrice() + "");
                edtNamePricesAndColor[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamePricesAndColor[index][2].setText(martialArtObject.getMartialArtColor());

                edtNamePricesAndColor[index][0].setId(martialArtObject.getMartialArtID() + 10);
                edtNamePricesAndColor[index][1].setId(martialArtObject.getMartialArtID() + 20);
                edtNamePricesAndColor[index][2].setId(martialArtObject.getMartialArtID() + 30);

                modifyButton[index] = new Button(UpdateMartialArtActivity.this);
                modifyButton[index].setText("Modify");
                modifyButton[index].setId(martialArtObject.getMartialArtID());
                modifyButton[index].setOnClickListener(UpdateMartialArtActivity.this);

                gridLayout.addView(idTextViews[index], (int)(screenWidth * 0.05),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                gridLayout.addView(edtNamePricesAndColor[index][0], (int)(screenWidth * 0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePricesAndColor[index][1], (int)(screenWidth * 0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePricesAndColor[index][2], (int)(screenWidth * 0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                gridLayout.addView(modifyButton[index], (int)(screenWidth * 0.35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                index++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);

        }
    }

    @Override
    public void onClick(View v) {

        int martialArtObjectID = v.getId();
        EditText edtMartialArtName = (EditText)findViewById(martialArtObjectID + 10);
        EditText edtMartialArtPrice = (EditText)findViewById(martialArtObjectID + 20);
        EditText edtMartialArtColor = (EditText)findViewById(martialArtObjectID + 30);

        String martialNameStringValue = edtMartialArtName.getText().toString();
        String martialPriceStringValue = edtMartialArtPrice.getText().toString();
        String martialColorStringValue = edtMartialArtColor.getText().toString();

        try{

            //double martialArtPriceDoubleValue = Double.parseDouble(martialPriceStringValue);

            databaseHandler.modifyMartialArtObject( martialArtObjectID,
                                                    martialNameStringValue,
                                                    Double.parseDouble(martialPriceStringValue),
                                                    martialColorStringValue );

            Toast.makeText(UpdateMartialArtActivity.this,
                    "This Martial Object is Updated.",Toast.LENGTH_SHORT).show();

        }catch (NumberFormatException e){

            e.printStackTrace();
        }

    }
}
