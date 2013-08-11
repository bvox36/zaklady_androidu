package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class PocitaciFragment extends Fragment {

    protected int                     mFragmentNumber;
    protected OnInstanceCountListener mOnInstanceCountListener;

    public static interface OnInstanceCountListener {
        public void onInstanceCount(int instanceNumber);
    }

    public static final PocitaciFragment newInstance(int n) {
        PocitaciFragment fragment = new PocitaciFragment();
        Bundle args = new Bundle(1);
        args.putInt("fragmentNumber", n);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mOnInstanceCountListener = (OnInstanceCountListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " nutno implementovat OnInstanceCountListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnInstanceCountListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pocitaci, container, false);
        mFragmentNumber = getArguments() != null ? getArguments().getInt("fragmentNumber") : 0;
        ((TextView) view.findViewById(R.id.textViewFragmentNumber)).setText(String.valueOf(mFragmentNumber));

        view.findViewById(R.id.buttonSendValue).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                int num = Integer.valueOf(((TextView) view.findViewById(R.id.textViewFragmentNumber)).getText().toString());

                if (mOnInstanceCountListener != null)
                    mOnInstanceCountListener.onInstanceCount(num);
            }

        });
        return view;
    }
}
