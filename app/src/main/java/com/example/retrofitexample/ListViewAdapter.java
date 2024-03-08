package com.example.retrofitexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {
    private final List<Change> changes;

    public ListViewAdapter(List<Change> changes) {
        this.changes = changes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtSubject;

        public ViewHolder(View view) {
            super(view);
            txtSubject = (TextView) view.findViewById(R.id.subject);
        }

        public TextView getTxtSubject() {
            return txtSubject;
        }
    }

    @NonNull
    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.ViewHolder holder, int position) {
        holder.getTxtSubject().setText(position+"/"+changes.get(position).subject);
    }

    @Override
    public int getItemCount() {
        return changes.size();
    }
}