package com.example.ds2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ListView lvEtudiants;
    SQLiteDatabase sqlDb;
    public static int RESULT_OK_FROM_INFO = 2019;
    Vector itemValues;
    customer cAdapter;
    ArrayList listEtudiants = new ArrayList();

   /* String strtitre ;
    String strauteur;
    String strediteur;
    String strDatepub;
    String strisbn ;
    String strnbrpages;*/
   String id1;
    String titre1 ;
    String auteur;
    String editeur ;
    String datepub ;
    String isbn ;
    String nbrpages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cAdapter = new customer(this, listEtudiants, getLayoutInflater());
         lvEtudiants = findViewById(R.id.lvEtudiants);
        lvEtudiants.setAdapter(cAdapter);
       // Toast.makeText(this, strtitre+ strauteur+ strediteur, Toast.LENGTH_SHORT).show();






        //appui simple :
        lvEtudiants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                  Vector itemValues = (Vector) lvEtudiants.getItemAtPosition(position);

               Intent i = new Intent(MainActivity.this, info2.class);
                i.putExtra("id", itemValues.get(0).toString());
                i.putExtra("titre", itemValues.get(1).toString());
                i.putExtra("auteur", itemValues.get(2).toString());
                i.putExtra("editeur", itemValues.get(3).toString());
                i.putExtra("datepub", itemValues.get(4).toString());
                i.putExtra("isbn", itemValues.get(5).toString());
                i.putExtra("nbrpages", itemValues.get(6).toString());
              startActivity(i);

        }
        });


        //appui long :
        lvEtudiants.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                   int position, long id) {

                       // System.out.println("Long click : pos=" + position);
                        Vector itemValues = (Vector) parent.getItemAtPosition(position);
                      //  String args[] = new String[]{ itemValues.get(1).toString(), itemValues.get(2).toString()};
                      //  sqlDb.delete("contact", "titre=? AND auteur=?", args);

                       //sqlDb.delete("contact", "id="+ position+" ", null);
                        String args[] = new String[]{itemValues.get(1).toString(), itemValues.get(2).toString()};
                        sqlDb.delete("person", "titre=? AND auteur=?", args);

                        listEtudiants.remove(position);//remove the et-èèudiant who has the index
                        cAdapter.notifyDataSetChanged();
                        return true;
                    }
                });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.miAjouter:
                Intent intent = new Intent( MainActivity.this,AddBookActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.miQuitter:
                finish();
                break;
        }
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

               // String strid = data.getStringExtra("id");
               String strtitre = data.getStringExtra("titre");
               String strauteur = data.getStringExtra("auteur");
               String strediteur = data.getStringExtra("editeur");
                String strDatepub = data.getStringExtra("datepub");
                String strisbn = data.getStringExtra("isbn");
               String strnbrpages = data.getStringExtra("nbrpages");
            /*    Vector itemValues = new Vector();
                itemValues.add(strtitre);
                itemValues.add(strauteur);
                itemValues.add(strediteur);
                itemValues.add(strDatepub);
                itemValues.add(strisbn);
                itemValues.add(strnbrpages);

                listEtudiants.add(itemValues);
                cAdapter.notifyDataSetChanged();*/

                //creation BD
              sqlDb = openOrCreateDatabase("mydatabase.db", Context.MODE_PRIVATE,null);

                sqlDb.execSQL("CREATE TABLE IF NOT EXISTS person (id INTEGER "
                        + " PRIMARY KEY AUTOINCREMENT, titre VARCHAR, auteur VARCHAR, editeur VARCHAR, datepub VARCHAR , isbn VARCHAR, nbrpages VARCHAR );");

                ContentValues values = new ContentValues();
                values.put("titre", String.valueOf(strtitre));
                values.put("auteur", String.valueOf(strauteur));
                values.put("editeur", String.valueOf(strediteur));
                values.put("datepub", String.valueOf(strDatepub));
                values.put("isbn", String.valueOf(strisbn));
                values.put("nbrpages", String.valueOf(strnbrpages));

                long id = sqlDb.insert("person", null, values);

                //sqlDb.insert() retourn -1 au cas d'erreur
                if (id == -1) {
                    Toast.makeText(this, "Erreur d'insertion dans la base", Toast.LENGTH_SHORT).show();
                }

                // lecture
                Cursor resultSet = sqlDb.rawQuery("SELECT * FROM person", null);
                if (resultSet.moveToFirst()) {
                    //lire une ligne et verifier s’il y en a d’autres
                do{
                        String id1 = resultSet.getString(0);
                        String titre1 = resultSet.getString(1);
                        String auteur = resultSet.getString(2);
                        String editeur = resultSet.getString(3);
                        String datepub = resultSet.getString(4);
                        String isbn = resultSet.getString(5);
                        String nbrpages = resultSet.getString(6);
                        itemValues = new Vector();
                        itemValues.add(id1);
                        itemValues.add(titre1);
                        itemValues.add(auteur);
                        itemValues.add(editeur);
                        itemValues.add(datepub);
                        itemValues.add(isbn);
                        itemValues.add(nbrpages);
                    listEtudiants.add(itemValues);
                    cAdapter.notifyDataSetChanged();
                    } while (resultSet.moveToNext());

                }
                resultSet.close();

            } else if (resultCode == RESULT_CANCELED) {
                String message = data.getStringExtra("message");
             //   Toast.makeText(this, message + " ok ", Toast.LENGTH_SHORT).show();
            }
        //}
    }
   /* public void gotoDetailEtudiant(Vector itemValues, int position){
      //  Vector
        itemValues = (Vector) lvEtudiants.getItemAtPosition(position);

        Intent mIntent = new Intent(MainActivity.this, BookInfoActivity.class);
        mIntent.putExtra("indexEtudiant", position);
        mIntent.putExtra("titre", itemValues.get(1).toString());
        mIntent.putExtra("auteur", itemValues.get(2).toString());
        mIntent.putExtra("editeur", itemValues.get(3).toString());
        mIntent.putExtra("datepub", itemValues.get(4).toString());
        mIntent.putExtra("isbn", itemValues.get(5).toString());
        mIntent.putExtra("nbrpages", itemValues.get(6).toString());
        startActivity(mIntent);

    }*/
   public void gotoDetailEtudiant(Vector itemValues, int position){
    //   Vector itemValues = (Vector) lvEtudiants.getItemAtPosition(position);
       Intent mIntent = new Intent(MainActivity.this, info2.class);
       mIntent.putExtra("indexEtudiant", position);
       mIntent.putExtra("titre", itemValues.get(1).toString());
       mIntent.putExtra("auteur", itemValues.get(2).toString());
       mIntent.putExtra("editeur", itemValues.get(3).toString());
       mIntent.putExtra("datepub", itemValues.get(4).toString());
       mIntent.putExtra("isbn", itemValues.get(5).toString());
       mIntent.putExtra("nbrpages", itemValues.get(6).toString());
       startActivityForResult(mIntent, 0);

   }
 /*   public void gotoDetailEtudiant(Vector etudiant, int position) {

        // lecture
        Cursor resultSet = sqlDb.rawQuery("SELECT * FROM person where  'id='+ position +'' ", null);
        if (resultSet.moveToFirst()) {
            //lire une ligne et verifier s’il y en a d’autres
                String id1 = resultSet.getString(0);
                String titre1 = resultSet.getString(1);
                String auteur = resultSet.getString(2);
                String editeur = resultSet.getString(3);
                String datepub = resultSet.getString(4);
                String isbn = resultSet.getString(5);
                String nbrpages = resultSet.getString(6);

                Vector itemValues = new Vector();
                itemValues.add(id1);
                itemValues.add(titre1);
                itemValues.add(auteur);
                itemValues.add(editeur);
                itemValues.add(datepub);
                itemValues.add(isbn);
                itemValues.add(nbrpages);

                Intent mIntent = new Intent(MainActivity.this, BookInfoActivity.class);
                mIntent.putExtra("indexEtudiant", position);
                mIntent.putExtra("titre", itemValues.get(1).toString());
                mIntent.putExtra("auteur", itemValues.get(2).toString());
                mIntent.putExtra("editeur", itemValues.get(3).toString());
                mIntent.putExtra("datepub", itemValues.get(4).toString());
                mIntent.putExtra("isbn", itemValues.get(5).toString());
                mIntent.putExtra("nbrpages", itemValues.get(6).toString());
                startActivityForResult(mIntent, 0);

        }

    }*/

}