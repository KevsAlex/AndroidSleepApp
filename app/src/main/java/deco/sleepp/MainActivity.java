package deco.sleepp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import deco.sleepp.Utils.Utils;

/**
 * Este es el menú principal de la aplicación
 *
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_ubicacion; // Boton que al pulsarlo, nos abrirá la direccion en google maps
    Button button_ir_cuestionarios; // Boton que al pulsarlo, nos abrirá la subventana cuestionarios
    Button button_teoria_trastornos;

    Toolbar toolbar; // Definimos la AppBar (Barra superior de la App)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_ubicacion = (Button) findViewById(R.id.button_info_clinica);
        button_ir_cuestionarios = (Button) findViewById(R.id.buton_escalas);
        button_teoria_trastornos = (Button) findViewById(R.id.button_tras_res);

        /**
         * Añadimos soporte al toolbar para que pueda funcionar
         */
        toolbar = (Toolbar) findViewById(R.id.bar_principal);
        toolbar.setTitle("DormiApp");
        setSupportActionBar(toolbar);



        button_ubicacion.setOnClickListener(this);
        button_ir_cuestionarios.setOnClickListener(this);
        button_teoria_trastornos.setOnClickListener(this);
    } // Fin del metodo onCreate()


    // **** Es necesario para que funcione el Toolbar ****
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_tras_res:
                Intent infoTrastornos = new Intent(this, TeoriaTrastornos.class);
                startActivity(infoTrastornos);
                break;

            case R.id.buton_escalas:
                Intent ir_encuestas = new Intent(this, CalidadSueno.class);
                startActivity(ir_encuestas);
                break;


            case R.id.button_info_clinica:
                Intent ir_clinica = new Intent(this,ClinicaInformacion.class);
                startActivity(ir_clinica);
                break;
        }
    } // fin del metodo onClick()



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.id_informacion:
                Intent informes = new Intent(this, AcercaDe.class);
                startActivity(informes);
                break;

            case R.id.id_salir:
                Utils.setIdpaciente(this,"");
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

} // Fin del MainActivity
