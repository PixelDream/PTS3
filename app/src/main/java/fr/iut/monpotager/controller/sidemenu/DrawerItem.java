package fr.iut.monpotager.controller.sidemenu;

import android.view.ViewGroup;

public abstract class DrawerItem<T extends DrawerAdapter.ViewHolder> {

    protected boolean isChecked;

    public abstract T createViewHolder(ViewGroup parent);

    public abstract void bindViewHolder(T holder);

<<<<<<< HEAD
<<<<<<< HEAD
    public DrawerItem<T> setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        return this;
    }

=======
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
=======
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
    public boolean isSelectable() {
        return true;
    }

    public boolean isChecked() {
        return isChecked;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
    public DrawerItem<T> setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        return this;
    }

<<<<<<< HEAD
>>>>>>> b3be88e904fcae5c31c99d018fd5db3bb87e95c1
=======
>>>>>>> b52d76051c649a8620a1fa436c5faab797ef93f3
    public abstract DrawerItem withSelectedTextBold();
}
