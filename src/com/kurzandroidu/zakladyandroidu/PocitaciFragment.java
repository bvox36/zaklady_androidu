package com.kurzandroidu.zakladyandroidu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PocitaciFragment extends Fragment {

    protected int mFragmentNumber;

    public static final PocitaciFragment newInstance(int n) {
        PocitaciFragment fragment = new PocitaciFragment();
        Bundle args = new Bundle(1);
        args.putInt("fragmentNumber", n);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pocitaci, container, false);
        mFragmentNumber = getArguments() != null ? getArguments().getInt("fragmentNumber") : 1;
        ((TextView) view.findViewById(R.id.textViewFragmentNumber)).setText(String.valueOf(mFragmentNumber));
        return view;
    }
}
