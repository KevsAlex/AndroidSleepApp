package deco.sleepp.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import deco.sleepp.CalidadSueno
import deco.sleepp.MenuDoctorActivity
import deco.sleepp.Models.Doctor
import deco.sleepp.Models.Paciente

import deco.sleepp.R

import kotlinx.android.synthetic.main.fragment_login_doctor.*
import kotlinx.android.synthetic.main.fragment_login_doctor.view.*


/**
 * A simple [Fragment] subclass.
 */
class LoginDoctorFrag : Fragment() , View.OnClickListener{

     var doctor : Doctor? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_login_doctor, container, false)
        view.bLoginDoctor.setOnClickListener(this)
        doctor = Doctor()
        return view
    }

    override fun onClick(view: View?) {
        when (view?.id){
            R.id.bLoginDoctor->{
                if (validateUser(doctor)){
                    Toast.makeText(activity, "LoginDoctorª", Toast.LENGTH_SHORT).show()
                    val menuDoctorActivity= Intent(activity, MenuDoctorActivity::class.java)
                    startActivity(menuDoctorActivity)
                }else{
                    Toast.makeText(activity, "Los campos no pueden estar vacíosª", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    fun validateUser(doctor: Doctor?): Boolean {
        val claveUnica = edCorreoDoctor.getText().toString()
        doctor?.claveUnica = claveUnica
        if (claveUnica.isEmpty()){
            return false
        }
        return true

    }
}// Required empty public constructor
