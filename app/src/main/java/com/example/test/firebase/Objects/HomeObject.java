package com.example.test.firebase.Objects;

public class HomeObject {

    int menu_item_image;
    String menu_name;
    Boolean Checked;

    public Boolean getChecked() {
        return Checked;
    }

    public void setChecked(Boolean checked) {
        Checked = checked;
    }

    public int getMenu_item_image() {
        return menu_item_image;
    }

    public void setMenu_item_image(int menu_item_image) {
        this.menu_item_image = menu_item_image;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }


}
