package com.pais.home.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pais.R;
import com.pais.domain.sensor.SensorItem;
import com.pais.home.adapter.SensorSpinnerAdapter;
import com.pais.home.dagger.HomeModule;
import com.pais.home.presenter.HomePresenter;
import com.pais.home.dagger.DaggerHomeComponent;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomePresenter.View{
    @Inject
    HomePresenter homePresenter;

    Spinner spinnerSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorSpinnerAdapter sensorSpinnerAdapter = new SensorSpinnerAdapter();

        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this,sensorSpinnerAdapter))
                .build()
                .inject(this);
        setCustomActionbar();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        homePresenter.initHome();
    }
    private void setCustomActionbar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_main, null);
        spinnerSensor = (Spinner) mCustomView.findViewById(R.id.spinner_sensor);
        actionBar.setCustomView(mCustomView);

        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0,0);

        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimary));

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT);
        actionBar.setCustomView(mCustomView,params);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, parent, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void refreshSensorSpinner(ArrayList<SensorItem> items) {
        ArrayList<String> array = new ArrayList<>();
        for(SensorItem item : items)
            array.add(item.getName());
        spinnerSensor.setAdapter(new ArrayAdapter<String>(this,R.layout.spinner_item,array));
    }

    @Override
    public void refreshDate(String date) {

    }

    @Override
    public void refreshUpdateTime(String time) {

    }

    @Override
    public void refreshMainSensorData() {

    }

    @Override
    public void refreshSubSensorData() {

    }

    @Override
    public void refreshSensorDataAtSettingTime() {

    }

    @Override
    public void refreshChoosableSensorData() {

    }

    @Override
    public void refreshSelectedSensorGraph() {

    }

    @Override
    public void refresh() {

    }
}
