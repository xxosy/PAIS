package com.pais.sensormonitor.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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
enum ValueType {
    temperature, humidity, co2, light, ph, ec
}
public class SensorMonitorFragment extends Fragment implements SensorMonitorPresenter.View{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Inject
    SensorMonitorPresenter presenter;
    //state
    private ValueType mainValueType = ValueType.temperature;
    private ValueType subValueType = ValueType.humidity;
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
        tvDate = (TextView) root.findViewById(R.id.txt_date);
        tvSub = (TextView) root.findViewById(R.id.tv_sub_data);
        tvMain = (TextView) root.findViewById(R.id.tv_main_data);
        tvChoosable1 = (TextView) root.findViewById(R.id.tv_choosable_data_1);
        tvChoosable2 = (TextView) root.findViewById(R.id.tv_choosable_data_2);
        tvChoosable3 = (TextView) root.findViewById(R.id.tv_choosable_data_3);
        tvChoosable4 = (TextView) root.findViewById(R.id.tv_choosable_data_4);
        tvLabel1 = (TextView) root.findViewById(R.id.tv_choosable_label1);
        tvLabel2 = (TextView) root.findViewById(R.id.tv_choosable_label2);
        tvLabel3 = (TextView) root.findViewById(R.id.tv_choosable_label3);
        tvLabel4 = (TextView) root.findViewById(R.id.tv_choosable_label4);
        tvUpdateTime = (TextView) root.findViewById(R.id.update_time);

        btnChoosable1 = (LinearLayout) root.findViewById(R.id.btn_choosable_1);
        btnChoosable2 = (LinearLayout) root.findViewById(R.id.btn_choosable_2);
        btnChoosable3 = (LinearLayout) root.findViewById(R.id.btn_choosable_3);
        btnChoosable4 = (LinearLayout) root.findViewById(R.id.btn_choosable_4);
        presenter.init();
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

    private void setCurveGraph(ViewGroup viewGroup, String[] legendArr, float[] graph, String Name, int Color, int maxValue, int increment) {
        CurveGraphVO vo = makeCurveGraphAllSetting(legendArr, graph, Name, Color, maxValue, increment);
        final CurveGraphView cgv = new CurveGraphView(getActivity(), vo);
        viewGroup.removeAllViews();
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
