package com.example.alan_.proyectomoviles;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alan_ on 4/7/2017.
 */

public class AlumnosAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private String temp;
    private CheckBox checar;
    public  AlumnosAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
        String temp;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
        //just return 0 if your list items do not have an Id variable.
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.alumnos, null);
        }

        //Handle TextView and display string from your list
        final TextView textview5 = (TextView)view.findViewById(R.id.textView5); ;

      //  final EditText listItemText = (EditText) view.findViewById(R.id.editText4);
        textview5.setText(list.get(position));

        //Handle buttons and add onClickListeners
        checar =(CheckBox)view.findViewById(R.id.checkBox);


        /*checar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                temp=listItemText.getText().toString();


                list.add(1,temp);
                notifyDataSetChanged();
            }
        });

        */
        return view;
    }

}
