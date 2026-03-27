// --- Dubligram Genesis Installer (vD2.2,0) ---
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.Gravity;

public class MainActivity extends Activity {
    private SharedPreferences p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = getSharedPreferences("Dubli", 0);

        if (p.getBoolean("injected", false)) {
            showFinal();
        } else {
            showStart();
        }
    }

    private void showStart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dubligram Genesis 🧬");
        builder.setMessage("Inject Swift iOS 26 Core?");
        builder.setCancelable(false);
        builder.setPositiveButton("INJECT", (d, w) -> startInfection());
        builder.show();
    }

    private void startInfection() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        
        TextView tv = new TextView(this);
        tv.setText("Injecting Swift: 0%");
        root.addView(tv);

        ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        pb.setMax(100);
        root.addView(pb);

        setContentView(root);

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try { Thread.sleep(40); } catch (Exception e) {}
                final int progress = i;
                runOnUiThread(() -> {
                    pb.setProgress(progress);
                    tv.setText("Injecting Swift: " + progress + "%");
                    if (progress == 100) {
                        p.edit().putBoolean("injected", true).apply();
                        restart();
                    }
                });
            }
        }).start();
    }

    private void showFinal() {
        TextView tv = new TextView(this);
        tv.setText("Thanks for installing! Dubligram Active 🍎");
        tv.setGravity(Gravity.CENTER);
        setContentView(tv);
    }

    private void restart() {
        Intent i = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (i != null) {
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}

