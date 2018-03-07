package alexgochi.jogathon;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASUS on 06-Mar-18.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RunnerViewHolder> {


    private final ArrayList<Runner> runners;
    private LayoutInflater mInflater;

    // Constructor

    public RVAdapter(Context context, ArrayList<Runner> runners) {
        this.runners = runners;
        this.mInflater = LayoutInflater.from(context);
    }

    class RunnerViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        public final TextView runnerID;
        public final TextView lapCount;
        public final ImageButton counter;


        public RunnerViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            runnerID = (TextView) itemView.findViewById(R.id.noPeserta);
            lapCount = (TextView) itemView.findViewById(R.id.lapPeserta);
            counter = (ImageButton) itemView.findViewById(R.id.countLap);
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
    public void onBindViewHolder(final RVAdapter.RunnerViewHolder runnerViewHolder, final int i) {

        runnerViewHolder.runnerID.setText(Integer.toString(runners.get(i).runnerID));
        runnerViewHolder.lapCount.setText(Integer.toString(runners.get(i).lapCount));
        runnerViewHolder.counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runners.get(i).lapCount++;
                runnerViewHolder.lapCount.setText(Integer.toString(runners.get(i).lapCount));
                Vibrator vibe = (Vibrator) runnerViewHolder.itemView.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(100);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (runners != null) {
            return runners.size();
        } else {
            return 0;
        }
    }
}
