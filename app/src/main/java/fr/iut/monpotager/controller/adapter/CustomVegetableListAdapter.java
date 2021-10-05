package fr.iut.monpotager.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.Vegetable;

public class CustomVegetableListAdapter extends BaseAdapter {

    private List<Vegetable> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomVegetableListAdapter(Context context,List<Vegetable> listData) {
        this.listData = listData;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Vegetable getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Vegetable vegetable = getItem(i);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.adapter_item_search_list,null);

        }

        TextView vegetableName = view.findViewById(R.id.nameVegetable);
        vegetableName.setText(vegetable.getName());


        return view;
    }
}
