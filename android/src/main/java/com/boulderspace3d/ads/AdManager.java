package com.boulderspace3d.ads;
import android.app.Activity;import com.google.android.gms.ads.*;import com.google.android.gms.ads.interstitial.*;
public class AdManager {
    private static InterstitialAd ad; private static final String AD_UNIT="ca-app-pub-xxx/zzz";
    public static void loadAd(Activity act){ AdRequest r=new AdRequest.Builder().build(); InterstitialAd.load(act,AD_UNIT,r,new InterstitialAdLoadCallback(){@Override public void onAdLoaded(InterstitialAd a){ad=a;}@Override public void onAdFailedToLoad(int e){ad=null;} }); }
    public static void showAd(Activity act,Runnable onComplete){ if(ad!=null){ ad.show(act); ad.setFullScreenContentCallback(new FullScreenContentCallback(){@Override public void onAdDismissedFullScreenContent(){onComplete.run();loadAd(act);} }); } else { onComplete.run(); loadAd(act); } }
}
