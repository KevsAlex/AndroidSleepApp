package deco.sleepp.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import deco.sleepp.MenuDoctorActivity
import deco.sleepp.Models.Doctor

import deco.sleepp.R
import deco.sleepp.Utils.Utils
import deco.sleepp.WebService.AppWebService
import deco.sleepp.WebService.ResponseWService

import kotlinx.android.synthetic.main.fragment_login_doctor.*
import kotlinx.android.synthetic.main.fragment_login_doctor.view.*


/**
 * A simple [Fragment] subclass.
 */
class LoginDoctorFrag : Fragment(), View.OnClickListener, ResponseWService {

    lateinit var doctor: Doctor
    lateinit var appService: AppWebService

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_login_doctor, container, false)
        view.bLoginDoctor.setOnClickListener(this)
        doctor = Doctor()
        appService = AppWebService(activity)
        appService.response = this
        return view
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bLoginDoctor -> {
                if (validateUser(doctor)) {
                    doctor.nombre = edNombreDoc.text.toString()
                    doctor.claveUnica = edClaveDoctor.text.toString()
                    appService.logingDoctor(this.doctor)
                } else {
                    Toast.makeText(activity, "Los campos no pueden estar vacíosª", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    fun validateUser(doctor: Doctor?): Boolean {
        val claveUnica = edNombreDoc.getText().toString()
        doctor?.claveUnica = claveUnica
        if (claveUnica.isEmpty()) {
            return false
        }
        return true

    }

    override fun didFInish(response: Any?) {
        doctor = response as Doctor
        Toast.makeText(activity, "LoginDoctor ${doctor.nombre}", Toast.LENGTH_SHORT).show()
        val menuDoctorActivity = Intent(activity, MenuDoctorActivity::class.java)
        Utils.setIdDOCTOR(activity, doctor.idDoctor.toString())
        startActivity(menuDoctorActivity)
    }

    override fun didFinishWithError(code: Int) {
        Toast.makeText(activity, "Hubo un error de validación", Toast.LENGTH_SHORT).show()
    }
}// Required empty public constructor
