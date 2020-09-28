package com.example.zad1kontaktlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zad1kontaktlist.tasks.TaskListContent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class TaskInfoCreator extends AppCompatActivity {

    final static String DATE_FORMAT = "dd/mm/yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info_creator);
    }

    public void addClick(View view) {
        EditText taskNameEditTxt = findViewById(R.id.name);
        EditText taskSurnameEditTxt = findViewById(R.id.surname);
        EditText taskBirthdayEditTxt = findViewById(R.id.birthday);
        EditText taskNumberEditTxt = findViewById(R.id.phoneNumber);

        Random rand = new Random();
        int upperbound = 10;
        int random = rand.nextInt(upperbound);
        String taskPic = Integer.toString(random);

        String taskName = taskNameEditTxt.getText().toString();
        String taskSurname = taskSurnameEditTxt.getText().toString();
        String taskBirthday = taskBirthdayEditTxt.getText().toString();
        String taskNumber = taskNumberEditTxt.getText().toString();

        if (isDateValid(taskBirthday) && isNumberValid(taskNumber)) {

                if (taskName.isEmpty() && taskSurname.isEmpty() && taskBirthday.isEmpty() && taskNumber.isEmpty()) {
                    TaskListContent.addItem(new TaskListContent.Task("Task. " + TaskListContent.ITEMS.size() + 1,
                            getString(R.string.default_name),
                            getString(R.string.default_surname),
                            getString(R.string.default_birthday),
                            getString(R.string.default_number),
                            taskPic));

                } else {
                    if (taskName.isEmpty())
                        taskName = getString(R.string.default_name);
                    if (taskSurname.isEmpty())
                        taskSurname = getString(R.string.default_surname);
                    if (taskBirthday.isEmpty())
                        taskBirthday = getString(R.string.default_birthday);
                    if (taskNumber.isEmpty())
                        taskNumber = getString(R.string.default_number);
                    TaskListContent.addItem(new TaskListContent.Task("Task. " + TaskListContent.ITEMS.size() + 1,
                            taskName,
                            taskSurname,
                            "Birthday: " + taskBirthday,
                            "Phone number: " + taskNumber,
                            taskPic));
                }

                Intent contactData = new Intent(this, MainActivity.class);
                startActivity(contactData);
        }
    }

    public boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            Toast.makeText(this, getString(R.string.wrong_date), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isNumberValid(String phoneNumber) {
        String regex = "\\d+";
        if(phoneNumber.matches(regex) && (phoneNumber.length() == 9)){
            return true;
        }

        else {
            Toast.makeText(this, getString(R.string.wrong_number), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
