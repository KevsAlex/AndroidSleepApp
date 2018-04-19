package deco.sleepp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout.VERTICAL
import deco.sleepp.Adapters.MyHistoralPreguntasAdapter

import kotlinx.android.synthetic.main.activity_menu_doctor.*
import kotlinx.android.synthetic.main.content_menu_doctor.*

class MenuDoctorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_doctor)
        setSupportActionBar(toolbar)
        mRecyclerDoctor.adapter = MyHistoralPreguntasAdapter(this)
        mRecyclerDoctor.layoutManager = LinearLayoutManager(this,VERTICAL,false)
    }

}
