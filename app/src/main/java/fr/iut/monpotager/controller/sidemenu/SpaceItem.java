package fr.iut.monpotager.controller.sidemenu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class SpaceItem extends DrawerItem<SpaceItem.ViewHolder> {
<<<<<<< HEAD
<<<<<<< HEAD
    private int spaceDp;
=======
    private final int spaceDp;
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
=======
    private final int spaceDp;
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3

    public SpaceItem(int spaceDp) {
        this.spaceDp = spaceDp;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        Context c = parent.getContext();
        View view = new View(c);
        int height = (int) (c.getResources().getDisplayMetrics().density * spaceDp / 2);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));

        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {

    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public DrawerItem withSelectedTextBold() {
        return this;
    }

    public class ViewHolder extends DrawerAdapter.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
