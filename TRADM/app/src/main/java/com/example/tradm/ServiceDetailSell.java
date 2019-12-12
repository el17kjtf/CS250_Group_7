package com.example.tradm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ServiceDetailSell extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "OfferDetail";
    private Offer offer;
    private TextView textViewDescription;
    private TextView textViewPrice;
    private ImageView imageView;
    private Button cancel;
    private Button apply;

    private ImageView imageViewPic;
    private TextView textViewID;
    private TextView textViewEmail;

    private Member member;
    private Intent intentUser = getIntent();
    private String pathUser = intentUser.getStringExtra("User");
    DocumentReference documentReferenceUser = db.document(pathUser);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail_sell);

        setTitle("View Offer Details");

        textViewDescription = findViewById(R.id.textView14);
        textViewPrice = findViewById(R.id.service_name_details);
        imageView = findViewById(R.id.imageView2);
        apply = findViewById(R.id.button1);
        cancel = findViewById(R.id.button);

        imageViewPic = findViewById(R.id.prof_picture);
        textViewID = findViewById(R.id.item_content);
        textViewEmail = findViewById(R.id.student_email);

        Picasso.get().load(R.mipmap.ic_launcher).fit().centerCrop().into(imageViewPic);
        textViewID.setText(member.getID());
        textViewEmail.setText(member.getEmail());

        Intent intent = getIntent();
        String path = intent.getStringExtra("OfferPath");

        DocumentReference documentReference = db.document(path);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        offer = document.toObject(Offer.class);
                        textViewDescription.setText(offer.getDescription());
                        textViewPrice.setText(String.valueOf(offer.getPrice()));
                        Picasso.get().load(offer.getUpload().getImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* offer.setBuyerID(); set buyer */
                offer.setOfferStatus(Offer.OfferStat.Sold);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offer.setBuyerID(0);
                offer.setOfferStatus(Offer.OfferStat.Available);
                finish();
            }
        });

        documentReferenceUser.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        member = document.toObject(Member.class);
                    }
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

}
