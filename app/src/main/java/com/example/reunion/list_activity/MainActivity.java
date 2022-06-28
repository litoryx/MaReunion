package com.example.reunion.list_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.reunion.Fonction.AddMeetingActivity;
import com.example.reunion.Fonction.DI;
import com.example.reunion.R;
import com.example.reunion.model.Meeting;
import com.example.reunion.service.MeetingApiService;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MainActivity extends AppCompatActivity implements MyMeetingRecyclerViewAdapter.DelMeetingListener {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    List<Meeting> mMeeting;
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
        initList(0);

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
                initList(1);
                return true;
            case R.id.filter2:
                String[] items = new String[] {"Réunion 1", "Réunion 2", "Réunion 3"};

                new MaterialAlertDialogBuilder(this)
                        .setTitle("Rooms").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String room = items[which];
                        mApiService.getMeetingbyRoom(room);
                    }
                }).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initList(int filter) {
        mMeeting = mApiService.getMeeting();
        if(filter == 0){}
        if(filter == 1){ Collections.sort(mMeeting, Meeting.ComparatorDate);}
        else if(filter == 2){Collections.sort(mMeeting, Meeting.ComparatorPlace);}
        mMyMeetingRecyclerViewAdapter = new MyMeetingRecyclerViewAdapter(mMeeting, this);
        mRecyclerView.setAdapter(mMyMeetingRecyclerViewAdapter);
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