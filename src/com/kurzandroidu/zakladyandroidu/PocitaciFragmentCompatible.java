package com.kurzandroidu.zakladyandroidu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PocitaciFragmentCompatible extends Fragment {

    protected int mFragmentNumber;

    public static final PocitaciFragmentCompatible newInstance(int n) {
        PocitaciFragmentCompatible fragment = new PocitaciFragmentCompatible();
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
