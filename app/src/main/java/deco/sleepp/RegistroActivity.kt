package deco.sleepp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import deco.sleepp.Fragments.RegistroPaciente

import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    lateinit var fragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        setSupportActionBar(toolbar)
        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        supportActionBar?.title = "Registro Paciente"
        fragment = RegistroPaciente()
        transaction.replace(R.id.fragmentContainerRegistro,fragment)
        transaction.commit()
    }

}
