package com.example.zad1kontaktlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.zad1kontaktlist.tasks.TaskListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements
        TaskFragment.OnListFragmentInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener,
        CallDialog.OnCallDialogInteractionListener {

    public static final String taskExtra = "taskExtra";
    public int currentItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TaskFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.taskFragment))).notifyDataChange();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), TaskInfoCreator.class));
            }
        });
    }


    private void startSecondActivity(TaskListContent.Task task, int position) {
        Intent intent = new Intent(this, TaskInfoActivity.class);
        intent.putExtra(taskExtra, task);
        startActivity(intent);
    }


    private void displayTaskInFragment(TaskListContent.Task task) {
        TaskInfoFragment taskInfoFragment = ((TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(taskInfoFragment != null) {
            taskInfoFragment.displayTask(task);
        }
    }

    @Override
    public void onListFragmentClickInteraction(TaskListContent.Task task, int position) {
        Toast.makeText(this, getString(R.string.item_selected_contact) + " " + task.name, Toast.LENGTH_SHORT).show();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            displayTaskInFragment(task);
        }else {
            startSecondActivity(task, position);
        }
    }

    private void showCallDialog(){
        CallDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.call_dialog_tag));
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        showCallDialog();
        currentItemPosition = position;
    }

    @Override
    public void onImageButtonClickInteraction(int position) {
        Toast.makeText(this, getString(R.string.delete_dialog_tag), Toast.LENGTH_SHORT).show();
        showDeleteDialog();
        currentItemPosition = position;
    }

    @Override
    public void onCallDialogPositiveClick(DialogFragment dialog) {
    }

    @Override
    public void onCallDialogNegativeClick(DialogFragment dialog) {
    }

    public void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.delete_dialog_tag));
    }

    @Override
    public void onDeleteDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != -1 && currentItemPosition < TaskListContent.ITEMS.size()) {
            TaskListContent.removeItem(currentItemPosition);
            ((TaskFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.taskFragment))).notifyDataChange();
        }
    }

    @Override
    public void onDeleteDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.addButton);
        if(v != null) {
            Snackbar.make(v, getString(R.string.delete_cancel_msg), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry_msg), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDeleteDialog();
                        }
                    }).show();
        }
    }
}
