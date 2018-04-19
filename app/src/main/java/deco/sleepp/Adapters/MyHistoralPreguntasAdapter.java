package deco.sleepp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import deco.sleepp.R;

/**
 * Created by admin on 18/04/18.
 */

public class MyHistoralPreguntasAdapter extends RecyclerView.Adapter<MyHistoralPreguntasAdapter.MyViewHolde>{

    private Context mContext;

    public MyHistoralPreguntasAdapter(Context context){
        mContext = context;
    }

    @Override
    public MyViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_preguntas,parent,false);
        MyViewHolde myViewHolde = new MyViewHolde(view);
        return myViewHolde;
    }

    @Override
    public void onBindViewHolder(MyViewHolde holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolde extends RecyclerView.ViewHolder{

        final TextView mTextPregunta;

        public MyViewHolde(View itemView) {
            super(itemView);
            mTextPregunta = itemView.findViewById(R.id.tPreguntaItem);

        }
    }
}
