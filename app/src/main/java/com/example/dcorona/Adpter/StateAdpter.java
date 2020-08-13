package com.example.dcorona.Adpter;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcorona.Model.State_model;
import com.example.dcorona.R;

import java.util.List;

public class StateAdpter extends RecyclerView.Adapter<StateAdpter.ViewHolder> {

    private Context context;
    private List<State_model>State_list;
    public StateAdpter(Context context, List<State_model>state_model) {

        this.context=context;
        this.State_list=state_model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.state_row,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        State_model state_model =State_list.get(position);
        holder.stateName.setText(state_model.getState_name());
        holder.stateActive.setText(state_model.getState_active() );
        holder.stateDeath.setText(state_model.getState_death() );
        holder.stateRecover.setText(state_model.getState_recover() );

    }

    @Override
    public int getItemCount() {
        return State_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName;
        TextView stateActive;
        TextView stateDeath;
        TextView stateRecover;
        public ViewHolder(@NonNull View itemView,Context ntext) {

            super(itemView);
            context =ntext;
            stateName=(TextView) itemView.findViewById(R.id.state_name);
            stateActive=(TextView) itemView.findViewById(R.id.state_active);
            stateDeath=(TextView) itemView.findViewById(R.id.state_death);
            stateRecover=(TextView) itemView.findViewById(R.id.state_recover);

        }
    }
}
