package hr.foi.air.osobnibankar.grafovi;

import hr.foi.air.osobnibankar.db.Transakcija;

import java.text.SimpleDateFormat;
import java.util.List;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.XYItemRenderer;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.data.time.Month;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.XYDataset;
import org.afree.graphics.SolidColor;
import org.afree.ui.RectangleInsets;

import com.activeandroid.query.Select;

import android.content.Context;
import android.graphics.Color;


public class LineGraf extends GrafView {

	public LineGraf(Context context) {
        super(context);

        final AFreeChart chart = createChart(createDataset());

        setChart(chart);
    }

    private static AFreeChart createChart(XYDataset dataset) {

        AFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Vremenski pregled prihoda, rashoda i �tednje",  // title
            "Datum",             // x-axis label
            "Iznos",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        chart.setBackgroundPaintType(new SolidColor(Color.WHITE));

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaintType(new SolidColor(Color.LTGRAY));
        plot.setDomainGridlinePaintType(new SolidColor(Color.WHITE));
        plot.setRangeGridlinePaintType(new SolidColor(Color.WHITE));
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

        return chart;

    }

    private static XYDataset createDataset() {
    	TimeSeries s1 = new TimeSeries("Iznos prihoda");
    	TimeSeries s2 = new TimeSeries("Iznos rashoda");
    	
    	TimeSeriesCollection dataset = new TimeSeriesCollection();

        List<Transakcija> listaPrihoda = new Select().all().from(Transakcija.class).where("tip_id=0").execute();
        
        for (int i =0;i<listaPrihoda.size();i++) {
        
   
        Double iznos = listaPrihoda.get(i).iznos;
            
        String datum = listaPrihoda.get(i).datum;
		

		String[] dohvatiMjesec = datum.split("\\.");
		String mjeseci = dohvatiMjesec[1];
		int mjesec = Integer.parseInt(mjeseci);

		String[] dohvatiGodinu = datum.split("\\.");
		String godina = dohvatiGodinu[2];
		int godine = Integer.parseInt(godina);
        
        s1.addOrUpdate(new Month(mjesec, godine), iznos);
        
        }
        
        List<Transakcija> listaRashoda = new Select().all().from(Transakcija.class).where("tip_id=1").execute();
        
        for (int i =0;i<listaRashoda.size();i++) {
        
   
        Double iznos = listaRashoda.get(i).iznos;
            
        String datum = listaRashoda.get(i).datum;
		

		String[] dohvatiMjesec = datum.split("\\.");
		String mjeseci = dohvatiMjesec[1];
		int mjesec = Integer.parseInt(mjeseci);

		String[] dohvatiGodinu = datum.split("\\.");
		String godina = dohvatiGodinu[2];
		int godine = Integer.parseInt(godina);
        
        s2.addOrUpdate(new Month(mjesec, godine), iznos);
        
        }
        
        dataset.addSeries(s2);
        dataset.addSeries(s1);
        return dataset;

    }

}