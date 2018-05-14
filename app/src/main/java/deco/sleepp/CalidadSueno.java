package deco.sleepp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import deco.sleepp.Models.Cuestionario;
import deco.sleepp.Models.Paciente;
import deco.sleepp.Models.Pregunta;
import deco.sleepp.Utils.Utils;
import deco.sleepp.WebService.AppWebService;
import deco.sleepp.WebService.ResponseWService;

public class CalidadSueno extends AppCompatActivity implements View.OnClickListener, ResponseWService, RadioGroup.OnCheckedChangeListener {

    Toolbar sueno_toolbar;

    private ArrayList<Pregunta> mPreguntas;
    private String[] mTitulosPregunta;
    private Cuestionario mCuestionario;
    private Paciente mPaciente;

    private AppWebService mAppWebService;
    private TextView tv_Respuesta_hora1;
    private TextView tv_Respuesta_hora2;
    private TextView tv_reporte;
    private TextView tv_reporte2;
    private TextView tv_reporte3;
    private ImageButton btn_establecer_horapr1;
    private ImageButton btn_establecer_horapr2;
    private TimePickerDialog timePickerDialog;
    private Button btn_reporte;

    RadioGroup rg5;
    RadioGroup rg6;
    RadioGroup rg7;
    RadioGroup rg8;
    RadioGroup rg9;
    RadioGroup rg10;
    RadioGroup rg11;
    RadioGroup rg12;
    RadioGroup rg13;
    RadioGroup rg14;
    RadioGroup rg15;
    RadioGroup rg16;
    RadioGroup rg17;
    RadioGroup rg18;
    RadioGroup rg19;
    RadioGroup rg20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calidad_sueno);
        mPreguntas = new ArrayList<>();
        mTitulosPregunta = getResources().getStringArray(R.array.preguntasPaciente);
        this.initPreguntas();
        sueno_toolbar = (Toolbar) findViewById(R.id.bar_escala_sueno);
        sueno_toolbar.setTitle("Calidad Sueño");
        setSupportActionBar(sueno_toolbar);
        ActionBar actionBarSueno = getSupportActionBar();
        actionBarSueno.setDisplayHomeAsUpEnabled(true);
        btn_establecer_horapr1 = (ImageButton) findViewById(R.id.btn_hora1);
        btn_establecer_horapr2 = (ImageButton) findViewById(R.id.btn_hora2);
        tv_Respuesta_hora1 = (TextView) findViewById(R.id.tv_hora1);
        tv_Respuesta_hora2 = (TextView) findViewById(R.id.tv_hora2);
        tv_reporte = (TextView) findViewById(R.id.tv_todo1);
        tv_reporte2 = (TextView) findViewById(R.id.tv_todo2);
        tv_reporte3 = (TextView) findViewById(R.id.tv_todo3);
        btn_reporte = (Button) findViewById(R.id.btn_imprime_edo);
        rg5 = (RadioGroup) findViewById(R.id.radiogroup5);
        rg6 = (RadioGroup) findViewById(R.id.radiogroup6);
        rg7 = (RadioGroup) findViewById(R.id.radiogroup7);
        rg8 = (RadioGroup) findViewById(R.id.radiogroup8);
        rg9 = (RadioGroup) findViewById(R.id.radiogroup9);
        rg10 = (RadioGroup) findViewById(R.id.radiogroup10);
        rg11 = (RadioGroup) findViewById(R.id.radiogroup11);
        rg12 = (RadioGroup) findViewById(R.id.radiogroup12);
        rg13 = (RadioGroup) findViewById(R.id.radiogroup13);
        rg14 = (RadioGroup) findViewById(R.id.radiogroup14);
        rg15 = (RadioGroup) findViewById(R.id.radiogroup15);
        rg16 = (RadioGroup) findViewById(R.id.radiogroup16);
        rg17 = (RadioGroup) findViewById(R.id.radiogroup17);
        rg18 = (RadioGroup) findViewById(R.id.radiogroup18);
        rg19 = (RadioGroup) findViewById(R.id.radiogroup19);
        rg20 = (RadioGroup) findViewById(R.id.radiogroup20);
        btn_establecer_horapr1.setOnClickListener(this);
        btn_establecer_horapr2.setOnClickListener(this);
        btn_reporte.setOnClickListener(this);
        mPaciente = (Paciente) getIntent().getExtras().getSerializable(MenuPaciente.Companion.getKEY_PACIENTE());
        mAppWebService = new AppWebService(this);
        mAppWebService.setResponse(this);
        mAppWebService.crearCuestionario(mPaciente);
        rg5.setOnCheckedChangeListener(this);
        rg6.setOnCheckedChangeListener(this);
        rg7.setOnCheckedChangeListener(this);
        rg8.setOnCheckedChangeListener(this);
        rg9.setOnCheckedChangeListener(this);
        rg10.setOnCheckedChangeListener(this);
        rg11.setOnCheckedChangeListener(this);
        rg12.setOnCheckedChangeListener(this);
        rg13.setOnCheckedChangeListener(this);
        rg14.setOnCheckedChangeListener(this);
        rg15.setOnCheckedChangeListener(this);
        rg16.setOnCheckedChangeListener(this);
        rg17.setOnCheckedChangeListener(this);
        rg18.setOnCheckedChangeListener(this);
        rg19.setOnCheckedChangeListener(this);
        rg20.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_test_sueno, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_hora1:
                Calendar calendar = Calendar.getInstance();

                int hora1 = 0;
                int minuto1 = 0;

                hora1 = calendar.get(Calendar.HOUR);
                minuto1 = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(CalidadSueno.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay > 12) {
                            tv_Respuesta_hora1.setText(hourOfDay + " : " + minute + " PM");
                        } else
                            tv_Respuesta_hora1.setText(hourOfDay + " : " + minute + " AM");
                    }
                }, hora1, minuto1, false);
                timePickerDialog.show();
                break;

            case R.id.btn_hora2:
                Calendar calendar2 = Calendar.getInstance();
                int hora2 = 0;
                int minuto2 = 0;

                hora2 = calendar2.get(Calendar.HOUR);
                minuto2 = calendar2.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(CalidadSueno.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay < 12) {
                            tv_Respuesta_hora2.setText(hourOfDay + " : " + minute + " AM");
                        } else
                            tv_Respuesta_hora2.setText(hourOfDay + " : " + minute + " PM");
                    }
                }, hora2, minuto2, false);
                timePickerDialog.show();
                break;


            case R.id.btn_imprime_edo:

                // Get the checked Radio Button ID from Radio Group

                int selectedRadioButtonID5 = rg5.getCheckedRadioButtonId();
                int selectedRadioButtonID6 = rg6.getCheckedRadioButtonId();
                int selectedRadioButtonID7 = rg7.getCheckedRadioButtonId();
                int selectedRadioButtonID8 = rg8.getCheckedRadioButtonId();
                int selectedRadioButtonID9 = rg9.getCheckedRadioButtonId();
                int selectedRadioButtonID10 = rg10.getCheckedRadioButtonId();
                int selectedRadioButtonID11 = rg11.getCheckedRadioButtonId();
                int selectedRadioButtonID12 = rg12.getCheckedRadioButtonId();
                int selectedRadioButtonID13 = rg13.getCheckedRadioButtonId();
                int selectedRadioButtonID14 = rg14.getCheckedRadioButtonId();
                int selectedRadioButtonID15 = rg15.getCheckedRadioButtonId();
                int selectedRadioButtonID16 = rg16.getCheckedRadioButtonId();
                int selectedRadioButtonID17 = rg17.getCheckedRadioButtonId();
                int selectedRadioButtonID18 = rg18.getCheckedRadioButtonId();
                int selectedRadioButtonID19 = rg19.getCheckedRadioButtonId();
                int selectedRadioButtonID20 = rg20.getCheckedRadioButtonId();

                String ResHora1 = tv_Respuesta_hora1.getText().toString();
                String ResHora2 = tv_Respuesta_hora2.getText().toString();


                if (selectedRadioButtonID5 != -1 && selectedRadioButtonID6 != -1 && selectedRadioButtonID7 != -1 && selectedRadioButtonID8 != -1 && selectedRadioButtonID9 != -1 && selectedRadioButtonID10 != -1 && selectedRadioButtonID11 != -1 && selectedRadioButtonID12 != -1 && selectedRadioButtonID13 != -1
                        && selectedRadioButtonID14 != -1 && selectedRadioButtonID15 != -1 && selectedRadioButtonID16 != -1 && selectedRadioButtonID17 != -1 && selectedRadioButtonID18 != -1 && selectedRadioButtonID19 != -1 && selectedRadioButtonID20 != -1) {
                    RadioButton selectedRadioButton5 = (RadioButton) findViewById(selectedRadioButtonID5);
                    String selectedRadioButtonText5 = selectedRadioButton5.getText().toString();

                    RadioButton selectedRadioButton6 = (RadioButton) findViewById(selectedRadioButtonID6);
                    String selectedRadioButtonText6 = selectedRadioButton6.getText().toString();


                    RadioButton selectedRadioButton7 = (RadioButton) findViewById(selectedRadioButtonID7);
                    String selectedRadioButtonText7 = selectedRadioButton7.getText().toString();


                    RadioButton selectedRadioButton8 = (RadioButton) findViewById(selectedRadioButtonID8);
                    String selectedRadioButtonText8 = selectedRadioButton8.getText().toString();

                    RadioButton selectedRadioButton9 = (RadioButton) findViewById(selectedRadioButtonID9);
                    String selectedRadioButtonText9 = selectedRadioButton9.getText().toString();

                    RadioButton selectedRadioButton10 = (RadioButton) findViewById(selectedRadioButtonID10);
                    String selectedRadioButtonText10 = selectedRadioButton10.getText().toString();

                    RadioButton selectedRadioButton11 = (RadioButton) findViewById(selectedRadioButtonID11);
                    String selectedRadioButtonText11 = selectedRadioButton11.getText().toString();

                    RadioButton selectedRadioButton12 = (RadioButton) findViewById(selectedRadioButtonID12);
                    String selectedRadioButtonText12 = selectedRadioButton12.getText().toString();

                    RadioButton selectedRadioButton13 = (RadioButton) findViewById(selectedRadioButtonID13);
                    String selectedRadioButtonText13 = selectedRadioButton13.getText().toString();

                    RadioButton selectedRadioButton14 = (RadioButton) findViewById(selectedRadioButtonID14);
                    String selectedRadioButtonText14 = selectedRadioButton14.getText().toString();

                    RadioButton selectedRadioButton15 = (RadioButton) findViewById(selectedRadioButtonID15);
                    String selectedRadioButtonText15 = selectedRadioButton15.getText().toString();

                    RadioButton selectedRadioButton16 = (RadioButton) findViewById(selectedRadioButtonID16);
                    String selectedRadioButtonText16 = selectedRadioButton16.getText().toString();

                    RadioButton selectedRadioButton17 = (RadioButton) findViewById(selectedRadioButtonID17);
                    String selectedRadioButtonText17 = selectedRadioButton17.getText().toString();

                    RadioButton selectedRadioButton18 = (RadioButton) findViewById(selectedRadioButtonID18);
                    String selectedRadioButtonText18 = selectedRadioButton18.getText().toString();

                    RadioButton selectedRadioButton19 = (RadioButton) findViewById(selectedRadioButtonID19);
                    String selectedRadioButtonText19 = selectedRadioButton19.getText().toString();

                    RadioButton selectedRadioButton20 = (RadioButton) findViewById(selectedRadioButtonID20);
                    String selectedRadioButtonText20 = selectedRadioButton20.getText().toString();

                    this.setResults();
                    tv_reporte.setText("" + "Pregunta 1: " + ResHora1 + "\n" + "" + "Pregunta 2: " + ResHora2 + "\n" + "" + "La respuesta 5 es: " + selectedRadioButtonText5 + "\n" + "" + "La respuesta 6 es: " + selectedRadioButtonText6 + "\n" + "" + "La respuesta 7 es: " + selectedRadioButtonText7 + "\n" + "" + "La respuesta 8 es: " + selectedRadioButtonText8 + "\n" + "" + "La respuesta 9 es: " + selectedRadioButtonText9 + "\n" + "" + "La respuesta 10 es: " + selectedRadioButtonText10);
                    tv_reporte2.setText("" + "Pregunta 11: " + selectedRadioButtonText11 + "\n" + "" + "Pregunta 12: " + selectedRadioButtonText12 + "\n" + "" + "Pregunta 13: " + selectedRadioButtonText13 + "\n" + "" + "Pregunta 14: " + selectedRadioButtonText14 + "\n" + "" + "Pregunta 15: " + selectedRadioButtonText15);
                    tv_reporte3.setText("" + "La respuesta 16 es: " + selectedRadioButtonText16 + "\n" + "" + "La respuesta 17 es: " + selectedRadioButtonText17 + "\n" + "" + "La respuesta 18 es: " + selectedRadioButtonText18 + "\n" + "" + "La respuesta 19 es: " + selectedRadioButtonText19 + "\n" + "" + "La respuesta 20 es: " + selectedRadioButtonText20);


                } else {
                    tv_reporte.setText("Por favor complete TODAS las respuestas para ver su resultados");
                }
                break;
        } // Fin del Switch()
    }

    /**
     * Inicializamos el arreglo de preguntas
     */
    private void initPreguntas() {
        for (String titulo : mTitulosPregunta) {
            Pregunta pregunta = new Pregunta();
            pregunta.setPregunta(titulo);
            mPreguntas.add(pregunta);
        }
    }

    private void setResults() {
        for (int i = 0; i < mPreguntas.size(); i++) {
            Pregunta pregunta = mPreguntas.get(i);
            switch (i) {
                case 0:
                    String respuesta0 = tv_Respuesta_hora1.getText().toString();
                    pregunta.setRespuesta(respuesta0);
                    pregunta.setAnswered(true);
                    break;
                case 1:
                    String respuesta1 = tv_Respuesta_hora2.getText().toString();
                    pregunta.setRespuesta(respuesta1);
                    pregunta.setAnswered(true);
                    break;
                case 2:
                    String respuesta2 = ((EditText) findViewById(R.id.et_pregunta3)).getText().toString();
                    pregunta.setRespuesta(respuesta2);
                    pregunta.setAnswered(true);
                case 3:
                    String respuesta3 = ((EditText) findViewById(R.id.et_pregunta4)).getText().toString();
                    pregunta.setRespuesta(respuesta3);
                    pregunta.setAnswered(true);
                    break;
            }
        }

        this.enviarPreguntas();

    }

    /**
     * Envia las preguntas a la base de datos
     */
    private void enviarPreguntas() {
        for (Pregunta pregunta : mPreguntas) {
            if (pregunta.isAnswered()) {
                this.mAppWebService.crearPregunta(pregunta, mCuestionario);
            }
        }
        Toast.makeText(this, "Las preguntas se han contestado correctamente, le enviaremos a tu doctor los resultados", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void didFInish(Object response) {
        if (response instanceof Cuestionario) {
            mCuestionario = (Cuestionario) response;
            Log.d(Utils.APPNAME, "idCuestionario" + mCuestionario.getIdCuestionario());
        } else if (response instanceof Pregunta) {

        }


    }

    @Override
    public void didFinishWithError(int code) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        RadioButton radioButton = findViewById(checkedId);
        String respuesta = radioButton.getText().toString();
        if (radioGroup == rg5) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(4);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg6) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(5);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg7) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(6);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);

        }
        if (radioGroup == rg8) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(7);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);

        }
        if (radioGroup == rg9) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(8);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg10) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(9);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg11) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(10);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg12) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(11);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg13) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(12);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg14) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(13);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg15) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(14);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg16) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(15);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg17) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(16);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg18) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(17);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg19) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(18);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
        if (radioGroup == rg20) {
            Log.d(Utils.APPNAME, "radio 5");
            Pregunta pregunta = mPreguntas.get(19);
            pregunta.setAnswered(true);
            pregunta.setRespuesta(respuesta);
        }
    }
}
