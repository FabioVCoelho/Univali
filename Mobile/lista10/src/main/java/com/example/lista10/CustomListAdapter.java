package com.example.lista10;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CustomListAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private int id;
    private List<Item> items;

    public CustomListAdapter(Context context, int textViewResourceId, List<Item> list) {
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
        ImageView imageView = mView.findViewById(R.id.imageView);
        imageView.setImageURI(Uri.parse("https://www.riodasostras.rj.gov.br/wp-content/uploads/2018/09/Festival-de-Pizza-ser%C3%A1-realizado-entre-os-dias-14-de-setembro-ate-14-de-Outubro-foto-Jorge-Ronald.jpg"));
        return mView;
    }


    public void updatedData(List<Item> listOfItems) {
        this.items.clear();
        this.items.addAll(listOfItems);
    }

    public List<Item> itensWithQuantityMoreThanOne() {
        List<Item> returnList = new ArrayList<>();
        for (Item item : items) {
            if (item.getQuantity() != null && Integer.parseInt(item.getQuantity()) > 0) {
                returnList.add(item);
            }
        }
        return returnList;
    }

}