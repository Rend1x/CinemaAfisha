package com.example.cinemaafisha.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinemaafisha.R;
import com.example.cinemaafisha.pojo.Films;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.cinemaafisha.config.Config.BASE_URL_IMAGE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Films> mData;
    private ClickListener mClickListener;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.mClickListener = clickListener;
        mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.image.getContext())
                .load(BASE_URL_IMAGE + mData.get(position).posterPath)
                .placeholder(R.drawable.ic_photo_black_24dp)
                .into(holder.image);
        holder.title.setText(mData.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private ConstraintLayout constraintLayoutContainer;

        ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            constraintLayoutContainer = itemView.findViewById(R.id.constraintLayout);

            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.launchIntent(mData.get(getAdapterPosition()).title,
                            mData.get(getAdapterPosition()).posterPath,
                            mData.get(getAdapterPosition()).overview);
                }
            });
        }
    }

    public void setData(List<Films> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }
}
