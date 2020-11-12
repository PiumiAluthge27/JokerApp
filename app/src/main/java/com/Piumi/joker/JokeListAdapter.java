package com.Piumi.joker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class JokeListAdapter extends ArrayAdapter<JokeObject> {

    private Context mContext;
    int mResource;
    private List<JokeObject> ljokes;
    private static LayoutInflater inflater = null;

    public JokeListAdapter(@NonNull Context context, int resource, @NonNull List<JokeObject> objects) {
        super(context, resource, objects);
        try {
            this.mContext = context;
            this.ljokes = objects;
            this.mResource = resource;

        } catch (Exception e) {

        }
    }

    public View getView(int position, View convertView, ViewGroup parent){

        int jId = getItem(position).Id;
        String jType = getItem(position).Type;
        String jSetup = getItem(position).Setup;
        String jPunchline = getItem(position).Punchline;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tId = (TextView)convertView.findViewById(R.id.jokeId);
        TextView tType = (TextView)convertView.findViewById(R.id.jokeType);
        TextView tSetup = (TextView)convertView.findViewById(R.id.jokeSetup);
        TextView tPunchline = (TextView)convertView.findViewById(R.id.jokePunchline);

        tId.setText(String.valueOf(jId));
        tType.setText(jType);
        tSetup.setText(jSetup);
        tPunchline.setText(jPunchline);

        return convertView;
    }

}
