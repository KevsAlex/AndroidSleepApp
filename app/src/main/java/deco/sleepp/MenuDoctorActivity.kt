package deco.sleepp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout.*
import android.widget.ProgressBar
import deco.sleepp.Adapters.MyHistoralPreguntasAdapter
import deco.sleepp.Models.Doctor
import deco.sleepp.Models.Pregunta
import deco.sleepp.R.id.mRecyclerDoctor
import deco.sleepp.R.id.toolbar
import deco.sleepp.Utils.Utils
import deco.sleepp.WebService.AppWebService
import deco.sleepp.WebService.ResponseWService
import junit.runner.Version.id

import kotlinx.android.synthetic.main.activity_menu_doctor.*
import kotlinx.android.synthetic.main.content_menu_doctor.*
import java.util.ArrayList

class MenuDoctorActivity : AppCompatActivity(), ResponseWService {

    lateinit var appService: AppWebService
    lateinit var preguntas: ArrayList<Pregunta>
    lateinit var adapter : MyHistoralPreguntasAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_doctor)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        preguntas = ArrayList()
        appService = AppWebService(this)
        appService.response = this
        appService.getPreguntasBy(Utils.getIDoctor(this))
        appService.getDoctorById(Utils.getIDoctor(this))
        adapter = MyHistoralPreguntasAdapter(this,preguntas)
        mRecyclerDoctor.adapter = adapter
        mRecyclerDoctor.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        Utils.getIDoctor(this)




    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
                Utils.setIdDOCTOR(this,"");
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun didFInish(response: Any?) {
        mRecyclerDoctor.visibility = View.VISIBLE
        progress.visibility = View.INVISIBLE
        if (response is Doctor) {
            var doctor = Doctor()
            doctor = response
            mTextDoctorName.text = "Nombre: ${doctor.nombre}"
        } else {
            preguntas = response as ArrayList<Pregunta>
            adapter.mPreguntas = preguntas
            mRecyclerDoctor.adapter.notifyDataSetChanged()
        }



    }

    override fun didFinishWithError(code: Int) {
        mRecyclerDoctor.visibility = View.GONE
        textDefault.visibility = View.VISIBLE
        progress.visibility = View.INVISIBLE

    }
}
