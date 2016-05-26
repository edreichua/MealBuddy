package com.example.mealbuddy.mealbuddy;

/**
 * Created by edreichua on 4/22/16.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class MealBuddyDialogFragment extends DialogFragment {

    private static final String DIALOG_ID_KEY = "which_dialog";

    // List of dialog IDs
    public static final int PHOTO_PICKER_ID = 0;


    // List of options for photo picker dialog
    public static final int SELECT_FROM_CAMERA = 0;
    public static final int SELECT_FROM_GALLERY = 1;

    /**
     * Create a new instance of a dialog fragment
     * @param dialog_id
     * @return
     */
    public static MealBuddyDialogFragment newInstance(int dialog_id) {

        MealBuddyDialogFragment dialogFrag = new MealBuddyDialogFragment();
        Bundle inputBundle = new Bundle();
        inputBundle.putInt(DIALOG_ID_KEY, dialog_id);
        dialogFrag.setArguments(inputBundle);

        return dialogFrag;
    }

    /**
     * Create dialog fragment
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Activity parent = getActivity();
        int dialog_id = getArguments().getInt(DIALOG_ID_KEY);

        // Create dialog builder to choose between camera and gallery
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(parent);

        // Create OnClickListener based on the type of dialog fragment
        switch (dialog_id) {

            case PHOTO_PICKER_ID:
                buildPhotoPickerDialog(dialogBuilder,parent);
                return dialogBuilder.create();


            default:
                return null;
        }
    }


    /////////////////////// Helper Functions for building dialogs ///////////////////////

    /**
     * build the photo picker dialog
     * @param dialogBuilder
     * @param parent
     */
    private void buildPhotoPickerDialog(AlertDialog.Builder dialogBuilder, final Activity parent){
        dialogBuilder.setTitle(R.string.ui_photo_picker);

        // Create listener
        DialogInterface.OnClickListener photoPickerlistener =
                new DialogInterface.OnClickListener() {

                    @Override // Override the onClick method
                    public void onClick(DialogInterface dialog, int item) {
                        ((UserProfileActivity) parent).selectPhotoPickerItem(item);
                    }
                };

        // Create the dialog
        dialogBuilder.setItems(R.array.ui_photo_picker_list, photoPickerlistener);
    }

}