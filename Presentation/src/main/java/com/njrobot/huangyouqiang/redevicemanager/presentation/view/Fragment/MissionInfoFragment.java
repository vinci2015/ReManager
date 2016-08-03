package com.njrobot.huangyouqiang.redevicemanager.presentation.view.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component.MissionInfoComponent;
import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.model.Mission;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.MissionInfoView;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.presenter.MissionInfoPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MissionInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MissionInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MissionInfoFragment extends BaseFragment implements MissionInfoView{

    @Inject
    MissionInfoPresenter missionInfoPresenter;
    @BindView(R.id.root_view)FrameLayout rootView;
    @BindView(R.id.tv)
    TextView tvContent;
    @BindView(R.id.rl_progress)
    RelativeLayout rlProgress;
    @BindView(R.id.rl_retry) RelativeLayout rlRetry;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MissionInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MissionInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MissionInfoFragment newInstance(String param1, String param2) {
        MissionInfoFragment fragment = new MissionInfoFragment();
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
        //将依赖注入
        getComponent(MissionInfoComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mission_info, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        missionInfoPresenter.setView(this);
        missionInfoPresenter.init();
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
    public void onDestroy() {
        super.onDestroy();
        missionInfoPresenter.destroy();
    }

    @Override
    public void renderMission(Mission mission) {
        tvContent.setText(mission.toString());
    }

    @Override
    public void showLoading() {
        this.rlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.rlProgress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        this.rlRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rlRetry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(rootView,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @OnClick(R.id.rl_retry)
    void retry(){

    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
