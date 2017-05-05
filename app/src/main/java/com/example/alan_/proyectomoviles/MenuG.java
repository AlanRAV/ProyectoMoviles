package com.example.alan_.proyectomoviles;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by pc on 04/05/2017.
 */

public class MenuG {
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    AppCompatActivity actividad;

    public MenuG(AppCompatActivity Actividad){
        actividad = Actividad;


    }
}
