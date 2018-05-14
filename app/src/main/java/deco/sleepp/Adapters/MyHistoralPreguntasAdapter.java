package deco.sleepp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import deco.sleepp.Models.Pregunta;
import deco.sleepp.R;

/**
 * Created by admin on 18/04/18.
 */

public class MyHistoralPreguntasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int PACIENTE = 0;
    public static final int PREGUNTA = 1;

    private Context mContext;
    public ArrayList<Pregunta> mPreguntas;

    public MyHistoralPreguntasAdapter(Context context, ArrayList<Pregunta> preguntas) {
        mPreguntas = preguntas;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_preguntas, parent, false);
        final View pacienteView = LayoutInflater.from(mContext).inflate(R.layout.item_paciente_pregunta, parent, false);

        switch (viewType) {
            case PACIENTE:
                return new PacienteVH(pacienteView);
            case PREGUNTA:
                return new MyViewHolde(view);
            default:
                return new PacienteVH(pacienteView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Pregunta pregunta = (Pregunta) mPreguntas.get(position);
        if (viewType == PACIENTE) {
            PacienteVH pacienteVH = (PacienteVH) holder;
            pacienteVH.mTextNombrePaciente.setText(pregunta.getPregunta());

        } else if (viewType == PREGUNTA) {

            MyViewHolde myViewHolde = (MyViewHolde) holder;
            myViewHolde.mTextPregunta.setText(pregunta.getPregunta());
            myViewHolde.mTextRespuesta.setText(pregunta.getRespuesta());
        }
    }

    @Override
    public int getItemCount() {
        return mPreguntas.size();
    }

    @Override
    public int getItemViewType(int position) {
        Pregunta pregunta = mPreguntas.get(position);
        return pregunta.isPaciente() ? PACIENTE : PREGUNTA;
    }

    public class MyViewHolde extends RecyclerView.ViewHolder {

        final TextView mTextPregunta;
        final TextView mTextRespuesta;
        public MyViewHolde(View itemView) {
            super(itemView);
            mTextPregunta = itemView.findViewById(R.id.tPreguntaItem);
            mTextRespuesta = itemView.findViewById(R.id.textRespuesta);

        }
    }

    public class PacienteVH extends RecyclerView.ViewHolder {
        TextView mTextNombrePaciente;

        public PacienteVH(View itemView) {
            super(itemView);
            mTextNombrePaciente = itemView.findViewById(R.id.textPacienteNamePregunta);
        }
    }
}
