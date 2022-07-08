package com.example.cookingapp.Model;

import java.io.Serializable;

public class ChiTiet implements Serializable {
    private String link;
    private String chitiet;

    public ChiTiet()
    {

    }
    public ChiTiet(String link, String chitiet) {
        this.link = link;
        this.chitiet = chitiet;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }
}
