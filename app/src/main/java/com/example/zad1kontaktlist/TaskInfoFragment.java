package com.example.zad1kontaktlist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.zad1kontaktlist.tasks.TaskListContent;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskInfoFragment extends Fragment {

    public TaskInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_info, container, false);
    }

    @SuppressLint("SetTextI18n")
    void displayTask(TaskListContent.Task task) {
        FragmentActivity activity = getActivity();

        TextView taskInfoName = activity.findViewById(R.id.taskInfoName);
        TextView taskInfoBirthday = activity.findViewById(R.id.taskInfoBirthday);
        TextView taskInfoNumber = activity.findViewById(R.id.taskInfoNumber);
        ImageView taskInfoImage = activity.findViewById(R.id.taskInfoImage);

        taskInfoName.setText(task.name + " " + task.surname);
        taskInfoBirthday.setText(task.birthday);
        taskInfoNumber.setText(task.phoneNumber);
        if(task.picture != null && !task.picture.isEmpty()) {
                Drawable taskDrawable;

                switch(task.picture) {
                    case "0":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case "1":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case "2":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case "3":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case "4":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case "5":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_10);
                        break;
                    case "6":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_12);
                        break;
                    case "7":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_13);
                        break;
                    case "8":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_14);
                        break;
                    case "9":
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_15);
                        break;
                    default:
                        taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);
                }
                taskInfoImage.setImageDrawable(taskDrawable);
        } else {
            taskInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.avatar_1));
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        if(intent != null) {
            TaskListContent.Task receivedTask = intent.getParcelableExtra(MainActivity.taskExtra);
            if(receivedTask != null) {
                displayTask(receivedTask);
            }
        }
    }
}
