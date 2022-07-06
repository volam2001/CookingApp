package com.example.cookingapp.controller;

import com.example.cookingapp.Model.MonAn;
import com.example.cookingapp.Model.ListMonAn;

import java.util.ArrayList;

public class MonAnController {
    private MonAn monAn;


    public ArrayList<String> getDataListBanner(){
        return  monAn.getDataListBanner();
    }

}
