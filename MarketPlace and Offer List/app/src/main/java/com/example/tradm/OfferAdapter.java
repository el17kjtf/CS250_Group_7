package com.example.tradm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class OfferAdapter extends FirestoreRecyclerAdapter<Offer, OfferAdapter.OfferHolder> {
    private OnItemClickListener listener;

    public OfferAdapter(@NonNull FirestoreRecyclerOptions<Offer> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OfferHolder holder, final int position, @NonNull Offer model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDescription.setText(model.getDescription());
        holder.textViewOfferStatus.setText(model.getOfferStatus().name());
        holder.textViewOfferType.setText(model.getOfferType());
        holder.textViewPrice.setText(String.valueOf(model.getPrice()));
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

        public OfferHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewOfferStatus = itemView.findViewById(R.id.text_view_offer_status);
            textViewOfferType = itemView.findViewById(R.id.text_view_offer_type);
            textViewPrice = itemView.findViewById(R.id.text_view_price);

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
