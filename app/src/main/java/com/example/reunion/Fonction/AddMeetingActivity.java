package com.example.reunion.Fonction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.reunion.R;
import com.example.reunion.model.Meeting;
import com.example.reunion.service.MeetingApiService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;

import androidx.appcompat.app.AppCompatActivity;

public class AddMeetingActivity extends AppCompatActivity {

    private TextInputLayout mNameAdd;
    private TextInputLayout mEmailAdd;
    private TextInputLayout mSubAdd;
    private TextInputLayout mRoomAdd;
    private Button mTimeAdd;
    MeetingApiService mApiService;
    MaterialButton mButton;
    LocalDate dateformatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        mNameAdd = findViewById(R.id.nameLyt);
        mRoomAdd = findViewById(R.id.roomLyt);
        mEmailAdd = findViewById(R.id.mailsLyt);
        mSubAdd = findViewById(R.id.subjectLyt);
        mTimeAdd = findViewById(R.id.timeLyt);
        mButton = findViewById(R.id.create);
        mApiService = DI.getMeetingApiService();

        mTimeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateformatter = LocalDate.of(year, month + 1, dayOfMonth);
                    }
                }, 2022, 02, 06);
                datePickerDialog.show();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meeting meeting = new Meeting(
                        mNameAdd.getEditText().getText().toString(),
                        mRoomAdd.getEditText().getText().toString(),
                        mEmailAdd.getEditText().getText().toString(),
                        dateformatter,
                        mSubAdd.getEditText().getText().toString());
                mApiService.createMeeting(meeting);
                finish();
            }
        });
    }

}