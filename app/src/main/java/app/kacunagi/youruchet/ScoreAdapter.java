package app.kacunagi.youruchet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    ArrayList<Score> scores;

    ScoreAdapter(ArrayList<Score> scores){
        this.scores = scores;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_score_elem, parent, false);
        return new ScoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        holder.ScoreName.setText(scores.get(position).getName());
        holder.ScoreSum.setText(String.valueOf(scores.get(position).getSum()));
        holder.ScoreCurrency.setText(scores.get(position).getCurrency());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView ScoreName;
        TextView ScoreSum;
        TextView ScoreCurrency;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.activity_chooser_view_content);
            ScoreName = itemView.findViewById(R.id.tv_score_name);
            ScoreSum = itemView.findViewById(R.id.tv_score_sum);
            ScoreCurrency = itemView.findViewById(R.id.tv_score_currency);
        }
    }
}
