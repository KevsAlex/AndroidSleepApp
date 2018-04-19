package deco.sleepp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import deco.sleepp.R;

/**
 * Created by admin on 07/04/18.
 */

public class Utils {
    public final static String APPNAME = "dreamApp";
    public final static String preferences = "dreamApp";
    public final static String IDPACIENTE = "idPacienye";
    public final static String IDDOCTOR = "idDoctor";

    public static void setIdpaciente(Context context,String idPaciente){
        SharedPreferences sharedPref = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(IDPACIENTE,idPaciente);
        edit.commit();
    }

    public static String getIDPACIENTE(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        return sharedPref.getString(IDPACIENTE,"");
    }

    public static void setIdDOCTOR(Context context,String idDoctor){
        SharedPreferences sharedPref = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(IDDOCTOR,idDoctor);
        edit.commit();
    }


    public static String getIDoctor(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        return sharedPref.getString(IDDOCTOR,"");
    }



}
