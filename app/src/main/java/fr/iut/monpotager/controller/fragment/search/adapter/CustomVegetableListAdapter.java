package fr.iut.monpotager.controller.fragment.search.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.model.Vegetable;

public class CustomVegetableListAdapter extends BaseAdapter implements Filterable {

    private List<Vegetable> listData;
    private LayoutInflater layoutInflater;

    public CustomVegetableListAdapter(Context context, List<Vegetable> listData) {
        super();
        this.listData = listData;
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
            view = layoutInflater.inflate(R.layout.adapter_item_search_list, null);

        }

        TextView vegetableName = view.findViewById(R.id.nameVegetable);
        vegetableName.setText(vegetable.getName());

        ImageView imageVegetable = view.findViewById(R.id.imageVegetable);

        TextView durationVegetable = view.findViewById(R.id.durationVegetable);
        int numOfWeeks = Math.round(vegetable.getDuration() / 7);
        durationVegetable.setText(numOfWeeks + " semaines");
        final int radius = 50;
        final int margin = 5;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin, RoundedCornersTransformation.CornerType.TOP);
        Picasso.get().load(vegetable.getPicture()).transform(transformation).into(imageVegetable);


        return view;
    }


    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                listData = (List<Vegetable>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Vegetable> FilteredArrayNames = new ArrayList<Vegetable>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < listData.size(); i++) {
                    Vegetable dataNames = listData.get(i);
                    if (dataNames.getName().toLowerCase().startsWith(constraint.toString())) {
                        FilteredArrayNames.add(dataNames);
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }

    public void setListData(List<Vegetable> listData) {
        this.listData = listData;
    }
}



