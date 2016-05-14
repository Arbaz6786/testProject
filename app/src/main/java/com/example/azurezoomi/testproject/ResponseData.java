package com.example.azurezoomi.testproject;

import java.util.ArrayList;

class ResponseData {

    private String query;

    private ArrayList<Entry> entries = new ArrayList<Entry>();

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }


}
