package com.example.tradm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewOfferActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String offerType;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinner;
    private EditText editTextPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offer);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Offer");

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextPrice = findViewById(R.id.edit_text_price);
        spinner = findViewById(R.id.spinner_offer_type);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.offer_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_offer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_offer:
                saveOffer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveOffer(){
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        //need to add userID here
        int price = Integer.parseInt(editTextPrice.getText().toString());

        if(title.trim().isEmpty() || description.trim().isEmpty() || price <= 0){
            Toast.makeText(this, "Please insert a correct title, description, and price and select an offer type.", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference offerRef = FirebaseFirestore.getInstance().collection("Offer");
        offerRef.add(new Offer(title, description, Offer.OfferStat.Available, offerType, price)); //to change
        Toast.makeText(this, "Offer added", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        offerType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }
}
