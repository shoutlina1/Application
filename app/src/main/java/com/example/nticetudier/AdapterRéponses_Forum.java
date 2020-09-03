package com.example.nticetudier;


import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.library.bubbleview.BubbleTextView;

import java.util.ArrayList;

import Model.Réponses_Forum;

public class AdapterRéponses_Forum extends RecyclerView.Adapter<AdapterRéponses_Forum.MyViewHolder> {

    Context c;
    ArrayList<Réponses_Forum> réponses_forums;

    public AdapterRéponses_Forum(Context c, ArrayList<Réponses_Forum> réponses_forums) {
        this.c = c;
        this.réponses_forums = réponses_forums;
    }


    @NonNull
    @Override
    public AdapterRéponses_Forum.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterRéponses_Forum.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bubbles_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.qst_text.setText(réponses_forums.get(position).getContenu());
        holder.qst_user.setText(réponses_forums.get(position).getRéponseUser());
        holder.qst_time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", réponses_forums.get(position).getRéponseTime()));

    }

    @Override
    public int getItemCount() {
        return réponses_forums.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        BubbleTextView qst_text;
        TextView qst_user,qst_time;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.qst_text = itemView.findViewById(R.id.question_text);
            this.qst_user = itemView.findViewById(R.id.question_user);
            this.qst_time = itemView.findViewById(R.id.question_time);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
