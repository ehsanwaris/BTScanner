package com.ed.btscanner;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Matt on 5/12/2015.
 */
public class DeviceListAdapter extends ArrayAdapter<DeviceItem>{

    private Context context;
    private BluetoothAdapter bTAdapter;
    private List<DeviceItem>items;

    public DeviceListAdapter(Context context, List items, BluetoothAdapter bTAdapter) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.bTAdapter = bTAdapter;
        this.context = context;
        this.items = items;
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
        DeviceItem item = (DeviceItem)getItem(position);
        final String name = item.getDeviceName();
        TextView macAddress = null;
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        viewToUse = mInflater.inflate(R.layout.device_list_item, null);
        holder = new ViewHolder();
        holder.titleText = (TextView)viewToUse.findViewById(R.id.titleTextView);
        holder.rssi = (TextView)viewToUse.findViewById(R.id.txtRSSI);
        holder.distance = (TextView)viewToUse.findViewById(R.id.txtDistance);
        viewToUse.setTag(holder);

        macAddress = (TextView)viewToUse.findViewById(R.id.macAddress);
        line = (View)viewToUse.findViewById(R.id.line);
        holder.titleText.setText("Name:["+item.getDeviceName()+"]");
        holder.titleText.setTag(item.getDeviceName());
        holder.distance.setText("Distance:["+item.getDistanceInMeters()+" m]");
        holder.rssi.setText("RSSI:["+item.getRssi()+"]");
        holder.rssi.setTag(item.getRssi());
        macAddress.setText(item.getAddress());

        if (item.getDeviceName()!=null && item.getDeviceName().toString() == "No Devices") {
            macAddress.setVisibility(View.INVISIBLE);
            line.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT, (int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            holder.titleText.setLayoutParams(params);
        }
        viewToUse.findViewById(R.id.btnStartTest).setTag(getItem(position));
        viewToUse.findViewById(R.id.btnStartTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeviceItem selectedDevice = (DeviceItem) v.getTag();
                Toast.makeText(getContext(),"Starting Test with "+selectedDevice.getDeviceName(),Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getContext(), ListActivity.class);
                in.putExtra("macAddress",selectedDevice.getAddress());
                in.putExtra("deviceName",selectedDevice.getDeviceName());
                if(bTAdapter!=null && bTAdapter.isDiscovering())
                    bTAdapter.cancelDiscovery();
                getContext().startActivity(in);
                ((Activity)getContext()).finish();


                // custom dialog

//                String rssi = holder.rssi.getTag().toString();
//                String device = holder.titleText.getTag().toString();

//                final Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.dialog_layout);
//                dialog.setCancelable(false);
//
//                Button dialogButton = (Button) dialog.findViewById(R.id.btnCancel);
//                Button dialogButtonSave = (Button) dialog.findViewById(R.id.btnSave);
//
//                final  EditText etDialogRssi = ((EditText)dialog.findViewById(R.id.etRSSI));
//                etDialogRssi.setText(rssi);
//                final  EditText etDialogDeviceName = ((EditText)dialog.findViewById(R.id.etDeviceName));
//                etDialogDeviceName.setText(device);
//                final  EditText etDialogTestName = ((EditText)dialog.findViewById(R.id.etTestName));
//                final  EditText etDialogDistance = ((EditText)dialog.findViewById(R.id.etDistance));
//                final  EditText etDialogOrientation = ((EditText)dialog.findViewById(R.id.etOrientation));
//
//
//                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                        Toast.makeText(getContext(),"Dismissed..!!",Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                dialogButtonSave.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String rssi = etDialogRssi.getText().toString();
//                        String device = etDialogDeviceName.getText().toString();
//                        String testName = etDialogTestName.getText().toString();
//                        String distance = etDialogDistance.getText().toString();
//                        String orientation = etDialogOrientation.getText().toString();
//                        if(testName.isEmpty() || distance.isEmpty()||orientation.isEmpty()){
//                            Toast.makeText(getContext(),"Please enter all fields.", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        RSSIEntryModel entry = new RSSIEntryModel();
//                        entry.setTest_name(testName);
//                        entry.setTime_stamp(System.currentTimeMillis());
//                        entry.setOrientation(orientation);
//
//                        entry.setDistance(Double.parseDouble(distance));
//                        entry.setDevice_name(device);
//
//                        long res = DBHelper.get(getContext()).AddRSSIEntry(entry);
//                        if(res ==-1){
//                            Toast.makeText(getContext(),"Error Saving Record", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(getContext(),"Saved Successfully", Toast.LENGTH_SHORT).show();
//                        }
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//                Window window = dialog.getWindow();
//                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });
        return viewToUse;
    }

    private static String getOrienation(Context context){
        int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // code for portrait mode
            return "PORTRAIT";
        } else {
            // code for landscape mode
            return "LANDSCAPE";
        }
    }

}
