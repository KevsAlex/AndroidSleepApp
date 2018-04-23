package deco.sleepp.WebService;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import deco.sleepp.Models.Cuestionario;
import deco.sleepp.Models.Doctor;
import deco.sleepp.Models.Paciente;
import deco.sleepp.Models.Pregunta;
import deco.sleepp.Utils.Utils;
import deco.sleepp.WebService.ResponseWService;
import deco.sleepp.WebService.VolleySingleton;

/**
 * Created by admin on 07/04/18.
 */


public class AppWebService {

    ResponseWService mResponse;
    private Context mContext;

    public AppWebService(Context context) {
        mContext = context;
    }

    public void loging(final Paciente paciente) {
        final JSONObject json = new JSONObject();
        try {
            json.put("correo", paciente.getCorreo());
            json.put("password", paciente.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String endoPoint = "http://mybackendtestin.esy.es/dreamBack/request/loginPaciente.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, endoPoint, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(Utils.APPNAME, response.toString());
                        final Paciente paciente1 = new Paciente();
                        try {
                            JSONObject jsonPaciente = response.getJSONObject("paciente");
                            paciente.setIdPaciente(jsonPaciente.getString("idPaciente"));
                            mResponse.didFInish(paciente);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mResponse.didFinishWithError(0);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(Utils.APPNAME, error.toString());
            }
        });

        RequestQueue request = VolleySingleton.getInstance(mContext).getRequestQueue();
        request.add(jsonObjectRequest);


    }

    public void registro(final Paciente paciente) {
        final JSONObject json = new JSONObject();
        try {
            json.put("nombre", paciente.getNombre());
            json.put("apellidoPaterno", "1");
            json.put("apellidoMaterno", "s");
            json.put("direccion", "s");
            json.put("telefono", "s");
            json.put("celular", "s");
            json.put("password", paciente.getPassword());
            json.put("correo", paciente.getCorreo());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String endoPoint = "http://mybackendtestin.esy.es/dreamBack/request/insertarPaciente.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, endoPoint, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d(Utils.APPNAME, response.toString());
                        try {
                            JSONObject jsonPaciente = response.getJSONObject("paciente");
                            paciente.setIdPaciente(jsonPaciente.getString("idPaciente"));
                            mResponse.didFInish(paciente);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        StringRequest sr = new StringRequest(Request.Method.POST, endoPoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(Utils.APPNAME, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                mResponse.didFinishWithError(0);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre", paciente.getNombre());
                params.put("apellidoPaterno", "");
                params.put("apellidoMaterno", "");
                params.put("direccion", "");
                params.put("password", paciente.getPassword());
                params.put("correo", paciente.getCorreo());
                return params;
            }
        };
        RequestQueue request = VolleySingleton.getInstance(mContext).getRequestQueue();
        request.add(jsonObjectRequest);

    }

    /**
     * Obtener paciente por id
     * @param idPaciente
     */
    public void getPacienteById(String idPaciente){

        final JSONObject json = new JSONObject();
        try {
            json.put("idPaciente", idPaciente);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String endoPoint = "http://mybackendtestin.esy.es/dreamBack/request/getPacienteById.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, endoPoint, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(Utils.APPNAME, response.toString());
                        try {
                            boolean error = response.getBoolean("error");
                            if (!error){
                                JSONObject jsonPaciente = response.getJSONObject("paciente");
                                Paciente paciente = new Paciente();
                                paciente.setIdPaciente(jsonPaciente.getString("idPaciente"));
                                paciente.setNombre("bxbxbx");
                                if (jsonPaciente.getString("idDoctor") == null){
                                    paciente.setIdDoctor(null);
                                }else{
                                    if (!jsonPaciente.isNull("idDoctor")){
                                        paciente.setIdDoctor(Integer.valueOf(jsonPaciente.getString("idDoctor")));
                                    }else{
                                        paciente.setIdDoctor(0);
                                    }

                                }
                                mResponse.didFInish(paciente);
                            }else{
                                mResponse.didFinishWithError(0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue request = VolleySingleton.getInstance(mContext).getRequestQueue();
        request.add(jsonObjectRequest);

    }

    /**
     * Crea un nuevo cuestionario
     * @param paciente
     */
    public void crearCuestionario(Paciente paciente){
        final JSONObject json = new JSONObject();
        try {
            json.put("idPaciente", paciente.getIdPaciente());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String endoPoint = "http://mybackendtestin.esy.es/dreamBack/request/crearCuestionario.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, endoPoint, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(Utils.APPNAME, response.toString());
                        try {
                            boolean error = response.getBoolean("error");
                            if (!error){
                                JSONObject cuestionarioJson = response.getJSONObject("cuestionario");
                                Cuestionario cuestionario = new Cuestionario();
                                cuestionario.setIdCuestionario(cuestionarioJson.getString("idCuestionario"));
                                cuestionario.setIdPaciente(cuestionarioJson.getString("idPaciente"));
                                mResponse.didFInish(cuestionario);
                            }else{
                                mResponse.didFinishWithError(0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue request = VolleySingleton.getInstance(mContext).getRequestQueue();
        request.add(jsonObjectRequest);

    }

    /**
     * Crea una nueva pregunta
     * @param pregunta
     * @param cuestionario
     */
    public void crearPregunta(Pregunta pregunta,Cuestionario cuestionario){
        final JSONObject json = new JSONObject();
        try {
            json.put("pregunta", pregunta.getPregunta());
            json.put("respuesta", pregunta.getRespuesta());
            json.put("idCuestionario",cuestionario.getIdCuestionario());
            json.put("score",1);
            json.put("tipoPregunta","0'");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String endoPoint = "http://mybackendtestin.esy.es/dreamBack/request/crearPregunta.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, endoPoint, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(Utils.APPNAME, response.toString());
                        try {
                            boolean error = response.getBoolean("error");
                            if (!error){
                                mResponse.didFInish(true);
                            }else{
                                mResponse.didFinishWithError(0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue request = VolleySingleton.getInstance(mContext).getRequestQueue();
        request.add(jsonObjectRequest);

    }

    /**
     * Setea id de doctor si se conoce la pinchi clave del doctor
     * @param paciente
     * @param claveDoctor
     */
    public void seteaIdDoctor(Paciente paciente,String claveDoctor){
        final JSONObject json = new JSONObject();
        try {
            json.put("idPaciente", paciente.getIdPaciente());
            json.put("claveDoctor", claveDoctor);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String endoPoint = "http://mybackendtestin.esy.es/dreamBack/request/updateDoctorPaciente.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, endoPoint, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(Utils.APPNAME, response.toString());
                        try {
                            boolean error = response.getBoolean("error");
                            if (!error){
                                JSONObject doctorJson = response.getJSONObject("doctor");
                                Doctor doctor = new Doctor();
                                doctor.setIdDoctor(Integer.valueOf(doctorJson.getString("idDoctor")));
                                mResponse.didFInish(doctor);
                            }else{
                                mResponse.didFinishWithError(1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mResponse.didFinishWithError(1);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue request = VolleySingleton.getInstance(mContext).getRequestQueue();
        request.add(jsonObjectRequest);

    }

    public ResponseWService getResponse() {
        return mResponse;
    }

    public void setResponse(ResponseWService response) {
        mResponse = response;
    }
}
