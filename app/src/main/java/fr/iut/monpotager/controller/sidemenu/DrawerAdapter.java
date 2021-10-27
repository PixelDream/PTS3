package fr.iut.monpotager.controller.sidemenu;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD

=======
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "ConstantConditions"})
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

<<<<<<< HEAD
    private List<DrawerItem> items;
    private Map<Class<? extends DrawerItem>, Integer> viewTypes;
    private SparseArray<DrawerItem> holderFactories;
=======
    private final List<DrawerItem> items;
    private final Map<Class<? extends DrawerItem>, Integer> viewTypes;
    private final SparseArray<DrawerItem> holderFactories;
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1

    private OnItemSelectedListener listener;

    public DrawerAdapter(List<DrawerItem> items) {
        this.items = items;
        this.viewTypes = new HashMap<>();
        this.holderFactories = new SparseArray<>();
<<<<<<< HEAD

        processViewTypes();
    }

=======
        processViewTypes();
    }

    private void processViewTypes() {
        int type = 0;
        for (DrawerItem item : items) {
            if (!viewTypes.containsKey(items.getClass())) {
                viewTypes.put(item.getClass(), type);
                holderFactories.put(type, item);
                type++;
            }
        }
    }

>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = holderFactories.get(viewType).createViewHolder(parent);
<<<<<<< HEAD
        holder.adapter = this;
=======
        holder.drawerAdapter = this;
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
        return holder;
    }

    @Override
<<<<<<< HEAD
    @SuppressWarnings("unchecked")
=======
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        items.get(position).bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes.get(items.get(position).getClass());
    }

<<<<<<< HEAD
    private void processViewTypes() {
        int type = 0;
        for (DrawerItem item : items) {
            if (!viewTypes.containsKey(item.getClass())) {
                viewTypes.put(item.getClass(), type);
                holderFactories.put(type, item);
                type++;
            }
        }
    }

=======
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
    public void setSelected(int position) {
        DrawerItem newChecked = items.get(position);
        if (!newChecked.isSelectable()) {
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            DrawerItem item = items.get(i);
<<<<<<< HEAD
=======

>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
            if (item.isChecked()) {
                item.setChecked(false);
                notifyItemChanged(i);
                break;
            }
        }

        newChecked.setChecked(true);
        notifyItemChanged(position);

        if (listener != null) {
            listener.onItemSelected(position);
        }
    }

    public void setListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

<<<<<<< HEAD
    static abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private DrawerAdapter adapter;

        public ViewHolder(View itemView) {
=======

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    static abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private DrawerAdapter drawerAdapter;

        public ViewHolder(@NonNull View itemView) {
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
<<<<<<< HEAD
            adapter.setSelected(getAdapterPosition());
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }
}
=======
            drawerAdapter.setSelected(getAdapterPosition());
        }
    }
}
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
