package com.example.tradm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class PersonBuy extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference offerRef = db.collection("Offer");

    private Button button_choose_sell;

    private OfferAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_buy);

        FloatingActionButton buttonAddOffer = findViewById(R.id.button_add_offer);
        buttonAddOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonBuy.this, NewOfferActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton buttonHome = findViewById(R.id.button_personal);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonBuy.this, MarketItem.class);
                startActivity(intent);
            }
        });

        setUpRecyclerView();
        button_choose_sell = findViewById(R.id.sell);

        button_choose_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonBuy.this, PersonSell.class);
                startActivity(intent);
            }
        });
    }

    private void setUpRecyclerView(){
        Query query = offerRef.orderBy("title", Query.Direction.ASCENDING).whereEqualTo("buyerID", 1);

        FirestoreRecyclerOptions<Offer> options = new FirestoreRecyclerOptions.Builder<Offer>()
                .setQuery(query, Offer.class)
                .build();

        adapter = new OfferAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new OfferAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Offer offer = documentSnapshot.toObject(Offer.class);
                //String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
                Intent intent = new Intent(PersonBuy.this, OfferDetail.class);
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
