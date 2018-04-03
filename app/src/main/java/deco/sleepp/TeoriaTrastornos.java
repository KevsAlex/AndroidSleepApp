package deco.sleepp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TeoriaTrastornos extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button button_info_trastornos;
    Button button_insomnio;
    Button button_apnea_primaria;
    Button button_sahos;
    Button button_somnolencia;
    Button button_narcolepsia;
    Button button_insuficiencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria_trastornos);

        button_info_trastornos = (Button) findViewById(R.id.button_tras_res);
        button_insomnio = (Button) findViewById(R.id.button_insomnio_info);
        button_apnea_primaria = (Button) findViewById(R.id.button_apnea_sueno);
        button_sahos = (Button) findViewById(R.id.button_sahos_sueno);
        button_somnolencia = (Button) findViewById(R.id.button_somnolencia_info);
        button_narcolepsia = (Button) findViewById(R.id.button_narcolepsia_info);
        button_insuficiencia = (Button) findViewById(R.id.button_comportamiento_info);

        toolbar = (Toolbar) findViewById(R.id.bar_principal);
        toolbar.setTitle("Información");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        button_info_trastornos.setOnClickListener(this);
        button_insomnio.setOnClickListener(this);
        button_apnea_primaria.setOnClickListener(this);
        button_sahos.setOnClickListener(this);
        button_somnolencia.setOnClickListener(this);
        button_narcolepsia.setOnClickListener(this);
        button_insuficiencia.setOnClickListener(this);
    } // fin del metodo onCreate()


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_tras_res:
                Intent tras_sue = new Intent(this,TrastornosActivity.class);
                startActivity(tras_sue);
                break;

            case R.id.button_insomnio_info:
                Intent insomnio = new Intent(this,InsomnioActivity.class);
                startActivity(insomnio);
                break;

            case R.id.button_apnea_sueno:
                Intent apnea = new Intent(this,ApneaActivity.class);
                startActivity(apnea);
                break;

            case R.id.button_sahos_sueno:
                Intent sahos = new Intent(this,SahosActivity.class);
                startActivity(sahos);
                break;

            case R.id.button_somnolencia_info:
                Intent somnolencia = new Intent(this,SomnolenciaActivity.class);
                startActivity(somnolencia);
                break;

            case R.id.button_narcolepsia_info:
                Intent narcolepsia = new Intent(this,NarcolepsiaActivity.class);
                startActivity(narcolepsia);
                break;

            case R.id.button_comportamiento_info:
                Intent comportamiento = new Intent(this,InsuficienciaActivity.class);
                startActivity(comportamiento);
                break;
        }
    }
} // Fin de Teoria de Trastornos Activity
