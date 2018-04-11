package deco.sleepp.WebService;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import deco.sleepp.Models.Paciente;
import deco.sleepp.Utils.Utils;

/**
 * Created by admin on 07/04/18.
 */



public class AppWebService {

    ResponseWService mAppWebService;
    private Context mContext;
    public AppWebService(Context context){
        mContext = context;
    }

    public void loging(final Paciente paciente){
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
                            paciente.setId(jsonPaciente.getString("idPaciente"));
                            mAppWebService.didFInish(paciente);
                        } catch (JSONException e) {
                            e.printStackTrace();
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

    public void registro(final Paciente paciente){
        final JSONObject json = new JSONObject();
        try {
            json.put("correo", paciente.getCorreo());
            json.put("password", paciente.getPassword());
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
                        Log.i(Utils.APPNAME, response.toString());
                        Paciente paciente1 = new Paciente();
                        try {
                            JSONObject jsonPaciente = response.getJSONObject("paciente");
                            paciente.setCorreo(jsonPaciente.getString("correo"));
                            paciente.setId(jsonPaciente.getString("idPaciente"));
                            mAppWebService.didFInish(paciente);
                        } catch (JSONException e) {
                            e.printStackTrace();
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

    public ResponseWService getAppWebService() {
        return mAppWebService;
    }

    public void setAppWebService(ResponseWService appWebService) {
        mAppWebService = appWebService;
    }
}
