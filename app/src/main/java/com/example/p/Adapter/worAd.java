package com.example.p.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.p.R;
import com.example.p.model.word;
import java.util.List;

public class worAd extends RecyclerView.Adapter<worAd.VbHolder> {
    private List<word> vbs;
    private final Context context;

    public interface OnItemClickListener {
        void onItemClick(word item);
    }

    private final OnItemClickListener listener;

    public worAd(List<word> vbs, Context context, OnItemClickListener listener) {
        this.vbs = vbs;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VbHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.v2, parent, false);
        return new VbHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VbHolder holder, int position) {
        word item = vbs.get(position);
        holder.wordy.setText(item.getWordy());
        holder.trans.setText(item.getTrans());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vbs.size();
    }

    // Этот метод теперь работает корректно
    public void updateData(List<word> newWords) {
        this.vbs = newWords;
        notifyDataSetChanged();
    }

    static class VbHolder extends RecyclerView.ViewHolder {
        TextView wordy, trans;

        public VbHolder(@NonNull View itemView) {
            super(itemView);
            wordy = itemView.findViewById(R.id.textView4);
            trans = itemView.findViewById(R.id.textView5);
        }
    }
}
