package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class FragmentAlertDialog extends DialogFragment {

    private OnClickListener mOnClickListener;

    public static FragmentAlertDialog newInstance(int title, int message) {
        FragmentAlertDialog frag = new FragmentAlertDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("message", message);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mOnClickListener = (OnClickListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " nutno implementovat DialogInterface.OnClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnClickListener = null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Bundle args = getArguments();
        int title = args.getInt("title");
        int message = args.getInt("message");

        setCancelable(false);

        builder.setTitle(title).setMessage(message).setNeutralButton(R.string.dlg_btn_ok, mOnClickListener);
        AlertDialog dlg = null;
        dlg = builder.setTitle(title).setMessage(message).setNeutralButton(R.string.dlg_btn_ok, mOnClickListener).create();
        dlg.setCanceledOnTouchOutside(false);

        //LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        //View view = layoutInflater.inflate(R.layout.activity_poznamka, null);
        //dlg.setView(view);

        return dlg;
    }
}
