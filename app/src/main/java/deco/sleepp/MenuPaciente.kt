package deco.sleepp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import deco.sleepp.Fragments.QuestionarioFragment
import deco.sleepp.Models.Paciente
import deco.sleepp.Utils.Utils
import deco.sleepp.WebService.AppWebService
import deco.sleepp.WebService.ResponseWService


import kotlinx.android.synthetic.main.activity_menu_paciente.*
import kotlinx.android.synthetic.main.content_menu_paciente.*

class MenuPaciente : AppCompatActivity(), View.OnClickListener, ResponseWService {

    private var mAppWebService: AppWebService? = null
    private var mPaciente: Paciente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_paciente)
        setSupportActionBar(toolbar)
        viewClinica.setOnClickListener(this)
        viewEncuesta.setOnClickListener(this)
        viewQueSon.setOnClickListener(this)
        mPaciente = Paciente()
        mAppWebService = AppWebService(this)
        mAppWebService?.response = this
        mAppWebService?.getPacienteById(Utils.getIDPACIENTE(this))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.id_informacion -> {
                val informes = Intent(this, AcercaDe::class.java)
                startActivity(informes)
            }

            R.id.id_salir -> {
                Utils.setIdpaciente(this, "")
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onClick(view: View?) {
        var id = view?.id
        when (id) {
            R.id.viewClinica -> {
                val ir_clinica = Intent(this, ClinicaInformacion::class.java)
                startActivity(ir_clinica)
            }
            R.id.viewEncuesta -> {
                val ir_encuestas = Intent(this, CalidadSueno::class.java)
                startActivity(ir_encuestas)
                /*
                var manager = supportFragmentManager
                var transaction = manager.beginTransaction()
                transaction.replace(R.id.containerMenu,QuestionarioFragment())
                transaction.commit()
                */

            }
            R.id.viewQueSon -> {
                val infoTrastornos = Intent(this, TeoriaTrastornos::class.java)
                startActivity(infoTrastornos)
            }
        }
    }

    override fun didFInish(response: Any?) {
        mPaciente = response as Paciente?
        mTextName.text = "Nombre :" + mPaciente?.nombre

    }

    override fun didFinishWithError(code: Int) {
        Toast.makeText(this, "Errors", Toast.LENGTH_SHORT).show()
    }
}
