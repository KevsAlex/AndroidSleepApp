package deco.sleepp.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import deco.sleepp.Models.Paciente

import deco.sleepp.R
import deco.sleepp.Utils.Utils
import deco.sleepp.WebService.AppWebService
import deco.sleepp.WebService.ResponseWService
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_registro_paciente.*
import kotlinx.android.synthetic.main.fragment_registro_paciente.view.*


/**
 * A simple [Fragment] subclass.
 */
class RegistroPaciente : Fragment() , View.OnClickListener , ResponseWService{

    lateinit var appService : AppWebService
    var paciente = Paciente()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_registro_paciente, container, false)
        view.btRegistroPaciente.setOnClickListener(this)
        appService = AppWebService(activity)
        appService.response = this

        return view
    }

    override fun onClick(view: View?) {
        when (view?.id){
            R.id.btRegistroPaciente -> {
                paciente.correo = edCorreoRegistro.text.toString()
                paciente.nombre = edNombrePaciente.text.toString()
                paciente.password = edPassRegistro.text.toString()
                if (paciente.correo!!.isBlank() || paciente.nombre!!.isBlank() || paciente.password!!.isBlank() ){
                    Toast.makeText(activity, "Uno de los campos está vacío", Toast.LENGTH_SHORT).show()
                    return
                }
                appService.registro(paciente)


            }

        }
    }

    override fun didFInish(response: Any?) {
        paciente = response as Paciente
        Utils.setIdpaciente(activity,paciente.idPaciente)
        Toast.makeText(activity, paciente.idPaciente, Toast.LENGTH_SHORT).show()
        Utils.setIdpaciente(activity,paciente.idPaciente)
    }

    override fun didFinishWithError(code: Int) {
        Toast.makeText(activity, "Hubo un error de validación", Toast.LENGTH_SHORT).show()
    }
}// Required empty public constructor
