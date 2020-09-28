package com.example.zad1kontaktlist;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallDialog extends DialogFragment {
    private OnCallDialogInteractionListener mListener;

    public CallDialog() {
        // Required empty public constructor
    }

    static CallDialog newInstance(){
        return new CallDialog();
    }

    public interface OnCallDialogInteractionListener{
        void onCallDialogPositiveClick(DialogFragment dialog);
        void onCallDialogNegativeClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setMessage(getString(R.string.call));

        builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onCallDialogPositiveClick(CallDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onCallDialogNegativeClick(CallDialog.this);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCallDialogInteractionListener) {
            mListener = (OnCallDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDeleteDialogInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
