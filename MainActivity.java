package com.orchid.countdown;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "II", "JJ", "KK", "LL"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        Log.v("MainActivity", "App Running Successful!");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, data[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting) {
            Log.i("MainActivity", item.toString());
            Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.share) {
            Log.i("MainActivity", item.toString());
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // getPackageName()是你当前类的包名
            PackageInfo packInfo = null;
            try {
                packInfo = this.getPackageManager().getPackageInfo("com.orchid.countdown", 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            File file = new File(packInfo.applicationInfo.sourceDir);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            startActivity(Intent.createChooser(intent, getTitle()));
            return true;
//            Toast.makeText(MainActivity.this,"Share",Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.add) {
            Log.i("MainActivity", item.toString());
            Toast.makeText(MainActivity.this, "Add", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("MainActivity", item.toString());
            Toast.makeText(MainActivity.this, "NULL", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}