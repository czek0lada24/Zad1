package com.example.zad1kontaktlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zad1kontaktlist.tasks.TaskListContent;

import java.util.List;

public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<TaskListContent.Task> mValues;
    private final TaskFragment.OnListFragmentInteractionListener mListener;

    MyTaskRecyclerViewAdapter(List<TaskListContent.Task> items, TaskFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        TaskListContent.Task task = mValues.get(position);
        holder.mItem = task;
        holder.mContentView.setText(task.name + " " + task.surname);

        final String picture = task.picture;
        Context context = holder.mView.getContext();
        if(picture != null && !picture.isEmpty()) {
                Drawable taskDrawable;

                switch(picture) {
                    case "0":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case "1":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case "2":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case "3":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case "4":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case "5":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_10);
                        break;
                    case "6":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_12);
                        break;
                    case "7":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_13);
                        break;
                    case "8":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_14);
                        break;
                    case "9":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_15);
                        break;
                    default:
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                }
                holder.mItemImageView.setImageDrawable(taskDrawable);
        } else{
            holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_1));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

       holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener){
                    mListener.onImageButtonClickInteraction(position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.onListFragmentLongClickInteraction(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mItemImageView;
        final TextView mContentView;
        TaskListContent.Task mItem;
        final ImageButton mImageButton;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView = view.findViewById(R.id.item_image);
            mContentView = view.findViewById(R.id.content);
            mImageButton = view.findViewById(R.id.delete_contact);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
