package com.example.nticetudier;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.Réponses_Forum;

public class Forum_réponses extends Fragment {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private String rep_user;
    private long rep_time;
    private String rep_text;
    //private FirebaseRecyclerAdapter<Questions_Forum, QuestionViewHolder> adapter;
    AdapterRéponses_Forum myAdapter;
    RelativeLayout layout_questions_forum;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    ArrayList<Réponses_Forum> RéponsesData;
    EditText question;
    TextView enonce_qst;
    ImageView emojiButton,submitButton,img_qst, retour_forum;
    String questionid;
    String enonce;
    RecyclerView listOfQuestion;
    Query query;
    FirebaseRecyclerOptions<Réponses_Forum> options;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_questions_forum,container,false);

        mAuth = FirebaseAuth.getInstance();


        layout_questions_forum = (RelativeLayout)view.findViewById(R.id.question_forum);
        listOfQuestion = (RecyclerView) view.findViewById(R.id.recycler_questions_forum);

        listOfQuestion.setLayoutManager(new LinearLayoutManager(getActivity()));

        query = FirebaseDatabase.getInstance().getReference();
        options = new FirebaseRecyclerOptions.Builder<Réponses_Forum>()
                .setQuery(query,Réponses_Forum.class)
                .build();
        //Add Emoji
        emojiButton = (ImageView)view.findViewById(R.id.emoji_button);
        submitButton = (ImageView)view.findViewById(R.id.submit_button);
        question = (EditText)view.findViewById(R.id.qst_text);
        enonce_qst = (TextView)view.findViewById(R.id.tvQst_enonce);
        img_qst = (ImageView)view.findViewById(R.id.img_qst);
        retour_forum = (ImageView)view.findViewById(R.id.retour_forum);


        //Récupération de l'énoncé de la question
        enonce_qst.setText(enonce);

        retour_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(question.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Veuillez saisir quelques chose avant.. ", Toast.LENGTH_SHORT).show();

                }else{
                    Réponses_Forum rep=new Réponses_Forum(FirebaseAuth.getInstance().getCurrentUser().getEmail(), question.getText().toString());
                    getRéponsesCount(questionid,rep);

                }

            }

        });


        //-------------------- récupération des réponses pour chaque question  -----------------------


        mDatabase = FirebaseDatabase.getInstance().getReference("Forum").child("-M8BfQkCllGvxj6rwdXZ").child("questions").child(questionid).child("réponses");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Réponses_Forum> reps=new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Réponses_Forum qst = dataSnapshot1.getValue(Réponses_Forum.class);
                    reps.add(qst);
                }
                setRéponsesData(reps);
                myAdapter = new AdapterRéponses_Forum(getActivity(), RéponsesData);
                listOfQuestion.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Oups.. something is wrong !", Toast.LENGTH_SHORT).show();
            }


        });

        return view;
    }


    public void setRep_user(String rep_user){
        this.rep_user=rep_user;
    }
    public void setRep_time(Long rep_time){
        this.rep_time=rep_time;
    }
    public void setRep_text(String rep_text){
        this.rep_text=rep_text;
    }
    public void setRéponsesData(ArrayList<Réponses_Forum> réponses){
        RéponsesData =réponses;
    }
    public void setQuestionid(String qstid){
        questionid=qstid;
    }


    public void getRéponsesCount(final String idquestion, final Réponses_Forum rep) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Forum").child("-M8BfQkCllGvxj6rwdXZ").child("questions").child(idquestion).child("réponses");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rep.setId(rep.getId()+(int)dataSnapshot.getChildrenCount());
                FirebaseDatabase.getInstance().getReference("Forum").child("-M8BfQkCllGvxj6rwdXZ").child("questions").child(questionid).child("réponses").child(rep.getId()+"").setValue(rep);
                question.setText("");
                question.requestFocus();
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String qstt = rep.getContenu();
                if(qstt.isEmpty()){
                    Toast.makeText(getActivity(),"Veuillez saisir votre réponse svp",Toast.LENGTH_SHORT).show();
                }else{
                    notification(qstt);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void notification(String rps){

        String réponse = rps;

        //String message = "Merci pour votre contribution :) ";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel =

                    new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(),"n")

                .setContentText("Code Sphere")

                .setSmallIcon(R.drawable.send_qst)

                .setAutoCancel(true)

                .setContentText("Nouvelle réponse a été ajoutée dans le forum : "+  réponse );

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());

        managerCompat.notify(999,builder.build());

    }



    public void setContenu(String enonce){
        this.enonce=enonce;
    }

}