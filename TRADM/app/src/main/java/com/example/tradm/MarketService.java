package com.example.tradm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class MarketService extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference offerRef = db.collection("Offer");

    private Button button_choose_item;

    private OfferAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_service);

        FloatingActionButton buttonAddOffer = findViewById(R.id.button_add_offer);
        buttonAddOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketService.this, NewOfferActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton buttonPersonal = findViewById(R.id.button_personal);
        buttonPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketService.this, PersonSell.class);
                startActivity(intent);
            }
        });

        setUpRecyclerView();
        button_choose_item = findViewById(R.id.choose_item);
        button_choose_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketService.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpRecyclerView(){
        Query query = offerRef.orderBy("title", Query.Direction.ASCENDING).whereEqualTo("offerType", "Service").whereEqualTo("offerStatus","Available");

        FirestoreRecyclerOptions<Offer> options = new FirestoreRecyclerOptions.Builder<Offer>()
                .setQuery(query, Offer.class)
                .build();

        adapter = new OfferAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OfferAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(MarketService.this, ServiceDetail.class);
                intent.putExtra("OfferPath", path);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
