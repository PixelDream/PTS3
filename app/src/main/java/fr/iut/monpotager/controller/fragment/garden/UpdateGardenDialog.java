package fr.iut.monpotager.controller.fragment.garden;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.iut.monpotager.R;
import fr.iut.monpotager.controller.fragment.plant.DateInputMask;
import fr.iut.monpotager.controller.utils.Callback;
import fr.iut.monpotager.manager.GardenManager;
import fr.iut.monpotager.model.Garden;

public class UpdateGardenDialog extends DialogFragment implements Validator.ValidationListener {

    private static final String TAG = "UpdateGardenDialog";
    final private DateFormat dateFormat;

    private final GardenManager gardenManager;
    Garden garden;
    private Validator validator;
    private boolean isValid = false;
    @NotEmpty
    private EditText choiceDate;
    @NotEmpty
    @Min(value = 1)
    private EditText choiceQuantity;
    private Button updateGarden;
    private Spinner spinnerUnit;


    public UpdateGardenDialog() {
        gardenManager = GardenManager.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        validator = new Validator(this);
        validator.setValidationListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_update_garden, null);

        choiceDate = view.findViewById(R.id.choiceDate);
        choiceDate.setText(dateFormat.format(new Date()));
        new DateInputMask(choiceDate);

        choiceQuantity = view.findViewById(R.id.choiceQuantity);

        spinnerUnit = view.findViewById(R.id.choiceUnit);
        ArrayAdapter<String> spinnerUnitAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, getResources().getStringArray(R.array.unit_array));
        spinnerUnit.setAdapter(spinnerUnitAdapter);


        garden = (Garden) getArguments().getSerializable("garden");
        updateGarden = view.findViewById(R.id.updateGarden);
        updateGarden.setOnClickListener(v -> {
            validator.validate();
            if (isValid)
                setUpdateGarden(requireActivity().findViewById(R.id.fragment_plant));
        });

        choiceDate.setText(dateFormat.format(garden.getDate()));
        choiceQuantity.setText(String.valueOf(garden.getQuantity()));
        spinnerUnit.setSelection(spinnerUnitAdapter.getPosition(garden.getUnit()));


        builder.setView(view);

        Dialog dialog = builder.create();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        return dialog;
    }

    private void setUpdateGarden(View v) {
        try {
            garden.setQuantity(Integer.parseInt(choiceQuantity.getText().toString()));
            garden.setDate(dateFormat.parse(choiceDate.getText().toString()));
            garden.setUnit(spinnerUnit.getSelectedItem().toString());
            gardenManager.updateVegetableFromGarden(garden.getId(), garden, new Callback() {
                @Override
                public void onSuccessResult(Object result) {
                    Snackbar success = Snackbar.make(v, "potager modifié !", Snackbar.LENGTH_SHORT);
                    success.show();
                }

                @Override
                public void onErrorResult(Exception e) {
                    Snackbar success = Snackbar.make(v, "erreur", Snackbar.LENGTH_SHORT);
                    success.show();
                }
            });

            this.getDialog().cancel();
        } catch (ParseException e) {
            Log.d(TAG, "Error during parsing date");
        }
    }

    @Override
    public void onValidationSucceeded() {
        isValid = true;
    }


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        isValid = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
