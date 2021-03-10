package app.kacunagi.youruchet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{
    ArrayList<HistoryElem> histories;

    HistoryAdapter(ArrayList<HistoryElem> histories){
        this.histories = histories;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_history_elem, parent, false);
        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.scoreHistory.setText(String.valueOf(histories.get(position).getScoreName()));
        holder.sumHistory.setText(String.valueOf(histories.get(position).getSum()));
        holder.currencyHistory.setText(histories.get(position).getCurrency());
        holder.commentHistory.setText(histories.get(position).getComment());
        holder.dateHistory.setText(new SimpleDateFormat("dd/MM HH.mm").format(histories.get(position).getDateHistory()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView scoreHistory;
        TextView sumHistory;
        TextView currencyHistory;
        TextView commentHistory;
        TextView dateHistory;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.activity_chooser_view_content);
            scoreHistory = itemView.findViewById(R.id.tv_score_history);
            sumHistory = itemView.findViewById(R.id.tv_sum_history);
            currencyHistory = itemView.findViewById(R.id.tv_currency_history);
            commentHistory = itemView.findViewById(R.id.tv_comment_history);
            dateHistory = itemView.findViewById(R.id.tv_date_history);
        }
    }
}
