package com.palardnicolasgmail.virus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nicolas_2 on 26/04/2015.
 */
public class VGeneticCodeViewAdapter extends BaseAdapter {

    private ArrayList<VMyCode> myCodes;
    LayoutInflater layoutInflater;
    Context context;
    private int lastPosition = -1;

    public int getCount(){
        return myCodes.size();
    }

    public Object getItem(int position){
        return myCodes.get(position);
    }

    public long getItemId(int arg0){
       return arg0;
    }

    public VGeneticCodeViewAdapter(Context context, ArrayList<VMyCode> myCodes) {
        layoutInflater = LayoutInflater.from(context);
        this.myCodes = myCodes;
        this.context = context;
        this.lastPosition = -1;
    }

    static class ViewHolder{
       TextView codeName;
       ImageView codePic;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (convertView ==null) {
            convertView = layoutInflater.inflate(R.layout.my_codes_item, null);
            holder = new ViewHolder();

            holder.codeName = (TextView) convertView.findViewById(R.id.codeName);
            holder.codePic = (ImageView) convertView.findViewById(R.id.codePicture);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.codeName.setText(myCodes.get(position).getName());
        holder.codePic.setBackgroundColor(myCodes.get(position).getImage());

        lastPosition = position;

        return convertView;
    }

}
