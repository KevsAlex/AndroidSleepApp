package deco.sleepp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity


import kotlinx.android.synthetic.main.activity_menu_paciente.*

class MenuPaciente : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_paciente)
        setSupportActionBar(toolbar)

    }

}
