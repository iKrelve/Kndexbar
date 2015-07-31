package bar.view.krelve.kndexbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bar.view.krelve.kndexbar.view.KndexBar;

public class MainActivity extends AppCompatActivity {
    private KndexBar kb;
    private TextView tv_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kb = (KndexBar) findViewById(R.id.kb);
        tv_dialog = (TextView) findViewById(R.id.tv_dialog);
        List<String> indexes = new ArrayList<>();
        for (char c = 'A'; c < 'Z'; c++) {
            indexes.add(c + "");
        }
        kb.setIndexDatas(indexes);
        KndexBar.OnIndexChangedListener listener = new KndexBar.OnIndexChangedListener() {
            @Override
            public void onIndexChanged(int index, CharSequence data) {
                tv_dialog.setText(data);
                tv_dialog.setVisibility(View.VISIBLE);
                tv_dialog.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv_dialog.setVisibility(View.GONE);
                    }
                }, 500);
            }
        };
        kb.setOnIndexChangedListener(listener);
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
}
