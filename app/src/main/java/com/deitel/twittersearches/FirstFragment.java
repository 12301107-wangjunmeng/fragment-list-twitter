package com.deitel.twittersearches;

import android.app.Activity;
import android.app.ListFragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;

import com.deitel.twittersearches.R;

import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FirstFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;
    private static final String SEARCHES = "searches";
    private MainActivity mainActivity;
    private ListView view;
    private SharedPreferences savedSearches;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Change Adapter to display your content
        mainActivity = (MainActivity)getActivity();
        setListAdapter(mainActivity.getAdapter());
    }

    @Override
    public void onListItemClick(ListView l,View v,int position,long id) {
        if (mListener != null) {
            String tag = ((TextView)v).getText().toString();
            savedSearches = getActivity().getSharedPreferences(SEARCHES, Context.MODE_PRIVATE);
            String urlString = getString(R.string.searchURL) + savedSearches.getString(tag, "");
            mListener.sendURLToFragment2(urlString);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //MainActivity mainToGetAdapter = new MainActivity();
        View view1 =  inflater.inflate(R.layout.fragment_first, container, false);
        view = (ListView)view1.findViewById(android.R.id.list);
        //view.setOnItemClickListener(mainActivity.getItemClickListener());
        view.setOnItemLongClickListener(mainActivity.getOnItemLongClickListener());
        return view1;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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
        public void sendURLToFragment2(String url);
    }

}
