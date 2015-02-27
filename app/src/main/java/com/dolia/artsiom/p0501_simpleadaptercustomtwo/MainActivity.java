package com.dolia.artsiom.p0501_simpleadaptercustomtwo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    //map attributes names
    final String ATTR_NAME_TEXT = "text";
    final String ATTR_NAME_LL = "ll";
    final String ATTR_NAME_PB = "pb";

    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        int load[] = { 41, 48, 22, 35, 30, 67, 51, 88,
                        41, 48,22, 35, 30, 67, 51, 88 };

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(load.length);
        Map<String, Object> m;

        for(int i = 0; i < load.length; i++){
            m = new HashMap<String, Object>();
            m.put(ATTR_NAME_TEXT, "Day " + (i+1) + ". Load: " + load[i]);
            m.put(ATTR_NAME_PB, load[i]);
            m.put(ATTR_NAME_LL, load[i]);
            data.add(m);
        }

        String[] from = {ATTR_NAME_TEXT, ATTR_NAME_PB, ATTR_NAME_LL};
        int[] to = {R.id.tvLoad, R.id.pbLoad, R.id.llLoad};

        SimpleAdapter sAdapter = new SimpleAdapter(this, data,
                R.layout.item, from, to);
        sAdapter.setViewBinder(new MyViewBinder());

        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }


    class MyViewBinder implements SimpleAdapter.ViewBinder{

        int red = getResources().getColor(R.color.Red);
        int orange = getResources().getColor(R.color.Orange);
        int green = getResources().getColor(R.color.Green);

        @Override
        public boolean setViewValue(View view, Object data,
                                    String textRepresentation){
            int i = 0;
            Boolean result = false;

            switch (view.getId()){

                case R.id.llLoad:
                    i = ((Integer)data).intValue();

                    if(i < 40) view.setBackgroundColor(green);
                    else if (i < 70) view.setBackgroundColor(orange);
                    else view.setBackgroundColor(red);
                    result = true;
                    break;

                case  R.id.pbLoad:
                    i = ((Integer)data).intValue();
                    ((ProgressBar)view).setProgress(i);
                    result = true;
                    break;
            }
            return result;
        }
    }
}
