package deco.sleepp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class EscalaEpworth extends AppCompatActivity {

    Toolbar toolbar_epworth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escala_epworth);

        /* Damos Soporte ToolBar */
        toolbar_epworth = (Toolbar) findViewById(R.id.bar_epworth);
        toolbar_epworth.setTitle("Escala Epworth");
        setSupportActionBar(toolbar_epworth);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


    } // fin del método onCreate()

    // **** Es necesario para que funcione el Toolbar ****
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_epworth, menu);
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

    public void funcionResultados(View view) {


    }
} // Fin de EscalaEpworth
