package com.pais.home.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.CurveGraphView;
import com.handstudio.android.hzgrapherlib.vo.GraphNameBox;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraph;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraphVO;
import com.pais.R;
import com.pais.domain.Value;
import com.pais.domain.sensor.SensorItem;
import com.pais.domain.temperature.TemperatureItem;
import com.pais.domain.temperature.TemperatureList;
import com.pais.home.adapter.SensorSpinnerAdapter;
import com.pais.home.dagger.HomeModule;
import com.pais.home.presenter.HomePresenter;
import com.pais.home.dagger.DaggerHomeComponent;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
enum ValueType{
    temperature, humidity, co2,light,ph,ec
}
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomePresenter.View {
    //state
    private ValueType mainValueType = ValueType.temperature;
    private ValueType subValueType = ValueType.humidity;
    private String serial;

    @Inject
    HomePresenter homePresenter;

    Spinner spinnerSensor;

    View includedView;

    ViewGroup mChart;
    TextView tvDate;
    TextView tvSub;
    TextView tvMain;
    TextView tvChoosable1;
    TextView tvChoosable2;
    TextView tvChoosable3;
    TextView tvChoosable4;
    TextView tvLabel1;
    TextView tvLabel2;
    TextView tvLabel3;
    TextView tvLabel4;
    TextView tvUpdateTime;
    LinearLayout btnChoosable1;
    LinearLayout btnChoosable2;
    LinearLayout btnChoosable3;
    LinearLayout btnChoosable4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorSpinnerAdapter sensorSpinnerAdapter = new SensorSpinnerAdapter();
        initViews();
        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this,sensorSpinnerAdapter))
                .build()
                .inject(this);
        setCustomActionbar();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        homePresenter.initHome();
    }
    private void initViews(){
        includedView = (View) findViewById(R.id.content_main);
        mChart = (ViewGroup) includedView.findViewById(R.id.graphview);
        tvDate =(TextView) includedView.findViewById(R.id.txt_date);
        tvSub = (TextView) includedView.findViewById(R.id.tv_sub_data);
        tvMain = (TextView) includedView.findViewById(R.id.tv_main_data);
        tvChoosable1 = (TextView) includedView.findViewById(R.id.tv_choosable_data_1);
        tvChoosable2 = (TextView) includedView.findViewById(R.id.tv_choosable_data_2);
        tvChoosable3 = (TextView) includedView.findViewById(R.id.tv_choosable_data_3);
        tvChoosable4 = (TextView) includedView.findViewById(R.id.tv_choosable_data_4);
        tvLabel1 = (TextView) includedView.findViewById(R.id.tv_choosable_label1);
        tvLabel2 = (TextView) includedView.findViewById(R.id.tv_choosable_label2);
        tvLabel3 = (TextView) includedView.findViewById(R.id.tv_choosable_label3);
        tvLabel4 = (TextView) includedView.findViewById(R.id.tv_choosable_label4);
        tvUpdateTime = (TextView) includedView.findViewById(R.id.update_time);

        btnChoosable1 = (LinearLayout) includedView.findViewById(R.id.btn_choosable_1);
        btnChoosable2 = (LinearLayout) includedView.findViewById(R.id.btn_choosable_2);
        btnChoosable3 = (LinearLayout) includedView.findViewById(R.id.btn_choosable_3);
        btnChoosable4 = (LinearLayout) includedView.findViewById(R.id.btn_choosable_4);

        View.OnClickListener choosableClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_choosable_1){
                    setMainValueType(tvLabel1);
                }else if(v.getId()==R.id.btn_choosable_2){
                    setMainValueType(tvLabel2);
                }else if(v.getId()==R.id.btn_choosable_3){
                    setMainValueType(tvLabel3);
                }else if(v.getId()==R.id.btn_choosable_4){
                    setMainValueType(tvLabel4);
                }
                homePresenter.initHome();
            }
            private void setMainValueType(TextView tv){
                if(tv.getText().equals("CO2")){
                    mainValueType = ValueType.co2;
                    subValueType = ValueType.light;
                }else if(tv.getText().equals("광량")){
                    mainValueType = ValueType.light;
                    subValueType = ValueType.co2;
                }else if(tv.getText().equals("ph")){
                    mainValueType = ValueType.ph;
                    subValueType = ValueType.ec;
                }else if(tv.getText().equals("ec")){
                    mainValueType = ValueType.ec;
                    subValueType = ValueType.ph;
                }else if(tv.getText().equals("온도")){
                    mainValueType = ValueType.temperature;
                    subValueType = ValueType.humidity;
                }else if(tv.getText().equals("습도")){
                    mainValueType = ValueType.humidity;
                    subValueType = ValueType.temperature;
                }
            }
        };
        btnChoosable1.setOnClickListener(choosableClickListener);
        btnChoosable2.setOnClickListener(choosableClickListener);
        btnChoosable3.setOnClickListener(choosableClickListener);
        btnChoosable4.setOnClickListener(choosableClickListener);

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
        spinnerSensor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSerial = items.get(position).getSerial();
                serial = itemSerial;
                homePresenter.spinnerItemChanged(serial);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setCurveGraph(ViewGroup viewGroup, String[] legendArr, float[] graph, String Name, int Color, int maxValue, int increment) {
        CurveGraphVO vo = makeCurveGraphAllSetting(legendArr, graph, Name, Color, maxValue, increment);
        final CurveGraphView cgv = new CurveGraphView(this, vo);
        viewGroup.addView(cgv);
    }

    private CurveGraphVO makeCurveGraphAllSetting(String[] legendArr, float[] graph, String Name, int Color, int maxValue, int increment) {
        //padding
        int paddingBottom = CurveGraphVO.DEFAULT_PADDING;
        int paddingTop = CurveGraphVO.DEFAULT_PADDING;
        int paddingLeft = CurveGraphVO.DEFAULT_PADDING;
        int paddingRight = CurveGraphVO.DEFAULT_PADDING;

        //graph margin
        int marginTop = CurveGraphVO.DEFAULT_MARGIN_TOP;
        int marginRight = CurveGraphVO.DEFAULT_MARGIN_RIGHT;

        //max value

        //increment

        List<CurveGraph> arrGraph = new ArrayList<CurveGraph>();

        arrGraph.add(new CurveGraph(Name, Color, graph));

        CurveGraphVO vo = new CurveGraphVO(
                paddingBottom, paddingTop, paddingLeft, paddingRight,
                marginTop, marginRight, maxValue, increment, legendArr, arrGraph);
        vo.setAnimation(new GraphAnimation(GraphAnimation.LINEAR_ANIMATION, GraphAnimation.DEFAULT_DURATION));
        vo.setGraphNameBox(new GraphNameBox());
        return vo;
    }

    @Override
    public void refreshChart(TemperatureList items) {
        String[] legend= new String[items.getTemperatureItems().length];
        float[] data = new float[items.getTemperatureItems().length];
        int i = 0;
        float fmax = 0;

        for(TemperatureItem item : items.getTemperatureItems()){
            legend[i] = item.getUpdate_time();
            data[i] = Float.parseFloat(item.getTemperature());
            if(fmax<data[i])
                fmax = data[i];
            i++;
        }
        int max = (int)fmax;
        max +=10;
        max = max/10*10;
        setCurveGraph(mChart, legend,data,"온도", Color.BLUE,max,10);
    }

    @Override
    public void refreshDate(String date) {
        Date today = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tvDate.setText(sdf.format(today));
    }

    @Override
    public void refreshUpdateTime(String time) {
        tvUpdateTime.setText(time);
    }

    @Override
    public void refreshMainSensorData(Value mainData) {
        String main = mainData.getTemperature();
        if(mainValueType.equals(ValueType.temperature)){
            main = mainData.getTemperature();
        }else if(mainValueType.equals(ValueType.humidity)){
            main = mainData.getHumidity();
        }else if(mainValueType.equals(ValueType.co2)){
            main = mainData.getCo2();
        }else if(mainValueType.equals(ValueType.light)){
            main = mainData.getLight();
        }else if(mainValueType.equals(ValueType.ph)){
            main = mainData.getPh();
        }else if(mainValueType.equals(ValueType.ec)){
            main = mainData.getEc();
        }
        tvMain.setText(main);
    }

    @Override
    public void refreshSubSensorData(Value subData) {
        String sub = subData.getTemperature();
        if(subValueType.equals(ValueType.temperature)){
            sub = subData.getTemperature();
        }else if(subValueType.equals(ValueType.humidity)){
            sub = subData.getHumidity();
        }else if(subValueType.equals(ValueType.co2)){
            sub = subData.getCo2();
        }else if(subValueType.equals(ValueType.light)){
            sub = subData.getLight();
        }else if(subValueType.equals(ValueType.ph)){
            sub = subData.getPh();
        }else if(subValueType.equals(ValueType.ec)){
            sub = subData.getEc();
        }
        tvSub.setText(sub);
    }


    @Override
    public void refreshSensorDataAtSettingTime() {

    }

    @Override
    public void refreshChoosableSensorData(String temp, String humid, String co2, String light,String ph, String ec) {
        String[] label = new String[4];
        label[0] = "CO2";
        label[1] = "광량";
        label[2] = "ph";
        label[3] = "ec";
        String[] data = new String[4];
        data[0] = co2;
        data[1] = light;
        data[2] = ph;
        data[3] = ec;
        if(mainValueType.equals(ValueType.temperature)){
        }else if(mainValueType.equals(ValueType.humidity)){
        }else if(mainValueType.equals(ValueType.co2)){
            label[0] = "온도";
            label[1] = "습도";
            data[0] = temp;
            data[1] = humid;
        }else if(mainValueType.equals(ValueType.light)){
            label[0] = "온도";
            label[1] = "습도";
            data[0] = temp;
            data[1] = humid;
        }else if(mainValueType.equals(ValueType.ph)){
            label[0] = "온도";
            label[1] = "습도";
            label[2] = "CO2";
            label[3] = "광량";
            data[0] = temp;
            data[1] = humid;
            data[2] = co2;
            data[3] = light;
        }else if(mainValueType.equals(ValueType.ec)){
            label[0] = "온도";
            label[1] = "습도";
            label[2] = "CO2";
            label[3] = "광량";
            data[0] = temp;
            data[1] = humid;
            data[2] = co2;
            data[3] = light;
        }
        tvLabel1.setText(label[0]);
        tvLabel2.setText(label[1]);
        tvLabel3.setText(label[2]);
        tvLabel4.setText(label[3]);

        tvChoosable1.setText(data[0]);
        tvChoosable2.setText(data[1]);
        tvChoosable3.setText(data[2]);
        tvChoosable4.setText(data[3]);
    }


    @Override
    public void refreshSelectedSensorGraph() {

    }

    @Override
    public void refresh() {

    }
}

