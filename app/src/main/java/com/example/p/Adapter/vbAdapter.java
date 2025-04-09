package com.example.p.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p.R;
import com.example.p.model.history;

import java.util.List;

public class vbAdapter extends RecyclerView.Adapter<vbAdapter.vbHolder> {
    private final List<history> vbs;
    private final Context context;

    public vbAdapter(List<history> vbs, Context context) {
        this.vbs = vbs;
        this.context = context;
    }

    @NonNull
    @Override
    public vbHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.v1, parent, false);
        return new vbHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vbHolder holder, int position) {
        history item = vbs.get(position);
        holder.historyname.setText(item.getName());
        holder.harrd.setText(item.getHard());
        holder.desc.setText(item.getDesc());

        // Load image from drawable if path matches
        if (item.getImg() != null) {
            int resId = context.getResources()
                    .getIdentifier(item.getImg(), "drawable", context.getPackageName());
            if (resId != 0) {
                holder.img.setImageResource(resId);
            }
        }
    }

    @Override
    public int getItemCount() { return vbs.size(); }

    static class vbHolder extends RecyclerView.ViewHolder {
        TextView historyname, desc, harrd;
        ImageView img;

        public vbHolder(@NonNull View itemView) {
            super(itemView);
            historyname = itemView.findViewById(R.id.d);
            desc = itemView.findViewById(R.id.d2);
            harrd = itemView.findViewById(R.id.d3);
            img = itemView.findViewById(R.id.imageVw);
        }
    }
}