package com.ed.btscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.ed.btscanner.database.DBHelper;
import com.ed.btscanner.database.model.RSSIEntryModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        List<RSSIEntryModel> entries = DBHelper.get(getApplicationContext()).GetAllRSSIEntries();
        EntryListAdapter adapter = new EntryListAdapter(getApplicationContext(),entries);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
