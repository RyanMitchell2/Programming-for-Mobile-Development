package com.uws.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> comments_body;

    public CommentAdapter(Context context, List<String> comments_body) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.comments_body = comments_body;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.comment_card, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String body = comments_body.get(position);
        viewHolder.commentBody.setText(body);
    }

    @Override
    public int getItemCount() {
        return comments_body.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView commentBody;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentBody = itemView.findViewById(R.id.commentBody);
        }

    }
}
