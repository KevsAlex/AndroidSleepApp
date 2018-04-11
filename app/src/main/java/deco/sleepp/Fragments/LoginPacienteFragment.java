package deco.sleepp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import deco.sleepp.MainActivity;
import deco.sleepp.MenuPaciente;
import deco.sleepp.Models.Paciente;
import deco.sleepp.R;
import deco.sleepp.WebService.AppWebService;
import deco.sleepp.WebService.ResponseWService;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginPacienteFragment extends Fragment implements View.OnClickListener,ResponseWService{

    private EditText mEdNombre;
    private EditText mEdPassword;
    private Button mButtonLogin;
    private Paciente mPaciente;
    private AppWebService mAppWebService;

    public LoginPacienteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mPaciente = new Paciente();
        mEdNombre = view.findViewById(R.id.editCorreo);
        mEdPassword = view.findViewById(R.id.editPassword);
        mAppWebService = new AppWebService(getActivity());
        mAppWebService.setAppWebService(this);
        mButtonLogin = view.findViewById(R.id.bLogin);
        mButtonLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.bLogin:
                if (validateUser(mPaciente)){
                    //Intent intent = new Intent(getActivity(), MainActivity.class);
                    //startActivity(intent);
                    mPaciente.setCorreo(mEdNombre.getText().toString());
                    mPaciente.setPassword(mEdPassword.getText().toString());
                    mAppWebService.loging(mPaciente);
                }

                break;

        }
    }

    public boolean validateUser(Paciente paciente){
        String correo = mEdNombre.getText().toString();
        String password = mEdPassword.getText().toString();
        paciente.setNombre(correo);
        paciente.setPassword(password);
        if (paciente.getPassword().isEmpty() || paciente.getNombre().isEmpty()){
            return false;
        }
        return true;

    }

    @Override
    public void didFInish(Object response) {
        mPaciente = (Paciente) response;
        Intent intent = new Intent(getActivity(), MenuPaciente.class);
        startActivity(intent);


    }
}
