package deco.sleepp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import deco.sleepp.Dialogs.MyDialog
import deco.sleepp.Fragments.QuestionarioFragment
import deco.sleepp.Models.Doctor
import deco.sleepp.Models.Paciente
import deco.sleepp.Utils.Utils
import deco.sleepp.WebService.AppWebService
import deco.sleepp.WebService.ResponseWService


import kotlinx.android.synthetic.main.activity_menu_paciente.*
import kotlinx.android.synthetic.main.content_menu_paciente.*

class MenuPaciente : AppCompatActivity(), View.OnClickListener, ResponseWService, MyDialog.DialogDelegate {

    private var mAppWebService: AppWebService? = null
    private var mPaciente: Paciente? = null
    private var dialog: MyDialog? = null

    companion object {
        public val KEY_PACIENTE = "keyPaciente"
    }

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
        dialog = MyDialog()

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
                if (mPaciente?.idDoctor != 0) {
                    val ir_encuestas = Intent(this, CalidadSueno::class.java)
                    ir_encuestas.putExtra(KEY_PACIENTE, mPaciente)
                    startActivity(ir_encuestas)
                } else {
                    dialog?.delegate = this
                    dialog?.show(fragmentManager, "")
                }

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
        if (response is Paciente) {
            mPaciente = response
            mTextName.text = "Nombre :" + mPaciente?.nombre
        }
        if (response is Doctor) {
            var doctor: Doctor = response
            mPaciente?.idDoctor = doctor.idDoctor
            dialog?.dismiss()
            Toast.makeText(this, "El registro de tu doctor ha sido exitoso", Toast.LENGTH_SHORT).show()

        }
    }

    override fun didFinishWithError(code: Int) {
        if (code != 0) {
            Toast.makeText(this, "Hubo un error", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        } else {
            Toast.makeText(this, "Hubo un error registrando tu doctor", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        }

    }

    override fun asignarDoctor(claveDoctor: String?) {
        mAppWebService?.seteaIdDoctor(mPaciente, claveDoctor)
    }
}
