package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class PoznamkaActivity extends Activity {

    private EditText mEditTextNazev;
    private EditText mEditTextObsah;
    private Button   mButtonUlozit;
    private Button   mButtonSmazat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEditTextNazev = new EditText(this);
        mEditTextNazev.setHint(R.string.label_nazev);
        mEditTextNazev.requestFocus();
        mEditTextNazev.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        mEditTextObsah = new EditText(this);
        mEditTextObsah.setHint(R.string.label_obsah);
        LinearLayout.LayoutParams paramsNazev = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        paramsNazev.weight = 1;
        mEditTextObsah.setLayoutParams(paramsNazev);
        mEditTextObsah.setGravity(Gravity.TOP);
        mEditTextObsah.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        mButtonUlozit = new Button(this);
        mButtonUlozit.setText(R.string.label_ulozit);
        LinearLayout.LayoutParams paramUlozit = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramUlozit.gravity = Gravity.RIGHT;
        mButtonUlozit.setLayoutParams(paramUlozit);

        mButtonSmazat = new Button(this);
        mButtonSmazat.setText(R.string.label_smazat);
        LinearLayout.LayoutParams paramSmazat = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramSmazat.gravity = Gravity.LEFT;
        mButtonSmazat.setLayoutParams(paramSmazat);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        linearLayout.setPadding(10, 10, 10, 10);
        linearLayout.addView(mEditTextNazev);
        linearLayout.addView(mEditTextObsah);
        linearLayout.addView(mButtonSmazat);
        linearLayout.addView(mButtonUlozit);

        setContentView(linearLayout);
    }
}
