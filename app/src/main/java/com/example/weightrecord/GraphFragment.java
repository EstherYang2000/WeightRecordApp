package com.example.weightrecord;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weightrecord.data.AppDatabase;
import com.example.weightrecord.data.DataConverter;
import com.example.weightrecord.data.DateConverter;
import com.example.weightrecord.data.Weight;
import com.example.weightrecord.data.WeightDao;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.PrimitiveIterator;
import java.util.concurrent.TimeUnit;


public class GraphFragment extends Fragment {


private View view;
AppDatabase mDb;
    private static final String DATE_FORMAT = "dd/MM/yyy";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    private  String [] dates ;
    List<Weight> WeightList;
    float weight;
    String date;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_graph, container, false);
        mDb=AppDatabase.getDbInstance(getActivity().getApplicationContext());
        BarChart barChart=view.findViewById(R.id.barchart);
        ArrayList<BarEntry> dailyweight=new ArrayList<>();
         WeightList=mDb.weightDao().getAllWeights();
        ArrayList<Long> dateList = new ArrayList<Long>();

        for(int i=0;i<WeightList.size();i++){
//            dateList.add(DataConverter.toTimestamp(WeightList.get(i).getDate())) ;

//            float date=(Float)(dateList.get(i));
             weight=Float.parseFloat(WeightList.get(i).getWeight());
//            date= dateFormat.format(WeightList.get(i).getDate());
//           long datelong=Long.parseLong(date);

//            dailyweight.add(new BarEntry((float)datelong,(float)weight));

            dailyweight.add(new BarEntry(i+1,weight));
        }
        BarDataSet barDataSet=new BarDataSet(dailyweight,"History Weight");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextColor((int) 16f);
        BarData barData=new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Weight Bar Chart");
        barChart.animateY(2000);
//        barChart.moveViewToX(getLongDateWithoutTime()-1209600000);
//
//        XAxis xAxis=barChart.getXAxis();
//        xAxis.setValueFormatter(new MyYAxisValueFormatter());
//        xAxis.setTextSize(14);
//        xAxis.setGranularity(2f);
//        xAxis.setLabelCount(6);
//        xAxis.setDrawAxisLine(true);
//        xAxis.setDrawGridLines(false);

        return view;
    }
    public class MyXAxisValueFormatter implements IAxisValueFormatter {
        //Makes X axis human readable dates
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String result = sdf.format(new Date((long)value));

            return result;
        }
    }

    public class MyYAxisValueFormatter implements IAxisValueFormatter {
        //Removes decimal places on Y axis labels
        @Override
        public String getFormattedValue(float value, AxisBase axis) {

            return String.format(Locale.ENGLISH, "%.0f%s", value, weight);
        }
    }

    public class WeightValueFormatter implements IValueFormatter {
        //1 decimal place on data point labels
        //Actual data values remain untouched
        //eg the points 1.11 and 1.12 will have the same label but will appear apart on the graph
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            String result = String.format(Locale.ENGLISH, "%.1f", value);
            return result;
        }
    }
    public static long getLongDateWithoutTime(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }

//public class MyAxisisValueFormatter implements IAxisValueFormatter {
//
//    private String [] mValues;
//
//    public MyAxisisValueFormatter(String [] values) {
//        this.mValues=values;
//
//
//    }
//    @Override
//    public String getFormattedValue(float value, AxisBase axis) {
//        return mValues[(int)value];
//    }
//}
//    public void dategetter(){
//        for(int i=0;i<WeightList.size();i++){
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date date = WeightList.get(i).getDate();
//            String dateToStr = dateFormat.format(date);
//            String dateList= String.valueOf(dateToStr);
//
////            String dateList= String.valueOf(dateFormat.format(WeightList.get(i).getDate()));
//            dates[i]=dateList;
//        }
//
//        };
    }



