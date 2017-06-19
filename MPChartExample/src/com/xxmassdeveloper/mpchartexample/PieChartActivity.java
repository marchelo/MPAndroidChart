
package com.xxmassdeveloper.mpchartexample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.util.ArrayList;

public class PieChartActivity extends DemoBase/* implements OnSeekBarChangeListener,
        OnChartValueSelectedListener*/ {

    private PieChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_piechart);

        tvX = (TextView) findViewById(R.id.tvXMax);
        tvY = (TextView) findViewById(R.id.tvYMax);

        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
        mSeekBarX.setProgress(4);
        mSeekBarY.setProgress(10);

//        mSeekBarX.setOnSeekBarChangeListener(this);
//        mSeekBarY.setOnSeekBarChangeListener(this);

        mChart = (PieChart) findViewById(R.id.chart1);

        initPieChart(mChart);
        initPieChart((PieChart) findViewById(R.id.chart2));
    }

    private void initPieChart(PieChart pieChart) {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setCenterTextTypeface(mTfLight);
        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

//        ChartHighlighter highlighter = new ChartHighlighter(pieChart);
//
//        pieChart.setHighlighter(highlighter);
//        pieChart.getHighlighter().

        // pieChart.setUnit(" €");
        // pieChart.setDrawUnitsInChart(true);

        // add a selection listener
//        pieChart.setOnChartValueSelectedListener(this);

        setData(pieChart, 4, 100);

        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // pieChart.spin(2000, 0, 360);

//        Legend l = pieChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);

        pieChart.getLegend().setEnabled(false);

        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTypeface(mTfRegular);
        pieChart.setEntryLabelTextSize(12f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pie, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.actionToggleValues: {
//                for (IDataSet<?> set : mChart.getData().getDataSets())
//                    set.setDrawValues(!set.isDrawValuesEnabled());
//
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionToggleIcons: {
//                for (IDataSet<?> set : mChart.getData().getDataSets())
//                    set.setDrawIcons(!set.isDrawIconsEnabled());
//
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionToggleHole: {
//                if (mChart.isDrawHoleEnabled())
//                    mChart.setDrawHoleEnabled(false);
//                else
//                    mChart.setDrawHoleEnabled(true);
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionDrawCenter: {
//                if (mChart.isDrawCenterTextEnabled())
//                    mChart.setDrawCenterText(false);
//                else
//                    mChart.setDrawCenterText(true);
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionToggleXVals: {
//
//                mChart.setDrawEntryLabels(!mChart.isDrawEntryLabelsEnabled());
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionSave: {
//                // mChart.saveToGallery("title"+System.currentTimeMillis());
//                mChart.saveToPath("title" + System.currentTimeMillis(), "");
//                break;
//            }
//            case R.id.actionTogglePercent:
//                mChart.setUsePercentValues(!mChart.isUsePercentValuesEnabled());
//                mChart.invalidate();
//                break;
//            case R.id.animateX: {
//                mChart.animateX(1400);
//                break;
//            }
//            case R.id.animateY: {
//                mChart.animateY(1400);
//                break;
//            }
//            case R.id.animateXY: {
//                mChart.animateXY(1400, 1400);
//                break;
//            }
//            case R.id.actionToggleSpin: {
//                mChart.spin(1000, mChart.getRotationAngle(), mChart.getRotationAngle() + 360, Easing.EasingOption
//                        .EaseInCubic);
//                break;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//        tvX.setText("" + (mSeekBarX.getProgress()));
//        tvY.setText("" + (mSeekBarY.getProgress()));
//
//        setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
//    }

    private void setData(PieChart pieChart, int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    mParties[i % mParties.length],
                    getResources().getDrawable(R.drawable.star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(25f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(Color.TRANSPARENT);
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

//    @Override
//    public void onValueSelected(Entry e, Highlight h) {
//
//        if (e == null)
//            return;
//        Log.i("VAL SELECTED",
//                "Value: " + e.getY() + ", index: " + h.getX()
//                        + ", DataSet index: " + h.getDataSetIndex());
//    }
//
//    @Override
//    public void onNothingSelected() {
//        Log.i("PieChart", "nothing selected");
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        // TODO Auto-generated method stub
//
//    }
}
