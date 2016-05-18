package com.example.tuanlv.myweather.views;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tuanlv.myweather.R;
import com.example.tuanlv.myweather.controllers.AdapterListView;
import com.example.tuanlv.myweather.controllers.GPSTracker;
import com.example.tuanlv.myweather.controllers.MenuDrawer;
import com.example.tuanlv.myweather.fragment.FragmentCity;
import com.example.tuanlv.myweather.fragment.FragmentHour;
import com.example.tuanlv.myweather.fragment.FragmentMap;
import com.example.tuanlv.myweather.fragment.FragmentToday;
import com.example.tuanlv.myweather.fragment.Fragment_Helper;
import com.example.tuanlv.myweather.fragment.Fragment_evalute;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;
    private int selected=0;
    private int ImageLink[] = {R.drawable.ic_alarm_black_24dp,R.drawable.ic_today_black_24dp,R.drawable.ic_location_city_black_24dp,R.drawable.ic_location_on_black_24dp,R.drawable.ic_exit_to_app_black_24dp};
    private ArrayList<MenuDrawer> list =new ArrayList<MenuDrawer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = this.getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        String[] values = new String[]{
                getResources().getString(R.string.weather_hour),
                getResources().getString(R.string.weather_today),
                getResources().getString(R.string.weather_city),
                getResources().getString(R.string.weather_map),
                getResources().getString(R.string.exit),
        };
        list.add(new MenuDrawer(values[0],ImageLink[0]));
        list.add(new MenuDrawer(values[1],ImageLink[1]));
        list.add(new MenuDrawer(values[2],ImageLink[2]));
        list.add(new MenuDrawer(values[3],ImageLink[3]));
        list.add(new MenuDrawer(values[4],ImageLink[4]));
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, values);
//        mDrawerList.setAdapter(adapter);

        AdapterListView adapter=new AdapterListView(this,R.layout.custom_layout_listview,list);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectItem(position);
            }
        });
//        GPSTracker gps= new GPSTracker(this);
//
//        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            gps.showSettingsAlert();
//        }
        selectItem(0);
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        selected =position;
        switch (position) {
            case 0:
                mDrawerLayout.closeDrawer(mDrawerList);
                fragment = new FragmentHour();
                break;
            case 1:
                mDrawerLayout.closeDrawer(mDrawerList);
                fragment = new FragmentToday();
                break;
            case 2:
                mDrawerLayout.closeDrawer(mDrawerList);
                fragment = new FragmentCity();
                break;
            case 3:
                mDrawerLayout.closeDrawer(mDrawerList);
                fragment = new FragmentMap();
                break;
            case 4:
                mDrawerLayout.closeDrawer(mDrawerList);
                exit();
                return;
            default:
                mDrawerLayout.closeDrawer(mDrawerList);
                fragment = new FragmentToday();
                break;
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private void exit() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Bạn chắc chắn muốn thoát không?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                System.exit(0);
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager;
        switch (item.getItemId()) {
            case R.id.action_helper:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, new Fragment_Helper())
                        .addToBackStack(null)
                        .commit();
                return true;
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                return true;
            case R.id.evalute_software:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, new Fragment_evalute())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.action_share:
                Bitmap bitmap = takeScreenShot();
                saveBitmap(bitmap);
                share(bitmap);
                return true;
            case R.id.action_renew:
                selectItem(selected);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void share(Bitmap bitmap) {
        OutputStream output;
        // Tim đến vị chí lưu File
        File filepath = Environment.getExternalStorageDirectory();
        // Create a new folder AndroidBegin in SD Card
        File dir = new File(filepath.getAbsolutePath() + "/Share Image Tutorial/");
        dir.mkdirs();
        // Create a name for the saved image
        File file = new File(dir, "sample_wallpaper.png");
        try {
            // Share Intent
            Intent share = new Intent(Intent.ACTION_SEND);
            // Type of file to share
            share.setType("image/jpeg");
            output = new FileOutputStream(file);
            // Compress into png format image from 0% - 100%
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
            // Locate the image to Share
            Uri uri = Uri.fromFile(file);
            // Pass the image into an Intnet
            share.putExtra(Intent.EXTRA_STREAM, uri);

            // Show the social share chooser list
            startActivity(Intent.createChooser(share, "Share Image Tutorial"));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public Bitmap takeScreenShot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

}
