package com.example.nathapong.app78_martialartsclub;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.nathapong.app78_martialartsclub.Model.DatabaseHandler;
import com.example.nathapong.app78_martialartsclub.Model.MartialArt;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHandler databaseHandler;
    private double totalMartialArtPrice;
    private ScrollView scrollView;
    private int martialArtButtonWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHandler = new DatabaseHandler(MainActivity.this);
        totalMartialArtPrice = 0.0;
        scrollView = (ScrollView)findViewById(R.id.scrollView);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);

        martialArtButtonWidth = screenSize.x / 2;

        modifyUserInterface();

    }

    private void modifyUserInterface(){

        ArrayList<MartialArt> allMartialArtObjects = databaseHandler.returnAllMartialArtObjects();

        scrollView.removeAllViewsInLayout();

        if (allMartialArtObjects.size() > 0){

            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allMartialArtObjects.size() + 1) / 2);
            gridLayout.setColumnCount(2);

            MartialArtButton[] martialArtButtons = new MartialArtButton[allMartialArtObjects.size()];

            int index = 0;

            for (MartialArt martialArtObject : allMartialArtObjects){

                martialArtButtons[index] = new MartialArtButton(MainActivity.this, martialArtObject);

                martialArtButtons[index].setText(martialArtObject.getMartialArtID() + "\n" +
                                                 martialArtObject.getMartialArtName()+"\n" +
                                                 martialArtObject.getMartialArtPrice());

                switch (martialArtObject.getMartialArtColor()){

                    case "Red" :
                        martialArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "Blue" :
                        martialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "Black" :
                        martialArtButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "Yellow" :
                        martialArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "Purple" :
                        martialArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "Green" :
                        martialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "White" :
                        martialArtButtons[index].setBackgroundColor(Color.WHITE);
                        break;
                    default :
                        martialArtButtons[index].setBackgroundColor(Color.GRAY);
                        break;
                }

                martialArtButtons[index].setOnClickListener(MainActivity.this);

                gridLayout.addView(martialArtButtons[index],
                        martialArtButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            }

            scrollView.addView(gridLayout);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){

            case R.id.add_martial_art :
                Intent addMartialArtIntent = new Intent(MainActivity.this,
                        AddMatialArtActivity.class);
                startActivity(addMartialArtIntent);
                return true;

            case R.id.delete_martial_art :
                Intent deleteMartialArtIntent = new Intent(MainActivity.this,
                        DeleteMartialArtActivity.class);
                startActivity(deleteMartialArtIntent);
                return true;

            case R.id.update_martial_art :
                Intent updateMartialArtIntent = new Intent(MainActivity.this,
                        UpdateMartialArtActivity.class);
                startActivity(updateMartialArtIntent);
                return true;
            case R.id.martial_art_price_reset :
                totalMartialArtPrice = 0.0;
                Toast.makeText(MainActivity.this, totalMartialArtPrice + "", Toast.LENGTH_SHORT).show();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        MartialArtButton martialArtButton = (MartialArtButton) view;
        totalMartialArtPrice = totalMartialArtPrice + martialArtButton.getMartialArtPrice();
        String martialArtsPriceFormatted = NumberFormat.getCurrencyInstance().format(totalMartialArtPrice);
        Toast.makeText(MainActivity.this, martialArtsPriceFormatted, Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onResume() {
        super.onResume();

        modifyUserInterface();
    }
}
