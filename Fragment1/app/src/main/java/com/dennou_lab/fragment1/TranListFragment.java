package com.dennou_lab.fragment1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;


import com.dennou_lab.fragment1.data.TranListData;
import com.dennou_lab.fragment1.data.TranContent;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class TranListFragment extends ListFragment {


    private OnFragmentInteractionListener mListener;

    private TranListData mTranListData;

    // TODO: Rename and change types of parameters
    public static TranListFragment newInstance() {
        TranListFragment fragment = new TranListFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TranListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // アクティビティを取得
        MainActivity mainActivity = (MainActivity)activity;

        // TODO: Change Adapter to display your content
        try {
            TranListData tranListDatat = new TranListData(activity, android.R.layout.simple_list_item_1, mainActivity.getTranContent().ITEMS);
            tranListDatat.SetupContent();

            // setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(
            /*
            setListAdapter(new ArrayAdapter<TranContent.TranItem>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    tranContent.ITEMS));
            */
            setListAdapter(tranListDatat);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(TranContent.ITEMS.get(position).objectId);
        }
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
        public void onFragmentInteraction(String id);
    }

}

