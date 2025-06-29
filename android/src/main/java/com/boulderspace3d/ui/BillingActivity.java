package com.boulderspace3d.ui;
import android.os.Bundle;import androidx.appcompat.app.AppCompatActivity;import com.boulderspace3d.billing.SubscriptionManager;import com.boulderspace3d.ads.AdManager;
public class BillingActivity extends AppCompatActivity {
    private SubscriptionManager sub;
    @Override protected void onCreate(Bundle s){ super.onCreate(s); sub=new SubscriptionManager(this); sub.startConnection(() -> sub.querySubscription()); AdManager.loadAd(this);}
    public void onMiniBossDefeated(){ if(!sub.isSubscribed()) AdManager.showAd(this,()->{}); }
    public void onBigBossDefeated(){ if(!sub.isSubscribed()) AdManager.showAd(this,()->{}); }
    public void onSubscribeClicked(){ sub.launchSubscriptionFlow(); }
}