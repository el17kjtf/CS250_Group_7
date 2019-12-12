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
import android.widget.TextView;
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

public class NewOfferActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String offerType;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinner;
    private EditText editTextPrice;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button buttonChooseImage;
    private Button buttonUpload;
    private EditText editTextFileName;
    private ImageView imageView;
    private ProgressBar progressBar;

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

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Offer");

<<<<<<< HEAD:MarketPlace and Offer List/app/src/main/java/com/example/tradm/NewOfferActivity.java
        buttonChooseImage = findViewById(R.id.button_choose_image);
        buttonUpload = findViewById(R.id.button_upload);
        editTextFileName = findViewById(R.id.edit_text_file_name);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextPrice = findViewById(R.id.edit_text_price);
        spinner = findViewById(R.id.spinner_offer_type);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.offer_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
=======
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
>>>>>>> parent of fbbf6db... Final commit:TRADM/app/src/main/java/com/example/tradm/NewOfferActivity.java

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadTask != null && uploadTask.isInProgress()){
                    Toast.makeText(NewOfferActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadFile();
                }
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
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 2000);

                            Toast.makeText(NewOfferActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();
                            /*upload = new Upload(editTextFileName.getText().toString().trim(),
                                    taskSnapshot.getUploadSessionUri().toString());
                            String uploadId = databaseReference.push().getKey();
                            databaseReference.child(uploadId).setValue(upload);*/
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();

                            Log.d(TAG, "onSuccess: firebase download url: " + downloadUrl.toString()); //use if testing...don't need this line.
                            upload = new Upload(editTextFileName.getText().toString().trim(),downloadUrl.toString());

                            String uploadId = databaseReference.push().getKey();
                            databaseReference.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NewOfferActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0) * taskSnapshot.getBytesTransferred() /
                                    taskSnapshot.getTotalByteCount();
                            progressBar.setProgress((int) progress);
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
            Picasso.get().load(imageUri).into(imageView);
        }
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
<<<<<<< HEAD:MarketPlace and Offer List/app/src/main/java/com/example/tradm/NewOfferActivity.java
<<<<<<< HEAD:MarketPlace and Offer List/app/src/main/java/com/example/tradm/NewOfferActivity.java
<<<<<<< HEAD:MarketPlace and Offer List/app/src/main/java/com/example/tradm/NewOfferActivity.java
<<<<<<< HEAD:MarketPlace and Offer List/app/src/main/java/com/example/tradm/NewOfferActivity.java
        offerRef.add(new Offer(title, description, Offer.OfferStat.Available, offerType, price, upload)); //to change
=======
        offerRef.add(new Offer(title, description, Offer.OfferStat.Available, "Item", price, upload)); //to change
>>>>>>> parent of fbbf6db... Final commit:TRADM/app/src/main/java/com/example/tradm/NewOfferActivity.java
=======
        offerRef.add(new Offer(title, description, Offer.OfferStat.Available, "Item", price, upload)); //to change
>>>>>>> parent of fbbf6db... Final commit:TRADM/app/src/main/java/com/example/tradm/NewOfferActivity.java
=======
        offerRef.add(new Offer(title, description, Offer.OfferStat.Available, "Item", price, upload)); //to change
>>>>>>> parent of fbbf6db... Final commit:TRADM/app/src/main/java/com/example/tradm/NewOfferActivity.java
=======
        offerRef.add(new Offer(title, description, Offer.OfferStat.Available, "Item", price, upload)); //to change
>>>>>>> parent of fbbf6db... Final commit:TRADM/app/src/main/java/com/example/tradm/NewOfferActivity.java
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
