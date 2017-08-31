package com.example.kritika.inventrom_android;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment mvhhjvust implement the

 */
public class ListFrag extends Fragment {
    ListView list;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_list, container, false);

       //String[] imgFileList = getActivity().getApplicationContext().fileList();
        list=(ListView)view.findViewById(R.id.list);


        ContextWrapper cw = new ContextWrapper(getActivity());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("InventromimageDir", Context.MODE_PRIVATE);


       /* File mydir = getContext().getDir("InventronimageDir", Context.MODE_PRIVATE);*/
        File lister = directory.getAbsoluteFile();
        String[] imgFileList=lister.list();




        MyAdapter adapter=new MyAdapter(getActivity(),imgFileList);


        list.setAdapter(adapter);
        return view;

    }
    class  MyAdapter extends ArrayAdapter {
        String[] imageArray;


        public MyAdapter(Context context, String[] imageArray) {
            super(context, R.layout.custom_listrow, imageArray);
            this.imageArray = imageArray;

        }

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.custom_listrow, parent, false);


            TextView imgname = (TextView) row.findViewById(R.id.imgList);

            imgname.setText(imageArray[position]);


            return row;
        }

    }


}