package deco.sleepp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ClinicaInformacion extends AppCompatActivity {

    Button button_mapa;
    Toolbar toolbar_clinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinica_informacion);

        button_mapa = (Button) findViewById(R.id.button_mapa);

        button_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Escribimos la dirección que vamos a buscar
                String direccion = "Unidad de Atención Médica de Alta Especialidad";

                // La dirección no acepta espacios , lo cambiamos por un "+"
                direccion.replace(" ", "+");

                // creamos la direccion de Google Maps
                String URL_GoogleMaps = "https://www.google.com.mx/maps/place/Unidad+de+Atenci%C3%B3n+M%C3%A9dica+de+Alta+Especialidad/@19.3295738,-99.1899003,17z/data=!3m1!4b1!4m5!3m4!1s0x85ce00072527436d:0x35809d5df20fe8c7!8m2!3d19.3295738!4d-99.1877116?dcr=0" + direccion;

                // Creamos la Intencion de ver el URL
                Intent verDireccion = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_GoogleMaps));
                startActivity(verDireccion);
            }
        });


        /* Damos Soporte ToolBar */
        toolbar_clinica = (Toolbar) findViewById(R.id.barra_clinica);
        toolbar_clinica.setTitle("Clínica De Trastornos");
        setSupportActionBar(toolbar_clinica);
        ActionBar actionBarClinica = getSupportActionBar();
        actionBarClinica.setDisplayHomeAsUpEnabled(true);
    } // fin del metodo onCreate()

    // **** Es necesario para que funcione el Toolbar ****
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_clinica, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
} // Fin de Clinica Informacion Activity
