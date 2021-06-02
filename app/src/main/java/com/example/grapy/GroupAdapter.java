package com.example.grapy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {

    ArrayList<GTList> arrGT;
    Context mctx;
    RecyclerViewClickListener listener;

    public GroupAdapter(ArrayList<GTList> arrGT, Context mctx) {
        this.arrGT = arrGT;
        this.mctx = mctx;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mctx).inflate(R.layout.activity_list_view, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull GroupAdapter.MyViewHolder holder, int position) {
         GTList gtList = arrGT.get(position);

         holder.group_name.setText(gtList.getName());
         holder.personality.setText(gtList.getR_personality());
         holder.total_member.setText(gtList.getNum_member() + " Member");


    }

    @Override
    public int getItemCount() {
        return arrGT.size();
    }


    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView group_name, personality, total_member;
        Button btnJoin;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            try {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setServerURL(new URL(""))
                        .setWelcomePageEnabled(false)
                        .build();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            group_name = itemView.findViewById(R.id.group_name);
            personality = itemView.findViewById(R.id.personality);
            total_member = itemView.findViewById(R.id.total_member);
            btnJoin = itemView.findViewById(R.id.btnJoin);
            btnJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                            .setRoom("Group Theraphy")
                            .build();
                    JitsiMeetActivity.launch(mctx, options);
                }
            });


        }
    }

}
