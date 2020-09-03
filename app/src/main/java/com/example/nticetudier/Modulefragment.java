package com.example.nticetudier;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Modulefragment extends Fragment  {
  //  GridView gridView ;

   // View view;
   // Fragment coursfragment ;
   // FragmentManager fragmentManager = getFragmentManager();
    //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
  //  String[] module ={"dam","gl","daw2","rc","bdm"};
   // int[] image={R.drawable.dam,R.drawable.gl,R.drawable.daw,R.drawable.rc,R.drawable.bdm};

     private ImageView dam,daw,rc,gl,bdm ;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

     ViewGroup root =(ViewGroup)  inflater.inflate(R.layout.fragment_module, container, false);
     ImageView dam = root.findViewById(R.id.dam);
     ImageView daw = root.findViewById(R.id.daw);
     ImageView gl = root.findViewById((R.id.gl));
     ImageView rc = root.findViewById((R.id.rc));
     ImageView bdm = root.findViewById((R.id.bdm));


     dam.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent();
             intent.setAction(Intent.ACTION_VIEW);
             intent.addCategory(Intent.CATEGORY_BROWSABLE);
             intent.setData(Uri.parse("https://elearning.univ-constantine2.dz/elearning/course/view.php?id=10"));
             startActivity(intent);
         }
     });
        /*  gridView= root.findViewById(R.id.grid_view);
        MainAdapter adapter= new MainAdapter(Modulefragment.this,module,image);
        gridView.setAdapter(adapter);

     gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Toast.makeText(getActivity().getApplicationContext(),"vous avez choisit "+module[+position],Toast.LENGTH_LONG).show();
         }


     });*/
     return root;
     //  ImageView dam = (ImageView)view.findViewById(R.id.dam);

      // return view;




    }}


  /*  @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.dam:
                coursfragment= new coursfragment();

                fragmentTransaction.replace(R.id.fragment_container, coursfragment);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
        }  }  */





