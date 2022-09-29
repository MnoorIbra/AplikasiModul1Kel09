package com.example.aplikasimodul1kel09;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button btnLogoutMain;
    private Button exit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.listview);
        // inisiasi method listview agar dapat mengisi listview dengan array yang ada pada file mainactivity.java
        String[] namaAnggota = {"               Kelompok 09 Praktikum MDP", // mengisikan array bernama nama anggota
                                "Juliant Raffa - 21120120130127",
                                "M. Yoga Ainur Rofiq - 21120120120005",
                                "Ida Bagus Putu P. M. - 21120120140108",
                                "M. Noor Ibrahim - 21120120140064",};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, namaAnggota);
        // array adapter disni berfungsi untuk menampung data array yang kita tuliskan tadi agar dapat
        // dimasukkan ke listview, setelah itu array adapter kita beri parameter this lalu android.r.layout.simple_list_item_1
        // yang berfungsi nantinya tampilannya dalam bentuk simple list item, lalu ada parameter terakhir yaitu array yang kaan kita tampilkan ke listview
        list.setAdapter(myAdapter);
        // list ini nantinya akan di set dengan array adapter guna agar list dapat menampilkan array yang sudah kita buat
        initView();
        initPreference();
        logout();
    }
    @Override
    public void onBackPressed() {
        showAlertDialog();
    }
    private void initView() {

        btnLogoutMain =
                findViewById(R.id.btnLogoutMain);
        exit = findViewById(R.id.btnLogoutMain);
    }
    private void initPreference() {
        SharedPreferences preferences =
                getSharedPreferences("LoginPreference", MODE_PRIVATE);
        String username =
                preferences.getString("username", "");

    }
    private void deletePreference(){
        SharedPreferences preferences =
                getSharedPreferences("LoginPreference", MODE_PRIVATE);
        preferences.edit().remove("username").commit();
        preferences.edit().remove("password").commit();
    }
    private void logout() {
        exit.setOnClickListener(view ->
                showAlertDialog());
    }
    public void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Apa kalian ingin Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface
                                                        dialog, int id) {

                                deletePreference();
                                Intent login = new
                                        Intent(MainActivity.this, LoginActivity.class);
                                startActivity(login);

                                finish();

                            }
                        })
                .setNegativeButton("No", null)
                .show();
    }
}
