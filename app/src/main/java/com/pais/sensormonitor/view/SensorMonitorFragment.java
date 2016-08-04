package com.pais.sensormonitor.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.CurveGraphView;
import com.handstudio.android.hzgrapherlib.vo.GraphNameBox;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraph;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraphVO;
import com.pais.R;
import com.pais.domain.Value;
import com.pais.domain.graph.GraphItem;
import com.pais.domain.graph.GraphList;
import com.pais.domain.temperature.TemperatureItem;
import com.pais.domain.temperature.TemperatureList;
import com.pais.sensormonitor.dagger.DaggerSensorMonitorComponent;
import com.pais.sensormonitor.dagger.SensorMonitorModule;
import com.pais.sensormonitor.presenter.SensorMonitorPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensorMonitorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensorMonitorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SensorMonitorFragment extends Fragment implements SensorMonitorPresenter.View{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Inject
    SensorMonitorPresenter presenter;
    //state
    private int mainValueType = ValueTpye.TEMPERATURE;


    ViewGroup mChart;
    TextView tvUnit;
    TextView tvDate;
    TextView tvMain;
    TextView tvChoosableCo2;
    TextView tvChoosableHumidity;
    TextView tvChoosableTemperature;
    TextView tvChoosableEc;
    TextView tvChoosableLight;
    TextView tvChoosablePh;
    TextView tvChoosableTemperature2;
    TextView tvUpdateTime;

    LinearLayout btnChoosableTemperature;
    LinearLayout btnChoosableHumidity;
    LinearLayout btnChoosableEc;
    LinearLayout btnChoosablePh;
    LinearLayout btnChoosableCo2;
    LinearLayout btnChoosableLight;
    LinearLayout btnChoosableTemperature2;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SensorMonitorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorMonitorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorMonitorFragment newInstance(String param1, String param2) {
        SensorMonitorFragment fragment = new SensorMonitorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        DaggerSensorMonitorComponent.builder()
                .sensorMonitorModule(new SensorMonitorModule(this))
                .build()
                .inject(this);
        View root = inflater.inflate(R.layout.fragment_sensor_monitor, container, false);
        mChart = (ViewGroup) root.findViewById(R.id.graphview);
        tvUnit = (TextView) root.findViewById(R.id.tv_unit);
        tvDate = (TextView) root.findViewById(R.id.txt_date);
        tvMain = (TextView) root.findViewById(R.id.tv_main_data);
        tvChoosableTemperature = (TextView)root.findViewById(R.id.tv_choosable_data_temp);
        tvChoosableLight = (TextView)root.findViewById(R.id.tv_choosable_data_light);
        tvChoosableTemperature2 = (TextView)root.findViewById(R.id.tv_choosable_data_temp);
        tvChoosableCo2 = (TextView) root.findViewById(R.id.tv_choosable_data_co2);
        tvChoosablePh = (TextView) root.findViewById(R.id.tv_choosable_data_ph);
        tvChoosableHumidity = (TextView) root.findViewById(R.id.tv_choosable_data_humidity);
        tvChoosableEc = (TextView) root.findViewById(R.id.tv_choosable_data_ec);
        tvUpdateTime = (TextView) root.findViewById(R.id.update_time);

        btnChoosableTemperature = (LinearLayout) root.findViewById(R.id.btn_choosable_temp);
        btnChoosableClick(btnChoosableTemperature);
        btnChoosableHumidity = (LinearLayout) root.findViewById(R.id.btn_choosable_humidity);
        btnChoosableClick(btnChoosableHumidity);
        btnChoosableEc = (LinearLayout) root.findViewById(R.id.btn_choosable_ec);
        btnChoosableClick(btnChoosableEc);
        btnChoosablePh = (LinearLayout) root.findViewById(R.id.btn_choosable_ph);
        btnChoosableClick(btnChoosablePh);
        btnChoosableCo2 = (LinearLayout) root.findViewById(R.id.btn_choosable_co2);
        btnChoosableClick(btnChoosableCo2);
        btnChoosableLight = (LinearLayout) root.findViewById(R.id.btn_choosable_light);
        btnChoosableClick(btnChoosableLight);
        btnChoosableTemperature2 = (LinearLayout) root.findViewById(R.id.btn_choosable_temp);
        btnChoosableClick(btnChoosableTemperature2);
        presenter.init(mParam1);
        return root;
    }
        //TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void btnChoosableClick(LinearLayout btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_choosable_temp){
                    mainValueType = ValueTpye.TEMPERATURE;
                    tvUnit.setText("°C");
                }else if(v.getId()==R.id.btn_choosable_humidity){
                    mainValueType = ValueTpye.HUMIDITY;
                    tvUnit.setText("%");
                }else if(v.getId()==R.id.btn_choosable_co2){
                    mainValueType = ValueTpye.CO2;
                    tvUnit.setText("ppm");
                }else if(v.getId()==R.id.btn_choosable_ec){
                    mainValueType = ValueTpye.EC;
                    tvUnit.setText("us/cm");
                }else if(v.getId()==R.id.btn_choosable_ph){
                    mainValueType = ValueTpye.PH;
                    tvUnit.setText("");
                }else if(v.getId()==R.id.btn_choosable_light){
                    mainValueType = ValueTpye.LIGHT;
                    tvUnit.setText("lux");
                }
                presenter.choosableSensorDataTouched(mainValueType);
            }
        });
    }
    private void setCurveGraph(ViewGroup viewGroup, String[] legendArr, float[] graph, String Name, int Color, int maxValue, int increment) {
        CurveGraphVO vo = makeCurveGraphAllSetting(legendArr, graph, Name, Color, maxValue, increment);
        final CurveGraphView cgv = new CurveGraphView(getActivity(), vo);
        viewGroup.removeAllViews();
        int height = viewGroup.getHeight()-2;
        ViewGroup.LayoutParams prams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
        viewGroup.addView(cgv,prams);

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
    public void refreshChart(GraphList items) {
        String[] legend= new String[items.getGraphItems().length];
        float[] data = new float[items.getGraphItems().length];
        int i = 0;
        float fmax = 0;
        String tag = "온도";
        int term=10;
        int color = new Color().rgb(0,153,255);
        for(GraphItem item : items.getGraphItems()){
            legend[i] = item.getUpdate_time();
            if(mainValueType==ValueTpye.TEMPERATURE){
                data[i] = Float.parseFloat(item.getTemperature());
                tag = "온도";
                color = new Color().rgb(0,153,255);
            }else if(mainValueType==ValueTpye.HUMIDITY){
                data[i] = Float.parseFloat(item.getHumidity());
                tag = "습도";
                color = new Color().rgb(0,204,255);
            }else if(mainValueType==ValueTpye.CO2){
                data[i] = Float.parseFloat(item.getCo2());
                tag = "CO2";
                color = new Color().rgb(0,128,128);
            }else if(mainValueType==ValueTpye.LIGHT){
                data[i] = Float.parseFloat(item.getLight());
                tag = "빛";
                color = new Color().rgb(255,204,0);
            }else if(mainValueType==ValueTpye.PH){
                data[i] = Float.parseFloat(item.getPh());
                tag = "PH";
                color = new Color().rgb(0,255,0);
            }else if(mainValueType==ValueTpye.EC){
                data[i] = Float.parseFloat(item.getEc());
                tag = "EC";
                color = new Color().rgb(255,140,20);
            }
            if(fmax<data[i])
                fmax = data[i];
            i++;
        }
        int max = (int)fmax;
        max +=10;
        max = max/10*10;
        term = max/4;
        setCurveGraph(mChart, legend,data,tag, color,max,term);
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
        if(mainValueType==ValueTpye.TEMPERATURE){
            main = mainData.getTemperature();
        }else if(mainValueType==ValueTpye.HUMIDITY){
            main = mainData.getHumidity();
        }else if(mainValueType==ValueTpye.CO2){
            main = mainData.getCo2();
        }else if(mainValueType==ValueTpye.LIGHT){
            main = mainData.getLight();
        }else if(mainValueType==ValueTpye.PH){
            main = mainData.getPh();
        }else if(mainValueType==ValueTpye.EC){
            main = mainData.getEc();
        }
        tvMain.setText(main);
    }


    @Override
    public void refreshSensorDataAtSettingTime() {

    }

    @Override
    public void refreshChoosableSensorData(Value data) {
        tvChoosableTemperature.setText(data.getTemperature());
        tvChoosableLight.setText(data.getLight());;
        tvChoosableTemperature2.setText(data.getTemperature());
        tvChoosableCo2.setText(data.getCo2());
        tvChoosablePh.setText(data.getPh());
        tvChoosableHumidity.setText(data.getHumidity());
        tvChoosableEc.setText(data.getEc());
    }


    @Override
    public void refreshSelectedSensorGraph() {

    }

    @Override
    public void refresh() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
