package com.example.pc.listas;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import java.util.ArrayList;



public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private String temp;





    public MyCustomAdapter(ArrayList<String> list, Context context) {
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
            view = inflater.inflate(R.layout.addgroup, null);
        }

        //Handle TextView and display string from your list
        final EditText listItemText = (EditText) view.findViewById(R.id.editText4);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.button3);
        Button addBtn = (Button)view.findViewById(R.id.button);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                if(position !=0) {

                    list.remove(position); //or some other task


                    notifyDataSetChanged();
                    Log.d("asdf", position + "");
                }
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                temp=listItemText.getText().toString();

                list.add(temp);
                notifyDataSetChanged();
            }
        });

        return view;
    }

}
