package com.example.currentplacedetailsonmap;

import android.app.Application;
import android.util.Pair;

import java.util.ArrayList;

public class Globals{
    private static Globals instance;

    private ArrayList<Pair<String, String>> myLocs;
    private ArrayList<Pair<String, String>> myInteractions;
    private int coronavirusScore;

    // Restrict the constructor from being instantiated
    private Globals(){}

    /*
    METHODS FOR MY LOCATIONS
     */

    public ArrayList<Pair<String, String>> getmyLocs() {
        return this.myLocs;
    }

    public void setMyLocs(ArrayList<Pair<String, String>> locs) {
        this.myLocs = locs;
    }

    public void clearLocs() {
        if (this.myLocs == null){
            this.myLocs = new ArrayList<Pair<String, String>>();
        } else {
            this.myLocs.clear();
        }
    }

    public void addLoc(Pair<String, String> locPair) {
        if (this.myLocs == null){
            this.myLocs = new ArrayList<Pair<String, String>>();
        }
        this.myLocs.add(locPair);
    }

    /*
    METHODS FOR MY INTERACTIONS
     */

    public ArrayList<Pair<String, String>> getMyInteractions() {
        return this.myInteractions;
    }

    public void setMyInteractions(ArrayList<Pair<String, String>> inters) {
        this.myInteractions = inters;
    }

    public void clearInteractions() {
        if (this.myInteractions == null){
            this.myInteractions = new ArrayList<Pair<String, String>>();
        } else {
            this.myInteractions.clear();
        }
    }

    public void addInteraction(Pair<String, String> interPair) {
        if (this.myInteractions == null){
            this.myInteractions = new ArrayList<Pair<String, String>>();
        }
        this.myInteractions.add(interPair);
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }

    /*
    METHODS FOR CORONAVIRUS SCORE
     */

    public void setCoronavirusScore(int score){
        coronavirusScore = score;
    }

    public int getCoronavirusScore(){
        return coronavirusScore;
    }

}
