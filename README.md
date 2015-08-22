
##HeartRailsExpressApi

HeartRailsExpressApi for Android

Only `getStaion` method http://express.heartrails.com/api.html#station

[ ![Download](https://api.bintray.com/packages/shts/maven/heart-rails-express-api/images/download.svg) ](https://bintray.com/shts/maven/heart-rails-express-api/_latestVersion)

##How to use

###Installation

```gradle
dependencies {
    compile 'jp.shts.android:heart-rails-express-api:1.0.0'
}
```

###Usage

```java
HeartRailsExpressApiClient.Request request
        = new HeartRailsExpressApiClient.Request("line", "name", "prefecture");
HeartRailsExpressApiClient.get(
        getApplicationContext(), request, new HeartRailsExpressApiResponseHandler() {
    @Override
    public void onFinishRequest(ArrayList<Station> stations) {
        if (stations == null || stations.isEmpty()) {
            return;
        }
        setupListAdapter(stations);
    }
});



```
