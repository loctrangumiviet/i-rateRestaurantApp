package com.example.i_raterestaurantapp.ui.rating_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.i_raterestaurantapp.R;
import com.example.i_raterestaurantapp.data.model.RatingModel;

import java.util.ArrayList;

public class AdapterRating extends RecyclerView.Adapter<AdapterRating.ViewHolder> {
    ArrayList<RatingModel> listRate;
    Context context;
    OnClickListener onClick;

    public interface OnClickListener
    {
        void onclick(RatingModel rate);
    }

    //set list in oder to update data of recyclerview
    public void setList(ArrayList<RatingModel> arrayList){
        this.listRate = arrayList;
        notifyDataSetChanged();
    }

    public void setOnclickListener(OnClickListener onClick) {
        this.onClick = onClick;
    }

    public AdapterRating(ArrayList<RatingModel> listRate, Context context) {
        this.listRate = listRate;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view (ánh xạ layout)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_restaurant_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listRate.get(position));
    }

    @Override
    public int getItemCount() {
        return listRate == null ? 0 : listRate.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvResName,tvResType,tvResDate,tvResAverage,tvServiceRating,tvCleanlinessRating,
                tvFoodQualityRating,tvNotes,tvReporterName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResName = itemView.findViewById(R.id.tvResName);
            tvResType = itemView.findViewById(R.id.tvResType);
            tvResDate = itemView.findViewById(R.id.tvResDate);
            tvResAverage = itemView.findViewById(R.id.tvResAverage);
            tvServiceRating = itemView.findViewById(R.id.tvServiceRating);
            tvCleanlinessRating = itemView.findViewById(R.id.tvCleanlinessRating);
            tvFoodQualityRating = itemView.findViewById(R.id.tvFoodQualityRating);
            tvNotes = itemView.findViewById(R.id.tvNotes);
            tvReporterName = itemView.findViewById(R.id.tvReporterName);
        }

        public void bind(final RatingModel rate){
            tvResName.setText(rate.getRestaurantName());
            tvResType.setText(rate.getRestaurantType());
            tvResDate.setText(rate.getDateVisit());
            tvResAverage.setText(rate.getAveragePrice());
            tvServiceRating.setText(rate.getServiceRating());
            tvCleanlinessRating.setText(rate.getCleanlinessRating());
            tvFoodQualityRating.setText(rate.getFoodQualityRating());
            tvNotes.setText(rate.getNotes());
            tvReporterName.setText(rate.getReporterName());
            //event onclick when click each item on recyclerview
            itemView.setOnClickListener(v -> {
                onClick.onclick(rate);
            });
        }
    }
}


