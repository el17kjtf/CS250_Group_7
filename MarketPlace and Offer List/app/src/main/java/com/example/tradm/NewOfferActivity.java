package com.example.tradm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class NewOfferActivity extends AppCompatActivity{
    private Button buttonService;
    private Button buttonItem;
    private Button upload_pic;
    private Button create_offer;
    private Button cancel;
    private EditText name_fill;
    private EditText price_tab;
    private ImageView imageView6;
    private EditText description_table;

    private static final int PICK_IMAGE_REQUEST = 1;
    //private Button buttonUpload;

    private Uri imageUri;
    private Upload upload;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private StorageTask uploadTask;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offer);

        setTitle("Add Offer");

        upload_pic = findViewById(R.id.upload_pic);
        //buttonUpload = findViewById(R.id.button_upload);
        imageView6 = findViewById(R.id.imageView6);
        name_fill = findViewById(R.id.name_fill);
        description_table = findViewById(R.id.description_table);
        price_tab = findViewById(R.id.price_tab);
        buttonItem = findViewById(R.id.item);
        buttonService = findViewById(R.id.service);
        cancel = findViewById(R.id.cancel);
        create_offer = findViewById(R.id.create_offer);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        upload_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(NewOfferActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });

        buttonService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOfferActivity.this, NewService.class);
                startActivity(intent);
            }
        });

        create_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOffer();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if (imageUri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
            + "." + getFileExtension(imageUri));
            uploadTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(NewOfferActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();

                            //Log.d(TAG, "onSuccess: firebase download url: " + downloadUrl.toString()); //use if testing...don't need this line.
                            upload = new Upload(downloadUrl.toString());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NewOfferActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
            && data != null && data.getData() != null){
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageView6);
        }
    }

    public void saveOffer(){
        String title = name_fill.getText().toString();
        String description = description_table.getText().toString();
        //need to add userID here
        int price = Integer.parseInt(price_tab.getText().toString());

        if(title.trim().isEmpty() || description.trim().isEmpty() || price <= 0){
            Toast.makeText(this, "Please insert a correct title, description, and price and select an offer type.", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference offerRef = FirebaseFirestore.getInstance().collection("Offer");
        offerRef.add(new Offer(title, description, Offer.OfferStat.Available, "Item", price, upload)); //to change
        Toast.makeText(this, "Offer added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
