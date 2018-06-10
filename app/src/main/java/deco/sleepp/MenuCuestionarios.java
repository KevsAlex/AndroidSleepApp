package deco.sleepp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuCuestionarios extends AppCompatActivity implements View.OnClickListener {

    Button button_ir_sueno;
    Button button_ir_epworth;


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cuestionarios);


        button_ir_sueno = (Button) findViewById(R.id.button_tras_res);
        button_ir_epworth = (Button) findViewById(R.id.button_Epworth);


        /* Damos Soporte a el ToolBar */
        toolbar = (Toolbar) findViewById(R.id.bar_principal);
        toolbar.setTitle("Evaluación");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        button_ir_epworth.setOnClickListener(this);
        button_ir_sueno.setOnClickListener(this);

    } // Fin del método onCreate


    // **** Es necesario para que funcione el Toolbar ****
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cuestionarios, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.id_acerca_cuestionarios:
                Toast.makeText(getApplicationContext(), "Latest version installed" , Toast.LENGTH_SHORT).show();;
                break;


            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            // Lo que sucede cuando presiono el boton ir a escala Epworth
            case R.id.button_Epworth:
                Intent ir_epworth = new Intent(this,EscalaEpworth.class);
                startActivity(ir_epworth);
                break;
            case R.id.button_tras_res:
                Intent ir_test_sueno = new Intent(this,CalidadSueno.class);
                startActivity(ir_test_sueno);
                break;
        }

    } // Fin del método onClick
} // Fin de la clase MainCuestionarios
