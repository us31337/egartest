package com.egartech.test.service;

import com.egartech.test.model.Record;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class GraphDesigner {

    @Autowired
    private RecordService recordService;

    public byte[] getAllUniq() {
        var chart = draw();
        List<String> arrayCompanyNames = recordService.getUniqNames();
        for (String companyName : arrayCompanyNames) {
            LinkedList<Date> xDate = new LinkedList<>(); //because it's already sorted by SQL query
            LinkedList<Double> yPrice = new LinkedList<>();
            var list = recordService.getAllByName(companyName);
            list.forEach(record -> {
                        xDate.add(record.getDate());
                        yPrice.add(record.getPrice());
                    });
            XYSeries seriesLiability = chart.addSeries(companyName, xDate, yPrice);
            seriesLiability.setMarker(SeriesMarkers.CIRCLE);
        }

        try {
            return BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.PNG);
            /*BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
            VectorGraphicsEncoder.saveVectorGraphic(chart, "./Sample_Chart", VectorGraphicsEncoder.VectorGraphicsFormat.SVG);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private XYChart draw() {
        XYChart chart = new XYChartBuilder().width(800).height(600).build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setYAxisLabelAlignment(Styler.TextAlignment.Right);
        chart.getStyler().setYAxisDecimalPattern("#,###.##");
        chart.getStyler().setPlotMargin(0);
        chart.getStyler().setPlotContentSize(.95);
        return chart;
    }

}
