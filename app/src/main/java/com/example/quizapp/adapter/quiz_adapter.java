package com.example.quizapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.activities.R;
import com.example.quizapp.activities.questions_activity;
import com.example.quizapp.models.quiz;
import com.example.quizapp.utils.color_picker;
import com.example.quizapp.utils.icon_picker;

import java.util.ArrayList;

public  class quiz_adapter extends RecyclerView.Adapter<quiz_adapter.ViewHolder> {
    Context context;
    private ArrayList<quiz> quizList=new ArrayList<quiz>();

    public quiz_adapter(Context context, ArrayList<quiz> quizes){
        this.context = context;
        this.quizList = quizes;
    }



    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void onBindViewHolder(@NonNull quiz_adapter.ViewHolder holder, int position) {
        String quiz = quizList.get(position).title;
        holder.quiz_t.setText(quiz);
        color_picker select_color = new color_picker();
        icon_picker select_icon = new icon_picker();
        holder.card_container.setCardBackgroundColor(Color.parseColor(select_color.get_color()));
        holder.quiz_icon.setImageResource(select_icon.get_icon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, quizList.get(position).title, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, questions_activity.class);
                intent.putExtra("Name", quizList.get(position).title);
                context.startActivity(intent);
            }
        });

    }

    public int getItemCount() {
        return quizList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView quiz_t;
        ImageView quiz_icon;
        CardView card_container;

        public ViewHolder(View itemView) {
            super(itemView);
            quiz_t = itemView.findViewById(R.id.quiz_title);
            quiz_icon = itemView.findViewById(R.id.quiz_icon);
            card_container = itemView.findViewById(R.id.card_container);

        }

    }


}
