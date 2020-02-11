package com.example.android.fragmentexample;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {
    private static final int YES = 0;   //YES berindex 0, karena urutan radio button pada xml
    private static final int NO = 1;    //NO berindex 1, karena urutan radio button pada xml
    private static final String CHOICE = "choice";
    private int mRadioButtonChoice2 = 2;    //untuk pilihan lainnya (selain YES dan NO)

    //agar method bisa diakses, inisialisasikan
    private OnFragmentInteractionListener mListener;

    //dipanggil di activity untuk menampilkan data choice (YES/NO)
    public static SimpleFragment newInstances(int choice){
        SimpleFragment fragment = new SimpleFragment();
        //Bundle menerima data choice
        Bundle args = new Bundle();
        args.putInt(CHOICE, choice);
        fragment.setArguments(args);
        return fragment;
    }

    public SimpleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Supaya bisa mengakses view pada rootView
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        //untuk mengakses radioGroup
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        //mengakses textView
        final TextView textView = rootView.findViewById(R.id.fragment_header);
        //untuk menerima argumen
        if (getArguments().containsKey(CHOICE)){
            mRadioButtonChoice2 = getArguments().getInt(CHOICE);
            if (mRadioButtonChoice2 != 2){
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice2).getId());
            }
        }
        //buat listener radioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //i adalah id radioButton yang di klik
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                switch (index){
                    case YES:
                        textView.setText("Article : YES");
                        mRadioButtonChoice2 = 0;
                        mListener.onRadioButtonChoice(mRadioButtonChoice2);
                        break;
                    case NO:
                        textView.setText("Article : NO");
                        mRadioButtonChoice2 = 1;
                        mListener.onRadioButtonChoice(mRadioButtonChoice2);
                        break;
                    default:
                        mRadioButtonChoice2 = 2;
                        mListener.onRadioButtonChoice(mRadioButtonChoice2);
                        break;
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractionListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //untuk mengirim data dari fragment ke activity, method harus di implement di activity
    public interface OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
    }
}
