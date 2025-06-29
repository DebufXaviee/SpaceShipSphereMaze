package com.boulderspace3d.billing;

import android.app.Activity;
import androidx.annotation.MainThread;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdManager {
    private static InterstitialAd interstitial;
    private static final String AD_UNIT_ID = "ca-app-pub-xxxxxxxxxxxxxxxx/zzzzzzzzzz";

    public static void loadAd(Activity activity) {
        AdRequest request = new AdRequest.Builder().build();
        InterstitialAd.load(activity, AD_UNIT_ID, request,
            new InterstitialAdLoadCallback() {
                @Override public void onAdLoaded(InterstitialAd ad) {
                    interstitial = ad;
                    interstitial.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override public void onAdDismissedFullScreenContent() {
                            interstitial = null;
                            loadAd(activity);
                        }
                    });
                }
                @Override public void onAdFailedToLoad(int errorCode) {
                    interstitial = null;
                }
            });
    }

    @MainThread
    public static void showAd(Activity activity, Runnable onComplete) {
        if (interstitial != null) {
            interstitial.show(activity);
            interstitial.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override public void onAdDismissedFullScreenContent() {
                    onComplete.run();
                }
            });
        } else {
            onComplete.run();
            loadAd(activity);
        }
    }
}