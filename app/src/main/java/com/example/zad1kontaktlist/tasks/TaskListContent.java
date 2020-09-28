package com.example.zad1kontaktlist.tasks;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskListContent {

    /**
     * An array of items.
     */
    public static final List<Task> ITEMS = new ArrayList<Task>();

    /**
     * A map of items, by ID.
     */
    public static final Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

    static {
        for (int i = 1; i < 2; i++) {
            addItem(createTask(i));
        }
    }

    private static Task createTask(int position) {
        return new Task(String.valueOf(position), "Name", "Surname", "DD/MM/RRRR", "Phone number", "1");
    }

    public static void addItem(Task item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A task representing a piece of content.
     */
    public static class Task implements Parcelable {
        public final String id;
        public final String name;
        public final String surname;
        public final String birthday;
        public final String phoneNumber;
        public final String picture;

        public Task(String id, String name, String surname, String birthday, String phoneNumber, String picture) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.birthday = birthday;
            this.phoneNumber = phoneNumber;
            this.picture = picture;
        }

        Task(Parcel in) {
            id = in.readString();
            name = in.readString();
            surname = in.readString();
            birthday = in.readString();
            phoneNumber = in.readString();
            picture = in.readString();
        }

        public static final Creator<Task> CREATOR = new Creator<Task>() {
            @Override
            public Task createFromParcel(Parcel in) {
                return new Task(in);
            }

            @Override
            public Task[] newArray(int size) {
                return new Task[size];
            }
        };

        @NonNull
        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(surname);
            dest.writeString(birthday);
            dest.writeString(phoneNumber);
            dest.writeString(picture);
        }
    }

    public static void removeItem(int position){
        String itemId = ITEMS.get(position).id;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
    }

}
