package com.example.tradm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

public class OfferAdapter extends FirestoreRecyclerAdapter<Offer, OfferAdapter.OfferHolder> {
    private OnItemClickListener listener;
    private Context context;
    private static final String TAG = "MyActivity";

    public OfferAdapter(@NonNull FirestoreRecyclerOptions<Offer> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OfferHolder holder, int position, @NonNull Offer model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDescription.setText(model.getDescription());
<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm/OfferAdapter.java
<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm/OfferAdapter.java
=======
        holder.textViewOfferStatus.setText(model.getOfferStatus().name());
        holder.textViewOfferType.setText(model.getOfferType());
        holder.textViewPrice.setText(String.valueOf(model.getPrice()));
>>>>>>> parent of 3db3a08... Final Commit Maybe?:MarketPlace and Offer List/app/src/main/java/com/example/tradm/OfferAdapter.java
        //Log.d(TAG, "onSuccess: firebase download url: " + model.getUpload().getImageUrl()); //use if testing...don't need this line.
=======
        holder.textViewOfferStatus.setText(model.getOfferStatus().name());
        holder.textViewOfferType.setText(model.getOfferType());
        holder.textViewPrice.setText(String.valueOf(model.getPrice()));
        Log.d(TAG, "onSuccess: firebase download url: " + model.getUpload().getImageUrl()); //use if testing...don't need this line.
>>>>>>> parent of 891c119... zip added:MarketPlace and Offer List/app/src/main/java/com/example/tradm/OfferAdapter.java
        Picasso.get().load(model.getUpload().getImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageView);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    @NonNull
    @Override
    public OfferHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent, false);
        return new OfferHolder(v);
    }

    class OfferHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewOfferType;
        TextView textViewPrice;
        TextView textViewOfferStatus;
        ImageView imageView;

        public OfferHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewOfferStatus = itemView.findViewById(R.id.text_view_offer_status);
            textViewOfferType = itemView.findViewById(R.id.text_view_offer_type);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
            imageView = itemView.findViewById(R.id.image_view_upload);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
