package com.appcognito.retrofit2mvvm_covid19.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appcognito.retrofit2mvvm_covid19.R;
import com.appcognito.retrofit2mvvm_covid19.api.models.SidoInfo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterSidoInfo extends RecyclerView.Adapter<AdapterSidoInfo.ViewHolder>  {
    List<SidoInfo.SidoItem> events = new ArrayList<>();
    Context ctx;

    public AdapterSidoInfo(List<SidoInfo.SidoItem> events, Context context) {
        if (events != null) {
            if (this.events.size() > 0)
                this.events.clear();
            this.events.addAll(events);
        }
        ctx = context;
    }

    public void addSidoInfos(List<SidoInfo.SidoItem> events) {
        this.events.addAll(events);
    }

    @NonNull
    @Override
    public AdapterSidoInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_sido_info, parent,false);
        AdapterSidoInfo.ViewHolder viewHolder = new AdapterSidoInfo.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSidoInfo.ViewHolder holder, int position) {
        final SidoInfo.SidoItem event = events.get(position);

        holder.gubun.setText(event.gubun);
        holder.dt.setText(event.stdDay);
        holder.incCnt.setText(getNumberFormat(Integer.valueOf(event.incDec)));
        holder.defCnt.setText(getNumberFormat(Integer.valueOf(event.defCnt)));
        holder.isolIngCnt.setText(getNumberFormat(Integer.valueOf(event.isolIngCnt)));
        holder.isolClearCnt.setText(getNumberFormat(Integer.valueOf(event.isolClearCnt)));

    }

    private String getNumberFormat(int number) {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(number);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView gubun, dt, incCnt, defCnt, isolIngCnt, isolClearCnt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gubun = itemView.findViewById(R.id.gubun);
            dt = itemView.findViewById(R.id.dt);
            incCnt = itemView.findViewById(R.id.inc_cnt);
            defCnt = itemView.findViewById(R.id.def_cnt);
            isolIngCnt = itemView.findViewById(R.id.isol_ing_cnt);
            isolClearCnt = itemView.findViewById(R.id.isol_clear_cnt);
        }
    }
}
