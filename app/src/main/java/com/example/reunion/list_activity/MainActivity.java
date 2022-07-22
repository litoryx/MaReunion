package com.example.reunion.list_activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.example.reunion.Fonction.AddMeetingActivity;
import com.example.reunion.Fonction.DI;
import com.example.reunion.R;
import com.example.reunion.model.Meeting;
import com.example.reunion.service.MeetingApiService;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MyMeetingRecyclerViewAdapter.DelMeetingListener {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    MeetingApiService mApiService;
    FloatingActionButton mAddButton;
    MyMeetingRecyclerViewAdapter mMyMeetingRecyclerViewAdapter;
    View mButtonFilter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = DI.getMeetingApiService();
        mToolbar = findViewById(R.id.toolbar);
        mAddButton = findViewById(R.id.add_meeting);
        mButtonFilter1 = findViewById(R.id.filter1);

        setSupportActionBar(mToolbar);
        mRecyclerView = findViewById(R.id.list_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        showAllMeetings();

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivity(activityIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.filter1:
                        DatePickerDialog datePickerDialog = new DatePickerDialog(this
                                , new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                LocalDate dateformatter = LocalDate.of(year,month+1,dayOfMonth);
                                List<Meeting> mMeeting = mApiService.getMeetingbyDate(dateformatter);
                                initList(mMeeting);
                            }
                        },2022,02,06);
                        datePickerDialog.show();
                return true;
            case R.id.filter2:
                String[] items = new String[] {"Réunion 1", "Réunion 2", "Réunion 8"};

                new MaterialAlertDialogBuilder(this)
                        .setTitle("Rooms").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String room = items[which];
                        List<Meeting> mMeeting = mApiService.getMeetingbyRoom(room);
                        initList(mMeeting);
                    }
                }).show();
                return true;
            case R.id.filter3:
                showAllMeetings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void initList(@NonNull List<Meeting> mMeeting) {

        mRecyclerView.setContentDescription("listmymeeting");
        mMyMeetingRecyclerViewAdapter = new MyMeetingRecyclerViewAdapter(mMeeting, this);
        mRecyclerView.setAdapter(mMyMeetingRecyclerViewAdapter);
    }

    private void showAllMeetings(){

        List<Meeting> mMeeting = mApiService.getMeeting();
        initList(mMeeting);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyMeetingRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteClick(Meeting meeting) {
        mApiService.deleteMeeting(meeting);
        mMyMeetingRecyclerViewAdapter.notifyDataSetChanged();
    }
}