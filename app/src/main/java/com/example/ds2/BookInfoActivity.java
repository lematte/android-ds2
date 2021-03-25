/*package com.example.ds2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookInfoActivity extends AppCompatActivity {

    TextView ID, titre, auteur , editeur , datePub , isbn , nombrePages, tv1 ,tv2, tv3 , tv4 ,tv5 , tv6 , tv7 ;
    int id = -1 ;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);

        ID = findViewById(R.id.edtBookInfoID);
        titre = findViewById(R.id.Titre);
        auteur = findViewById(R.id.Auteur);
        editeur = findViewById(R.id.Editeur);
        datePub = findViewById(R.id.atpub);
        isbn = findViewById(R.id.ISBN);
        nombrePages = findViewById(R.id.nbrpages);
        ok = findViewById(R.id.buttonnnn);


        Intent data = getIntent();
        id = data.getIntExtra("indexEtudiant", -1);
        String strtitre = data.getStringExtra("titre");
        String strauteur = data.getStringExtra("auteur");
        String strediteur = data.getStringExtra("editeur");
        String strDatepub = data.getStringExtra("datepub");
        String strisbn = data.getStringExtra("isbn");
        String strnbrpages = data.getStringExtra("nbrpages");

        ID.setText(id);
        titre.setText(strtitre);
        auteur.setText(strauteur);
        editeur.setText(strediteur);
        datePub.setText(strDatepub);
        isbn.setText(strisbn);
        nombrePages.setText(strnbrpages);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnOnclick(v);
            }
        });

    }
    private void btnOnclick(View view) {

        Intent intent = new Intent();
            setResult(MainActivity.RESULT_OK_FROM_INFO, intent);
        finish();
    }

}*/