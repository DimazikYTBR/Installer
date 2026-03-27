// --- Dubligram Genesis Installer (vD2.2,0) ---

private void startGenesisInstaller() {
    // 1. CHECK (If already injected) 🧼
    SharedPreferences p = getSharedPreferences("Dubli", 0);
    if (p.getBoolean("injected", false)) {
        showThanks(); // Show final screen if done
        return;
    }

    // 2. RENDER LOADER (Graphite + 20dp 📐) 🌑
    LinearLayout root = new LinearLayout(this);
    root.setOrientation(1); root.setGravity(17);
    root.setBackgroundColor(0xFF121212); // Deep Graphite

    // CAPSULE CARD 🧼
    GradientDrawable gd = new GradientDrawable();
    gd.setColor(0xFF1E1E1E); gd.setCornerRadius(60); // 20dp
    
    LinearLayout card = new LinearLayout(this);
    card.setOrientation(1); card.setGravity(17);
    card.setPadding(80, 80, 80, 80); card.setBackground(gd);

    // STATUS TEXT ✍️
    TextView st = new TextView(this);
    st.setText("Swift iOS 26 Core Detected...");
    st.setTextColor(-1); st.setTextSize(17);
    st.setPadding(0, 0, 0, 40);

    // PROGRESS BAR 0-100% 🧪
    ProgressBar pb = new ProgressBar(this, null, 16842872);
    pb.setMax(100);
    pb.setLayoutParams(new LinearLayout.LayoutParams(700, 20));

    // BUTTON "INJECT" 🔵
    Button btn = new Button(this);
    btn.setText("INJECT SWIFT CODES");
    btn.setAllCaps(false); btn.setTextColor(-1);
    
    GradientDrawable bd = new GradientDrawable();
    bd.setColor(0xFF8129FF); bd.setCornerRadius(40);
    btn.setBackground(bd);

    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(550, 120);
    lp.topMargin = 70; btn.setLayoutParams(lp);

    // INJECTION LOGIC 🏎️💨
    btn.setOnClickListener(v -> {
        btn.setVisibility(8);
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try { Thread.sleep(45); } catch (Exception e) {}
                int prg = i;
                runOnUiThread(() -> {
                    pb.setProgress(prg);
                    st.setText("Injecting Swift iOS 26: " + prg + "%");
                    if (prg == 100) {
                        p.edit().putBoolean("injected", true).apply();
                        // RESTART AND SHOW "Thanks for installing our software!"
                    }
                });
            }
        }).start();
    });

    card.addView(st); card.addView(pb); card.addView(btn);
    root.addView(card); setContentView(root);
}

