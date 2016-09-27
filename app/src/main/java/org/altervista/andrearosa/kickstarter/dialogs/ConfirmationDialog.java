package org.altervista.andrearosa.kickstarter.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by andre on 21/08/16.
 * <p>
 * kickstarter-android.
 */
public class ConfirmationDialog extends DialogFragment {

    private static final String TITLE_KEY = "TitleKey";
    private static final String MESSAGE_KEY = "MessageKey";
    private static final String CONFIRM_KEY = "ConfirmKey";
    private static final String CANCEL_KEY = "CancelKey";

    private DialogListener listener = null;

    public interface DialogListener {
        void onConfirm();

        void onCancel();
    }

    public static ConfirmationDialog newInstance(
            String title,
            String message,
            String confirm,
            String cancel,
            DialogListener listener) {

        ConfirmationDialog dialog = new ConfirmationDialog();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        args.putString(MESSAGE_KEY, message);
        args.putString(CONFIRM_KEY, confirm);
        args.putString(CANCEL_KEY, cancel);
        dialog.setArguments(args);
        dialog.listener = listener;

        return dialog;
    }

    public ConfirmationDialog() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (getArguments() != null) {
            String title = getArguments().getString(TITLE_KEY, null);
            String message = getArguments().getString(MESSAGE_KEY, null);
            String confirm = getArguments().getString(CONFIRM_KEY, null);
            String cancel = getArguments().getString(CANCEL_KEY, null);
            if (title != null && message != null && confirm != null && cancel != null) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(title);
                builder.setMessage(message);
                builder.setPositiveButton(confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (listener != null) {
                            listener.onConfirm();
                        }
                    }
                });
                builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (listener != null) {
                            listener.onCancel();
                        }
                    }
                });
                return builder.create();

            } else {
                throw new IllegalStateException("You need to create the dialog using newInstance method!");
            }
        } else {
            throw new IllegalStateException("You need to create the dialog using newInstance method!");
        }
    }
}
