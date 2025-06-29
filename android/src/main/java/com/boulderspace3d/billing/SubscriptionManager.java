package com.boulderspace3d.billing;
import android.app.Activity;import androidx.annotation.NonNull;import com.android.billingclient.api.*;
import java.util.*;
public class SubscriptionManager implements PurchasesUpdatedListener {
    private final BillingClient client; private final Activity activity; private Purchase purchase;
    public SubscriptionManager(Activity act){activity=act;client=BillingClient.newBuilder(act).enablePendingPurchases().setListener(this).build();}
    public void startConnection(Runnable onReady){client.startConnection(new BillingClientStateListener(){@Override public void onBillingSetupFinished(@NonNull BillingResult r){if(r.getResponseCode()==BillingClient.BillingResponseCode.OK)onReady.run();}@Override public void onBillingServiceDisconnected(){}});}
    public void querySubscription(){client.queryPurchasesAsync(BillingClient.SkuType.SUBS,(r,p)->{ if(p!=null&&!p.isEmpty()) purchase=p.get(0); });}
    public void launchSubscriptionFlow(){ SkuDetailsParams p=SkuDetailsParams.newBuilder().setSkusList(List.of("boulderspace3d_sub_5eur")).setType(BillingClient.SkuType.SUBS).build(); client.querySkuDetailsAsync(p,(r,skus)->{ if(!skus.isEmpty()){ BillingFlowParams f=BillingFlowParams.newBuilder().setSkuDetails(skus.get(0)).build(); client.launchBillingFlow(activity,f); } }); }
    @Override public void onPurchasesUpdated(BillingResult r, List<Purchase> ps){ if(r.getResponseCode()==BillingClient.BillingResponseCode.OK&&ps!=null){purchase=ps.get(0);} }
    public boolean isSubscribed(){return purchase!=null&&purchase.getPurchaseState()==Purchase.PurchaseState.PURCHASED;}
}