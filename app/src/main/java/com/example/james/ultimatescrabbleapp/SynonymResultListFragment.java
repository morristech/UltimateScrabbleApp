package com.example.james.ultimatescrabbleapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by james on 2/02/2017.
 */

public class SynonymResultListFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView listResults;
    private ListViewAdapter adapter;
    private TextView textViewNumResults;
    ArrayList<String> synonyms;

    public SynonymResultListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SynonymResultListFragment newInstance(String param1, String param2) {
        SynonymResultListFragment fragment = new SynonymResultListFragment();
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
        View view = inflater.inflate(R.layout.fragment_synonym_result_list, container, false);

        Bundle bundle = getArguments();
        synonyms = bundle.getStringArrayList("Synonyms");

        adapter = new ListViewAdapter(getActivity(), synonyms, R.layout.row_result_list);
        listResults = (ListView) view.findViewById(R.id.listViewResults);
        listResults.setAdapter(adapter);

        textViewNumResults = (TextView) view.findViewById(R.id.textViewNumResults);
        textViewNumResults.setText("Found " + synonyms.size() + " results");

        final EditText editTextSearch = (EditText) view.findViewById(R.id.editTextSearch);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = editTextSearch.getText().toString();
                ArrayList<String> results = new ArrayList<>();

                for(String word : synonyms){
                    if(word.startsWith(search)){
                        results.add(word);
                    }
                }

                adapter = new ListViewAdapter(getActivity(), results, R.layout.row_result_list);
                listResults.setAdapter(adapter);
                textViewNumResults.setText("Found " + listResults.getCount() + " results");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
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
