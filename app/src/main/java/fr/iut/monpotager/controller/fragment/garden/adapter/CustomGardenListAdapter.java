package fr.iut.monpotager.controller.fragment.garden.adapter;

import android.content.Context;
import android.os.Build;
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
import fr.iut.monpotager.controller.fragment.search.adapter.RoundedCornersTransformation;
import fr.iut.monpotager.model.Garden;

public class CustomGardenListAdapter extends BaseAdapter implements Filterable {

    private final LayoutInflater layoutInflater;
    private List<Garden> listData;

    public CustomGardenListAdapter(Context context, List<Garden> listData) {
        super();
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Garden getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Garden garden = getItem(i);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.adapter_item_search_list_garden, null);
        }

        TextView vegetableName = view.findViewById(R.id.nameVegetable);
        vegetableName.setText(garden.getVegetable().getName());

        ImageView imageVegetable = view.findViewById(R.id.imageVegetable);

        TextView GardenDate = view.findViewById(R.id.GardenDate);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            GardenDate.setText(garden.getDayDuration() + " jours avant récolte");
        }

        TextView GardenQuantity = view.findViewById(R.id.GardenQuantity);
        GardenQuantity.setText(garden.getQuantity() + " " + garden.getUnit());

        final Transformation transformation = new RoundedCornersTransformation(50, 5, RoundedCornersTransformation.CornerType.TOP);
        Picasso.get().load(garden.getVegetable().getPicture()).transform(transformation).resize(2048, 550).centerCrop().into(imageVegetable);

        return view;
    }


    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                listData = (List<Garden>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Garden> FilteredArrayNames = new ArrayList<>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < listData.size(); i++) {
                    Garden dataNames = listData.get(i);
                    if (dataNames.getVegetable().getName().toLowerCase().startsWith(constraint.toString())) {
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

}