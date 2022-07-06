package com.example.cookingapp.Model;

import java.util.List;

public class ListMonAn {
    private  String tenplaylist;
    private List<MonAn> monAnList;

    public ListMonAn(String tenplaylist, List<MonAn> monAnList) {
        this.tenplaylist = tenplaylist;
        this.monAnList = monAnList;
    }
    public String getTenplaylist() {
        return tenplaylist;
    }

    public void setTenplaylist(String tenplaylist) {
        this.tenplaylist = tenplaylist;
    }

    public List<MonAn> getMonAnList() {
        return monAnList;
    }

    public void setMonAnList(List<MonAn> monAnList) {
        this.monAnList = monAnList;
    }
}
