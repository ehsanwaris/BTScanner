package com.ed.btscanner;

import android.app.Activity;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ed.btscanner.database.DBHelper;
import com.ed.btscanner.database.model.RSSIEntryModel;

import java.util.List;

/**
 * Created by Ehsan Waris on 4/20/2020.
 */
public class EntryListAdapter extends ArrayAdapter<RSSIEntryModel>{

    private Context context;

    public EntryListAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    /**
     * Holder for the list items.
     */
    private class ViewHolder{
        TextView titleText;
        TextView distance;
        TextView rssi;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View line = null;
        RSSIEntryModel item = (RSSIEntryModel)getItem(position);
        final String name = item.getDevice_name();
        TextView testName = null;
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        viewToUse = mInflater.inflate(R.layout.entry_list_item, null);
        holder = new ViewHolder();
        holder.titleText = (TextView)viewToUse.findViewById(R.id.titleTextView);
        holder.rssi = (TextView)viewToUse.findViewById(R.id.txtRSSI);
        holder.distance = (TextView)viewToUse.findViewById(R.id.txtDistance);
        viewToUse.setTag(holder);

        testName = (TextView)viewToUse.findViewById(R.id.testName);
        line = (View)viewToUse.findViewById(R.id.line);
        holder.titleText.setText("Name:["+item.getDevice_name()+"]");
        holder.distance.setText("Distance:["+item.getDistance()+" feet]");
        holder.rssi.setText("RSSI:["+item.getRssi_value()+"]");
        testName.setText(item.getTest_name());

//        if (item.getDeviceName()!=null && item.getDeviceName().toString() == "No Devices") {
//            testName.setVisibility(View.INVISIBLE);
//            line.setVisibility(View.INVISIBLE);
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
//                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT, (int) RelativeLayout.LayoutParams.WRAP_CONTENT);
//            params.addRule(RelativeLayout.CENTER_VERTICAL);
//            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
//            holder.titleText.setLayoutParams(params);
//        }

        return viewToUse;
    }



}
