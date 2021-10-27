package fr.iut.monpotager.controller.sidemenu;

import android.view.ViewGroup;

public abstract class DrawerItem<T extends DrawerAdapter.ViewHolder> {

    protected boolean isChecked;

    public abstract T createViewHolder(ViewGroup parent);

    public abstract void bindViewHolder(T holder);

<<<<<<< HEAD
    public DrawerItem<T> setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        return this;
    }

=======
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
    public boolean isSelectable() {
        return true;
    }

    public boolean isChecked() {
        return isChecked;
    }

<<<<<<< HEAD
=======
    public DrawerItem<T> setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        return this;
    }

>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
    public abstract DrawerItem withSelectedTextBold();
}
