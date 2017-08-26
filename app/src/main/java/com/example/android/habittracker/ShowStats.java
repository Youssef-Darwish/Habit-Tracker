package com.example.android.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;


public class ShowStats extends AppCompatActivity {

    private PieChart chart;
    private ArrayList<Double> days;
    private int done, missed;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        toolbar = (Toolbar) findViewById(R.id.show_stats_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Progress Statistics");
        Bundle extras = getIntent().getExtras();
        days = (ArrayList<Double>) extras.get("days");

        chart = (PieChart) findViewById(R.id.chart);
        showPieCart();
    }


    private void showPieCart() {
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i) == (double) 1) {
                done++;
            } else {
                missed++;
            }
        }
        Log.e("show stats", String.valueOf(done));
        Log.e("show stats", String.valueOf(missed));
        float donePercent = ((float) done / days.size()) * 100;
        float missedPercent = ((float) missed / days.size()) * 100;
        Log.e("show stats", String.valueOf(donePercent));
        Log.e("show stats", String.valueOf(missedPercent));

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(donePercent, "Done"));
        entries.add(new PieEntry(missedPercent, "Missed"));

        PieDataSet set = new PieDataSet(entries, "progress Results");
        PieData data = new PieData(set);
        set.setColors(new int[]{R.color.black, R.color.aqua}, this);
        chart.setData(data);
        chart.invalidate(); // refresh


    }
}
