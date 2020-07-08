package com.example.seafoodlist;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
public class ListSeafoodAdapter extends RecyclerView.Adapter<ListSeafoodAdapter.ListViewHolder> {
    private ArrayList<Seafood> seafoodArrayList = new ArrayList<>();
    public void setSeafoodArrayList(ArrayList<Seafood> arrayList) {
        seafoodArrayList.clear();
        seafoodArrayList.addAll(arrayList);
        notifyDataSetChanged();
    }
    //untuk menampilkan dalam bentuk list item row
    @NonNull
    @Override
    public ListSeafoodAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_seafood, parent, false);
        return new ListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ListSeafoodAdapter.ListViewHolder holder, int position) {
        final Seafood seafood = seafoodArrayList.get(position);
        holder.title.setText(seafood.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(seafood.getImage())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.image);
        //menampilkan list detail
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.DATA, seafood);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return seafoodArrayList.size();
    }
    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_item_name);
            image = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
