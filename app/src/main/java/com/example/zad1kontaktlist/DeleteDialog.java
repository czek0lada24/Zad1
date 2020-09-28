package com.example.zad1kontaktlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteDialog extends DialogFragment {

    private OnDeleteDialogInteractionListener mListener;

    public DeleteDialog() {
        // Required empty public constructor
    }

    static DeleteDialog newInstance(){
        return new DeleteDialog();
    }

    public interface OnDeleteDialogInteractionListener{
        void onDeleteDialogPositiveClick(DialogFragment dialog);
        void onDeleteDialogNegativeClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.delete_question));
        builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDeleteDialogPositiveClick(DeleteDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDeleteDialogNegativeClick(DeleteDialog.this);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnDeleteDialogInteractionListener) {
            mListener = (OnDeleteDialogInteractionListener) context;
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

