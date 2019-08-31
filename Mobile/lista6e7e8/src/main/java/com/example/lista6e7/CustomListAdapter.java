package com.example.lista6e7;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static java.lang.Integer.parseInt;

public class CustomListAdapter extends ArrayAdapter<Food> {

    private Context mContext;
    private int id;
    private List<Food> items;

    public CustomListAdapter(Context context, int textViewResourceId, List<Food> list) {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View mView = v;
        if (mView == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        Button buttonPlus = mView.findViewById(R.id.button);
        Button buttonMinus = mView.findViewById(R.id.button2);
        TextView quantity = mView.findViewById(R.id.textView2);
        buttonPlus.setOnClickListener(v1 -> {
            quantity.setText(Integer.toString(parseInt(quantity.getText().toString()) + 1));
            items.get(position).setQuantity(quantity.getText().toString());
        });
        buttonMinus.setOnClickListener(v1 -> {
            if (Integer.parseInt(quantity.getText().toString()) - 1 < 0)
                quantity.setText("0");
            else
                quantity.setText(Integer.toString(parseInt(quantity.getText().toString()) - 1));
            items.get(position).setQuantity(quantity.getText().toString());
        });
        TextView text = mView.findViewById(R.id.textView);
        text.setTextColor(Color.BLACK);
        text.setText(items.get(position).getName());
        text.setBackgroundColor(Color.WHITE);
        text.setOnClickListener(v1 -> {
            Toast.makeText(mContext, items.get(position).toString(), Toast.LENGTH_LONG).show();
        });
        return mView;
    }
}