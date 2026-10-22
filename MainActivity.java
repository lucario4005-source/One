package com.viral.dataone;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private final int ink = Color.rgb(32, 33, 36);
    private final int blue = Color.rgb(66, 133, 244);

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(248,250,253));
        getWindow().setNavigationBarColor(Color.rgb(248,250,253));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(28), dp(22), dp(20));
        root.setBackgroundColor(Color.rgb(248,250,253));

        TextView title = new TextView(this);
        title.setText("Dataone"); title.setTextSize(29); title.setTypeface(Typeface.DEFAULT, Typeface.BOLD); title.setTextColor(ink);
        root.addView(title);
        TextView subtitle = new TextView(this);
        subtitle.setText("Your Google services in one place"); subtitle.setTextSize(15); subtitle.setTextColor(Color.rgb(95,99,104));
        LinearLayout.LayoutParams subLp = new LinearLayout.LayoutParams(-1, -2); subLp.topMargin = dp(7); subLp.bottomMargin = dp(24); root.addView(subtitle, subLp);

        addService(root, "G", "Gmail", "Email and inbox", "https://mail.google.com", Color.rgb(234,67,53));
        addService(root, "▧", "Google Photos", "Photos and videos", "https://photos.google.com", Color.rgb(66,133,244));
        addService(root, "▣", "Google Drive", "Files and documents", "https://drive.google.com", Color.rgb(15,157,88));
        addService(root, "●", "Google Account", "Manage your Google account", "https://myaccount.google.com", Color.rgb(251,188,5));

        TextView note = new TextView(this);
        note.setText("Opens Google's official services. Sign in to the account you want to use.");
        note.setTextColor(Color.rgb(95,99,104)); note.setTextSize(12); note.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams noteLp = new LinearLayout.LayoutParams(-1, -2); noteLp.topMargin = dp(18); root.addView(note, noteLp);
        setContentView(root);
    }

    private void addService(LinearLayout root, String symbol, String name, String desc, String url, int color) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL); card.setGravity(Gravity.CENTER_VERTICAL); card.setPadding(dp(16), dp(14), dp(14), dp(14));
        GradientDrawable bg = new GradientDrawable(); bg.setColor(Color.WHITE); bg.setCornerRadius(dp(18)); bg.setStroke(dp(1), Color.rgb(232,234,237)); card.setBackground(bg); card.setElevation(dp(1));
        LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(-1, -2); cardLp.bottomMargin = dp(12); root.addView(card, cardLp);

        TextView icon = new TextView(this); icon.setText(symbol); icon.setTextColor(Color.WHITE); icon.setTextSize(23); icon.setTypeface(Typeface.DEFAULT, Typeface.BOLD); icon.setGravity(Gravity.CENTER);
        GradientDrawable iconBg = new GradientDrawable(); iconBg.setColor(color); iconBg.setCornerRadius(dp(14)); icon.setBackground(iconBg);
        card.addView(icon, new LinearLayout.LayoutParams(dp(52), dp(52)));

        LinearLayout texts = new LinearLayout(this); texts.setOrientation(LinearLayout.VERTICAL); texts.setPadding(dp(14), 0, 0, 0);
        LinearLayout.LayoutParams textLp = new LinearLayout.LayoutParams(0, -2, 1); card.addView(texts, textLp);
        TextView label = new TextView(this); label.setText(name); label.setTextColor(ink); label.setTextSize(16); label.setTypeface(Typeface.DEFAULT, Typeface.BOLD); texts.addView(label);
        TextView detail = new TextView(this); detail.setText(desc); detail.setTextColor(Color.rgb(95,99,104)); detail.setTextSize(12);
        LinearLayout.LayoutParams detailLp = new LinearLayout.LayoutParams(-1, -2); detailLp.topMargin = dp(4); texts.addView(detail, detailLp);
        TextView arrow = new TextView(this); arrow.setText("›"); arrow.setTextSize(30); arrow.setTextColor(Color.rgb(95,99,104)); card.addView(arrow);
        View.OnClickListener click = v -> { try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); } catch (Exception e) { Toast.makeText(this, "Unable to open service", Toast.LENGTH_SHORT).show(); } };
        card.setOnClickListener(click); card.setFocusable(true); card.setClickable(true);
    }
    private int dp(int v) { return (int)(v * getResources().getDisplayMetrics().density + 0.5f); }
}
