package alexgochi.jogathon;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ASUS on 06-Mar-18.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RunnerViewHolder> {


    private final List<Runner> runners;
    private LayoutInflater mInflater;

    // Constructor

    public RVAdapter(Context context, List<Runner> runners) {
        this.runners = runners;
        this.mInflater = LayoutInflater.from(context);
    }

    class RunnerViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        public final TextView runnerID;
        // TextView lapDonation;
        public final TextView lapCount;
        RVAdapter mAdapter;

        public RunnerViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            runnerID = (TextView) itemView.findViewById(R.id.noPeserta);
            lapCount = (TextView) itemView.findViewById(R.id.lapPeserta);
//            this.mAdapter = adapter;

        }

    }

    @Override
    public RVAdapter.RunnerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.runner_item,
                viewGroup, false);
        RunnerViewHolder rvh = new RunnerViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RVAdapter.RunnerViewHolder runnerViewHolder, int i) {
        runnerViewHolder.runnerID.setText(Integer.toString(runners.get(i).runnerID));
        runnerViewHolder.lapCount.setText(Integer.toString(runners.get(i).lapCount));

    }

    @Override
    public int getItemCount() {
        return runners.size();
    }
}
