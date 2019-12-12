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
        //Log.d(TAG, "onSuccess: firebase download url: " + model.getUpload().getImageUrl()); //use if testing...don't need this line.
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
        ImageView imageView;
        ImageView imageViewArrow;

        public OfferHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.item_title);
            textViewDescription = itemView.findViewById(R.id.item_content);
            imageView = itemView.findViewById(R.id.item_pic);
            imageViewArrow = itemView.findViewById(R.id.imageView3);

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
