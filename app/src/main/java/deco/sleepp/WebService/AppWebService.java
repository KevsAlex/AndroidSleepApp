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

import deco.sleepp.Models.Paciente;
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
                                if (jsonPaciente.getString("idPaciente") == null){
                                    paciente.setIdDoctor(null);
                                }else{
                                    paciente.setIdDoctor(Integer.valueOf(jsonPaciente.getString("idPaciente")));
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

    public ResponseWService getResponse() {
        return mResponse;
    }

    public void setResponse(ResponseWService response) {
        mResponse = response;
    }
}
