package com.example.tuanlv.myweather.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuanlv.myweather.R;
import com.example.tuanlv.myweather.views.MainActivity;

/**
 * Created by Ta Giang on 7/8/2015.
 */
public class Fragment_evalute extends Fragment {
    Button btnSave;
    TextView txtContentSendEmail;
    RadioButton rbnButton1, rbnButton2, rbnButton3, rbnButton4, rbnButton5;
    RadioGroup radioGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.evaluate_software, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        innitView();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtContentSendEmail.getText() == "") {
                    Toast.makeText(getActivity(), "Bạn chưa nhập nội dung", Toast.LENGTH_LONG).show();
                } else {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tagiangkstncnttk57@gmail.com"});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Đánh giá phần mềm dự báo thời tiết của nhóm 02");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Đánh giá hiệu năng:" + getStringRadioButton() + "\n" + "ý kiến đóng góp thêm:" + txtContentSendEmail.getText());
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Choose Email"));
                        getActivity().finish();
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getActivity(), "Bạn chưa đăng nhập EMAIL", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void innitView() {
        btnSave = (Button) getActivity().findViewById(R.id.btnSend);
        txtContentSendEmail = (TextView) getActivity().findViewById(R.id.txtContentSendEmail);
        rbnButton1 = (RadioButton) getActivity().findViewById(R.id.rbnVerryGood);
        rbnButton2 = (RadioButton) getActivity().findViewById(R.id.rbnGood);
        rbnButton3 = (RadioButton) getActivity().findViewById(R.id.rbnNomal);
        rbnButton4 = (RadioButton) getActivity().findViewById(R.id.rbnBad);
        rbnButton5 = (RadioButton) getActivity().findViewById(R.id.rbnVerryBad);
        radioGroup = (RadioGroup) getActivity().findViewById(R.id.rbnGroup);
    }

    private String getStringRadioButton() {
        String content = "";
        int checked = radioGroup.getCheckedRadioButtonId();
        switch (checked) {
            case R.id.rbnVerryGood:
                content = rbnButton1.getText().toString();
                break;
            case R.id.rbnGood:
                content = rbnButton2.getText().toString();
                break;
            case R.id.rbnNomal:
                content = rbnButton3.getText().toString();
                break;
            case R.id.rbnBad:
                content = rbnButton4.getText().toString();
                break;
            case R.id.rbnVerryBad:
                content = rbnButton5.getText().toString();
                break;
            default:
                content = "Không có đánh giá";
        }
        return content;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
