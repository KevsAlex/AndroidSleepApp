package deco.sleepp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View


import kotlinx.android.synthetic.main.activity_menu_paciente.*
import kotlinx.android.synthetic.main.content_menu_paciente.*

class MenuPaciente : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_paciente)
        setSupportActionBar(toolbar)
        viewClinica.setOnClickListener(this)
        viewEncuesta.setOnClickListener(this)
        viewQueSon.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        var id = view?.id
        when (id){
            R.id.viewClinica ->{
                val ir_clinica = Intent(this, ClinicaInformacion::class.java)
                startActivity(ir_clinica)
            }
            R.id.viewEncuesta ->{
                val ir_encuestas = Intent(this, CalidadSueno::class.java)
                startActivity(ir_encuestas)

            }
            R.id.viewQueSon ->{
                val infoTrastornos = Intent(this, TeoriaTrastornos::class.java)
                startActivity(infoTrastornos)
            }
        }


    }
}
