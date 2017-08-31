package com.example.kritika.inventrom_android;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ColorGraphActivity extends AppCompatActivity {

    GraphView graph;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_graph);
        graph=(GraphView) findViewById(R.id.graph);
        reset=(Button)findViewById(R.id.reset) ;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });



        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
               new DataPoint(0, 0),
               new DataPoint(80, 80),
               
        });
        graph.addSeries(series);

        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setColor(Color.GREEN);




      LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(80,80),
                new DataPoint(80,0),

        });
        graph.addSeries(series2);
        // graph.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(10);


        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,0),
                new DataPoint(80,0)

        });
        graph.addSeries(series3);

        series3.setDrawDataPoints(true);
        series3.setDataPointsRadius(10);
        series3.setColor(Color.GREEN);



        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
        series.setCustomPaint(paint);
        series2.setCustomPaint(paint);
        series3.setCustomPaint(paint);



    }
}
