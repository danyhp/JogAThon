package alexgochi.jogathon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView lap;
    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;
    ArrayList<Runner> runners = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            runners = savedInstanceState.getParcelableArrayList("key");

        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRunner();
            }
        });

//        initializeData();

        // Get a handle to RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new RVAdapter(this, runners);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the runner data
        outState.putParcelableArrayList("key", runners);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addRunner() {

        // Get the layout inflater
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.runner_add, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogLayout)
                // Add action buttons
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        EditText mRunnerId = dialogLayout.findViewById(R.id.id_runner);
                        EditText mDonation = dialogLayout.findViewById(R.id.donation);

                        String mRunnerIdString = mRunnerId.getText().toString();
                        String mDonationString = mDonation.getText().toString();

                        Log.v("runnerid", "runnerid " + mRunnerIdString);

                        int currentRunnerId = Integer.parseInt(mRunnerIdString);
                        int currentDonation = Integer.parseInt(mDonationString);
                        runners.add(new Runner(currentRunnerId, currentDonation));

                        //	Notify	the	adapter,	that	the	data	has	changed	so	it	can
                        //	update	the	RecyclerView	to	display	the	data.
                        int runnerListSize = runners.size();
                        mRecyclerView.getAdapter().notifyItemInserted(runnerListSize);
                        //	Scroll	to	the	bottom.
                        mRecyclerView.smoothScrollToPosition(runnerListSize);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        builder.show();
    }

    private void initializeData() {
//        runners = new ArrayList<>();

        runners.add(new Runner(2009, 10000));
        runners.add(new Runner(2209, 20000));
        runners.add(new Runner(2309, 34400));
    }
}
