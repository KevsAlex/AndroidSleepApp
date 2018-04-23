package deco.sleepp.Dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import deco.sleepp.R;

/**
 * Created by admin on 21/04/18.
 */

public class MyDialog extends DialogFragment implements View.OnClickListener{

    Button mBAceptar;
    private EditText mEditText ;

    public interface DialogDelegate {
        void asignarDoctor(String claveDoctor);
    }

    DialogDelegate mDelegate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_clave_doctor,null);
        mBAceptar = view.findViewById(R.id.bAceptarDialog);
        mEditText = view.findViewById(R.id.editClaveDoctorDialog);
        mBAceptar.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.bAceptarDialog:
                mDelegate.asignarDoctor(mEditText.getText().toString());
                break;
        }
    }



    public DialogDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(DialogDelegate delegate) {
        mDelegate = delegate;
    }
}
