package com.example.tradm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class OfferDetail extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "OfferDetail";
    private TextView textViewTitle;
    private TextView textViewDescription;
<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm/OfferDetail.java
<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm/OfferDetail.java
=======
    private TextView textViewOfferType;
    private TextView textViewOfferStatus;
>>>>>>> parent of 3db3a08... Final Commit Maybe?:MarketPlace and Offer List/app/src/main/java/com/example/tradm/OfferDetail.java
    private TextView textViewPrice;
=======
    private TextView textViewOfferType;
    private TextView textViewOfferStatus;
>>>>>>> parent of 891c119... zip added:MarketPlace and Offer List/app/src/main/java/com/example/tradm/OfferDetail.java
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("View Offer Details");

<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm/OfferDetail.java
<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm/OfferDetail.java
        textViewDescription = findViewById(R.id.textView14);
        textViewPrice = findViewById(R.id.service_name_details);
        imageView = findViewById(R.id.imageView2);
        apply = findViewById(R.id.button1);
        cancel = findViewById(R.id.button);
=======
=======
>>>>>>> parent of 3db3a08... Final Commit Maybe?:MarketPlace and Offer List/app/src/main/java/com/example/tradm/OfferDetail.java
        textViewTitle = findViewById(R.id.text_view_title_1);
        textViewDescription = findViewById(R.id.text_view_description_1);
        textViewOfferType = findViewById(R.id.text_view_offer_type_1);
        textViewOfferStatus = findViewById(R.id.text_view_offer_status_1);
<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm/OfferDetail.java
        imageView = findViewById(R.id.image_view_1);
>>>>>>> parent of 891c119... zip added:MarketPlace and Offer List/app/src/main/java/com/example/tradm/OfferDetail.java
=======
        textViewPrice = findViewById(R.id.text_view_price_1);
        imageView = findViewById(R.id.image_view_1);
>>>>>>> parent of 3db3a08... Final Commit Maybe?:MarketPlace and Offer List/app/src/main/java/com/example/tradm/OfferDetail.java

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
                        Offer offer = document.toObject(Offer.class);
                        textViewTitle.setText(offer.getTitle());
                        textViewDescription.setText(offer.getDescription());
<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm/OfferDetail.java
<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm/OfferDetail.java
=======
                        textViewOfferType.setText(offer.getOfferType());
                        textViewOfferStatus.setText(offer.getOfferStatus().name());
>>>>>>> parent of 3db3a08... Final Commit Maybe?:MarketPlace and Offer List/app/src/main/java/com/example/tradm/OfferDetail.java
                        textViewPrice.setText(String.valueOf(offer.getPrice()));
=======
                        textViewOfferType.setText(offer.getOfferType());
                        textViewOfferStatus.setText(offer.getOfferStatus().name());
>>>>>>> parent of 891c119... zip added:MarketPlace and Offer List/app/src/main/java/com/example/tradm/OfferDetail.java
                        Picasso.get().load(offer.getUpload().getImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
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
