package com.example.ds2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    EditText titre, auteur , editeur , datePub , isbn , nombrePages;
    Button btnAjouter , btnAnnuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        titre = findViewById(R.id.edtAddBookTitre);
        auteur = findViewById(R.id.edtAddBookAuteur);
        editeur = findViewById(R.id.edtAddBookEditeur);
        datePub = findViewById(R.id.edtAddBookDatepub);
        isbn = findViewById(R.id.edtAddBookISBN);
        nombrePages = findViewById(R.id.edtAddBooknbrpages);

        btnAjouter = findViewById(R.id.btnAjouter);
        btnAnnuler = findViewById(R.id.btnAnnuler);

         btnAjouter.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 operationAjouter() ;

             }
         });
         btnAnnuler.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 operationAnnuler();
             }
         });

    }
    void operationAjouter() {
            Intent intent = new Intent();
            //intent.putExtra("message",titre.getText().toString() + " " + auteur.getText().toString() + " " + editeur.getText().toString() + " " + datePub.getText().toString()+ " " + isbn.getText().toString()+ " " + nombrePages.getText().toString() );
            intent.putExtra("titre", titre.getText().toString());
            intent.putExtra("auteur", auteur.getText().toString());
            intent.putExtra("editeur", editeur.getText().toString());
            intent.putExtra("datepub", datePub.getText().toString());
            intent.putExtra("isbn", isbn.getText().toString());
            intent.putExtra("nbrpages", nombrePages.getText().toString());
           // Toast.makeText(AddBookActivity.this, "Done", Toast.LENGTH_LONG).show();
            setResult(Activity.RESULT_OK, intent);
            finish();

    }
    void operationAnnuler() {
        Intent intent = new Intent();
        intent.putExtra("message", "Annuler");
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}