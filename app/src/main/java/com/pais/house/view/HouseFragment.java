package com.pais.house.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.pais.R;
import com.pais.house.adapter.HouseAdapter;
import com.pais.house.adapter.HouseAdapterDataView;
import com.pais.house.dagger.DaggerHouseComponent;
import com.pais.house.dagger.HouseModule;
import com.pais.house.presenter.HousePresenter;
import com.pais.views.OnRecyclerItemDeleteClickListener;
import com.pais.views.OnRecyclerItemModifyClickListener;
import com.pais.views.PageChange;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HouseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HouseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HouseFragment extends Fragment implements HousePresenter.View{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Inject
    HousePresenter presenter;


    FloatingActionButton btnAddHouse;

    LinearLayout addWindow;
    LinearLayout btnAddCancle;
    EditText etHouseName;
    LinearLayout btnAddOk;
    RecyclerView rvHouseList;
    HouseAdapterDataView mHouseAdapterDataView;

    private static PageChange pageChange = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HouseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HouseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HouseFragment newInstance(String param1, String param2, PageChange mPageChange) {
        HouseFragment fragment = new HouseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        pageChange  = mPageChange;
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
        DaggerHouseComponent.builder()
                .houseModule(new HouseModule(this))
                .build()
                .Inject(this);
        View root = inflater.inflate(R.layout.fragment_house, container, false);
        presenter.initDataBase(getActivity());

        btnAddHouse = (FloatingActionButton) root.findViewById(R.id.btn_add_house);
        btnAddHouse.setOnClickListener(v -> presenter.btnAddHouseClicked());
        addWindow = (LinearLayout) root.findViewById(R.id.add_window);
        btnAddCancle = (LinearLayout) root.findViewById(R.id.btn_add_cancle);
        btnAddCancle.setOnClickListener(v -> {
            presenter.addCancleClick();
            hideSoftKeyboard(etHouseName);
        });
        etHouseName = (EditText) root.findViewById(R.id.et_house_name);
        btnAddOk = (LinearLayout) root.findViewById(R.id.btn_add_ok);
        btnAddOk.setOnClickListener(v -> {
            presenter.addOkClick(etHouseName.getText().toString());
            hideSoftKeyboard(etHouseName);
        });

        rvHouseList = (RecyclerView)root.findViewById(R.id.rv_house_list);
        HouseAdapter mHouseAdapter = new HouseAdapter(getActivity());
        presenter.setHouseAdapterDataModel(mHouseAdapter);
        mHouseAdapterDataView = mHouseAdapter;
        rvHouseList.setAdapter(mHouseAdapter);
        presenter.initRecycler();
        rvHouseList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHouseAdapterDataView.setOnRecyclerItemClickListener((adapter, position) -> presenter.onItemClick(position));
        mHouseAdapterDataView.setOnRecyclerItemDeleteClickListener((adapter, position) -> presenter.onItemDeleteClick(position));
        mHouseAdapterDataView.setOnRecyclerItemModifyClickListener((adapter, position) -> presenter.onItemModifyClick(position));
        presenter.setPageChange(pageChange);
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    @Override
    public void showAddWindow() {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0f);
        animation.setDuration(300);
        addWindow.setVisibility(View.VISIBLE);
        addWindow.setAnimation(animation);
        hideBtnAddHouse();
    }
    protected void hideSoftKeyboard(View view) {
        InputMethodManager mgr = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void hideAddWindow() {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -1.0f);
        animation.setDuration(300);
        addWindow.setAnimation(animation);
        addWindow.setVisibility(View.GONE);
        showBtnAddHouse();
    }

    @Override
    public void refreshRecycler() {
        mHouseAdapterDataView.refresh();
    }

    @Override
    public void setEditTextHouseName(String name) {
        etHouseName.setText(name);
    }

    @Override
    public void hideBtnAddHouse() {
        btnAddHouse.setVisibility(View.GONE);

    }
    @Override
    public void showBtnAddHouse() {
        btnAddHouse.setVisibility(View.VISIBLE);
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
