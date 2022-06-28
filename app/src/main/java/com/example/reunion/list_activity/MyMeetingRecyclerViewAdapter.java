package com.example.reunion.list_activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.reunion.Fonction.DI;
import com.example.reunion.R;
import com.example.reunion.model.Meeting;
import com.example.reunion.service.MeetingApiService;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    interface DelMeetingListener {

        void onDeleteClick(Meeting meeting);

    }

    private List<Meeting> mMeetings;
    public MeetingApiService mApiService;
    DelMeetingListener mDelMeetingListener;

    public MyMeetingRecyclerViewAdapter(List<Meeting> meetings, DelMeetingListener viewada) {
        mMeetings = meetings;
        mDelMeetingListener = viewada;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Meeting meeting = mMeetings.get(position);
            holder.mMeetingName.setText(meeting.getName());
            holder.mMeetingTime.setText(meeting.getTime());
            holder.mMeetingRoom.setText(meeting.getRoom());

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelMeetingListener.onDeleteClick(meeting);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mMeetingTime;
        public TextView mMeetingName;
        public TextView mMeetingRoom;
        public TextView mMeetingEmails;
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            mMeetingName = view.findViewById(R.id.item_list_name);
            mMeetingTime = view.findViewById(R.id.item_list_time);
            mMeetingRoom = view.findViewById(R.id.item_list_room);
            mMeetingEmails = view.findViewById(R.id.item_list_mail);
            mDeleteButton = view.findViewById(R.id.item_list_delete_button);

        }
    }
}
