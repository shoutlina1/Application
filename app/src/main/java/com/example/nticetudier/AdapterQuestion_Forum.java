package com.example.nticetudier;


import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.Questions_Forum;

public class AdapterQuestion_Forum extends RecyclerView.Adapter<AdapterQuestion_Forum.MyViewHolder> {

    Context c;
    ArrayList<Questions_Forum> questions_forums;

    public AdapterQuestion_Forum(Context c, ArrayList<Questions_Forum> questions_forums) {
        this.c = c;
        this.questions_forums = questions_forums;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterQuestion_Forum.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_forum_row,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.qst_titre.setText(questions_forums.get(position).getTitre());
        holder.qst_user.setText(questions_forums.get(position).getQuestionUser());
        holder.icone_qst.setImageResource(R.drawable.qst);
        if(questions_forums.get(position).getQuestionTime()==0) questions_forums.get(position).setQuestionTime(questions_forums.get(position).getRéponses().get(0).getRéponseTime());
        holder.qst_time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", questions_forums.get(position).getQuestionTime()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=((AppCompatActivity)c).getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Forum_réponses réponses = new Forum_réponses();
                réponses.setRéponsesData(questions_forums.get(position).getRéponses());
                réponses.setQuestionid(String.valueOf(questions_forums.get(position).getId()));
                réponses.setContenu(questions_forums.get(position).getContenu());
                ft.replace(R.id.fragment_container,réponses).addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions_forums.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView icone_qst;
        TextView qst_titre,qst_user,qst_time;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.icone_qst = itemView.findViewById(R.id.img_qst);
            this.qst_titre = itemView.findViewById(R.id.tvTitre_qst);
            this.qst_user = itemView.findViewById(R.id.tvUser_qst);
            this.qst_time = itemView.findViewById(R.id.tvTime_qst);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
