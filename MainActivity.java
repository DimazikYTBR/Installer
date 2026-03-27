// --- Dubligram Genesis Installer (vD2.2,0) ---
package com.dubligram.genesis.installer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private SharedPreferences p;
    private TextView st;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = getSharedPreferences("Dubli", 0);

        if (p.getBoolean("injected", false)) {
            showThanks();
        } else {
            showWelcomeAlert();
        }
    }

    private void showWelcomeAlert() {
        new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
            .setTitle("Dubligram Genesis 🧬")
            .setMessage("Swift iOS 26 Core Detected. Inject Swift codes to activate dynamic islands?")
            .setCancelable(false)
            .setPositiveButton("INJECT SWIFT", (d, w) -> startInfectionUI())
            .setNegativeButton("EXIT", (d, w) -> finish())
            .show();
    }

    private void startInfectionUI() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setBackgroundColor(0xFF121212);

        GradientDrawable cardBg = new GradientDrawable();
        cardBg.setColor(0xFF1E1E1E);
        cardBg.setCornerRadius(60);
        
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setGravity(Gravity.CENTER);
        card.setPadding(80, 80, 80, 80);
        card.setBackground(cardBg);

        st = new TextView(this);
        st.setText("Swift iOS 26 Core Detected...");
        st.setTextColor(Color.WHITE);
        st.setTextSize(17);
        st.setPadding(0, 0, 0, 40);

        pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        pb.setMax(100);
        pb.setLayoutParams(new LinearLayout.LayoutParams(700, 25));

        card.addView(st);
        card.addView(pb);
        root.addView(card);
        setContentView(root);

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try { Thread.sleep(45); } catch (Exception e) {}
                final int prg = i;
                runOnUiThread(() -> {
                    pb.setProgress(prg);
                    st.setText("Injecting Swift iOS 26: " + prg + "%");
                    if (prg == 100) {
                        p.edit().putBoolean("injected", true).apply();
                        triggerRestart();
                    }
                });
            }
        }).start();
    }

    private void showThanks() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setBackgroundColor(0xFF121212);

        TextView msg = new TextView(this);
        msg.setText("Thanks for installing our software! 🍎\n\nDubligram Genesis is now active.");
        msg.setTextColor(Color.WHITE);
        msg.setGravity(Gravity.CENTER);
        msg.setTextSize(20);
        msg.setPadding(60, 0, 60, 100);

        Button btn = new Button(this);
        btn.setText("CONTINUE (СЮДААААА!) 🔵");
        btn.setAllCaps(false);
        GradientDrawable b = new GradientDrawable();
        b.setColor(0xFF8129FF);
        b.setCornerRadius(60);
        btn.setBackground(b);
        btn.setTextColor(Color.WHITE);
        btn.setLayoutParams(new LinearLayout.LayoutParams(850, 160));

        btn.setOnClickListener(v -> finish());
        root.addView(msg);
        root.addView(btn);
        setContentView(root);
    }

    private void triggerRestart() {
        Intent i = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (i != null) {
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
