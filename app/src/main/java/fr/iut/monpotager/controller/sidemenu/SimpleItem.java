package fr.iut.monpotager.controller.sidemenu;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fr.iut.monpotager.R;

public class SimpleItem extends DrawerItem<SimpleItem.ViewHolder> {

    private final Drawable icon;
    private final String title;
    private int selectedItemIconTint;
    private int selectedItemTextTint;
    private boolean selectedItemTextStyle;
    private int normalItemIconTint;
    private int normalItemTextTint;

<<<<<<< HEAD
<<<<<<< HEAD
    private Drawable icon;
    private String title;
=======
    private final Drawable icon;
    private final String title;
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1

=======
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
    public SimpleItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_option, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.title.setText(title);
        if (selectedItemTextStyle && isChecked) {
            holder.title.setTypeface(Typeface.DEFAULT_BOLD);
<<<<<<< HEAD
<<<<<<< HEAD
=======
        } else {
            holder.title.setTypeface(Typeface.DEFAULT);
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
=======
        } else {
            holder.title.setTypeface(Typeface.DEFAULT);
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
        }
        holder.icon.setImageDrawable(icon);

        holder.title.setTextColor(isChecked ? selectedItemTextTint : normalItemTextTint);
        holder.icon.setColorFilter(isChecked ? selectedItemIconTint : normalItemIconTint);
    }

    @Override
    public DrawerItem withSelectedTextBold() {
        this.selectedItemTextStyle = true;
        return this;
    }

    public SimpleItem withSelectedIconTint(int selectedItemIconTint) {
        this.selectedItemIconTint = selectedItemIconTint;
        return this;
    }

    public SimpleItem withSelectedTextTint(int selectedItemTextTint) {
        this.selectedItemTextTint = selectedItemTextTint;
        return this;
    }

    public SimpleItem withIconTint(int normalItemIconTint) {
        this.normalItemIconTint = normalItemIconTint;
        return this;
    }

    public SimpleItem withTextTint(int normalItemTextTint) {
        this.normalItemTextTint = normalItemTextTint;
        return this;
    }

    static class ViewHolder extends DrawerAdapter.ViewHolder {

<<<<<<< HEAD
<<<<<<< HEAD
        private ImageView icon;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
=======
=======
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
        private final ImageView icon;
        private final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
<<<<<<< HEAD
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
=======
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
        }
    }
}
