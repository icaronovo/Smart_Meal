package com.example.smart_meal;

public class ItemModel {

    private int imageItem;
    private String titleTxt;
    private String txtPrice;
    private String txtDescItem;

    public ItemModel(int imageItem, String titleTxt, String txtPrice, String txtDescItem) {
        this.imageItem = imageItem;
        this.titleTxt = titleTxt;
        this.txtPrice = txtPrice;
        this.txtDescItem = txtDescItem;
    }

    public int getImageItem() {
        return imageItem;
    }

    public void setImageItem(int imageItem) {
        this.imageItem = imageItem;
    }

    public String getTitleTxt() {
        return titleTxt;
    }

    public void setTitleTxt(String titleTxt) {
        this.titleTxt = titleTxt;
    }

    public String getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }

    public String getTxtDescItem() {
        return txtDescItem;
    }

    public void setTxtDescItem(String txtDescItem) {
        this.txtDescItem = txtDescItem;
    }
}
