package com.gj.administrator.gjerp.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.RecyclerAdapter;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.domain.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Room fragment
 * Created by guojun on 2015/12/09
 */
public class RoomDisplayOrderedFragment extends BaseFragment {

    // TODO: Rename and change types of parameters

    private static Room[] mRoomArray;
    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private static Context context;

    private OnFragmentInteractionListener mListener;

    public RoomDisplayOrderedFragment() {
        // Required empty public constructor
    }

    public static RoomDisplayOrderedFragment getInstance(Room[] roomArray, Context context) {
        RoomDisplayOrderedFragment fragment = new RoomDisplayOrderedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        mRoomArray = roomArray;
        RoomDisplayOrderedFragment.context = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_room_display_ordered, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        mRecyclerView = (RecyclerView) (activity.findViewById(R.id.room_display_ordered_recyclerView));
        mRecyclerView.setHasFixedSize(true);
        List<RecyclerAdapter.ListData> rmIdStrings = new ArrayList<>();

        for (Room room: mRoomArray) {
            rmIdStrings.add(new RecyclerAdapter.ListData(room.getRmId(),room.getRmId(),null));
        }

        mAdapter = new RecyclerAdapter(
                context,
                R.layout.grid_items,
                rmIdStrings,
                RecyclerAdapter.DRAWABLE_TYPE.SAMPLE_RECT,
                false
        );
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(),4));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
