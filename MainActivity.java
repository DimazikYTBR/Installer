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

        // 1. ПРОВЕРКА: Если уже "вшито" — сразу на ФИНАЛ 🍎
        if (p.getBoolean("injected", false)) {
            showThanks();
        } else {
            showWelcomeAlert();
        }
    }

    // --- ЭТАП 1: СИСТЕМНАЯ ПЛАШКА (ВЫБОР) ---
    private void showWelcomeAlert() {
        new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
            .setTitle("Dubligram Genesis 🧬")
            .setMessage("Swift iOS 26 Core Detected. Inject Swift codes to activate dynamic islands?")
            .setCancelable(false)
            .setPositiveButton("INJECT SWIFT", (d, w) -> startInfectionUI())
            .setNegativeButton("EXIT", (d, w) -> finish())
            .show();
    }

    // --- ЭТАП 2: ЗАГРУЗЧИК С ТВОИМИ КНОПКАМИ (0% -> 100%) ---
    private void startInfectionUI() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(1); root.setGravity(17);
        root.setBackgroundColor(0xFF121212); // Элитный графит 🌑

        GradientDrawable cardBg = new GradientDrawable();
        cardBg.setColor(0xFF1E1E1E); cardBg.setCornerRadius(60); // 20dp 🧼
        
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(1); card.setGravity(17);
        card.setPadding(80, 80, 80, 80); card.setBackground(cardBg);

        st = new TextView(this);
        st.setText("Swift iOS 26 Core Detected...");
        st.setTextColor(-1); st.setTextSize(17);
        st.setPadding(0, 0, 0, 40);

        pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        pb.setMax(100);
        pb.setLayoutParams(new LinearLayout.LayoutParams(700, 25));

        Button btnInfo = new Button(this);
        btnInfo.setText("PROCESS INFO"); btnInfo.setAllCaps(false);
        btnInfo.setTextColor(0xFF8129FF);
        GradientDrawable bd = new GradientDrawable();
        bd.setColor(0x1A8129FF); bd.setCornerRadius(40);
        btnInfo.setBackground(bd);
        
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(450, 110);
        lp.topMargin = 60; btnInfo.setLayoutParams(lp);

        card.addView(st); card.addView(pb); card.addView(btnInfo);
        root.addView(card); setContentView(root);

        // ПОТОК ПРОГРУЗКИ (0.2s вайб 🏎️💨)
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try { Thread.sleep(45); } catch (Exception e) {}
                final int prg = i;
                runOnUiThread(() -> {
                    pb.setProgress(prg);
                    st.setText("Injecting Swift iOS 26: " + prg + "%");
                    if (prg == 100) {
                        p.edit().putBoolean("injected", true).apply();
                        triggerRestart(); // ПЕРЕЗАГРУЗКА 🔄💀
                    }
                });
            }
        }).start();
    }

    // --- ЭТАП 3: ФИНАЛЬНЫЙ РЕСПЕКТ (ПОСЛЕ РЕСТАРТА) ---
    private void showThanks() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(1); root.setGravity(17);
        root.setBackgroundColor(0xFF121212);

        TextView msg = new TextView(this);
        msg.setText("Thanks for installing our software! 🍎\n\nDubligram Genesis is now active.");
        msg.setTextColor(-1); msg.setGravity(17); msg.setTextSize(20);
        msg.setPadding(60, 0, 60, 100);

        Button btn = new Button(this);
        btn.setText("CONTINUE (СЮДААААА!) 🔵");
        btn.setAllCaps(false); btn.setTextColor(-1);
        GradientDrawable b = new GradientDrawable();
        b.setColor(0xFF8129FF); b.setCornerRadius(60);
        btn.setBackground(b);
        btn.setLayoutParams(new LinearLayout.LayoutParams(850, 160));

        btn.setOnClickListener(v -> finish());
        root.addView(msg); root.addView(btn);
        setContentView(root);
    }

    private void triggerRestart() {
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        if (i != null) {
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
