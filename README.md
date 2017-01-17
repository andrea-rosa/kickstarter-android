# kickstarter-android
An Android fully materialized kickstarter project including the following libraries:

* [ButterKnife](http://jakewharton.github.io/butterknife/)
* [Retrofit](http://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide)
* [Parceler](https://github.com/johncarl81/parceler)
* [Dagger2](http://google.github.io/dagger/)
* [EventBus](http://greenrobot.org/eventbus/)

### Usage
Clone the repo
```bash
git clone https://github.com/andrea-rosa/kickstarter-android.git
```
Then open it in Android Studio and wait for gradle build

### Activities
Define your layout (e.g. `activity_main`)  
Create a class and extend `BaseActivity`  
Override `onCreate` like this
```java
public class MainActivity extends BaseActivity {
   ...
   @Override
		   protected void onCreate(Bundle savedInstanceState) {
	        setContentView(R.layout.activity_main);
	        super.onCreate(savedInstanceState);
       }
   ...
}
```
BasicActivity provide the rest interface and the butterknife binder

### Fragments
Define your layout (e.g. `fragment_home`)  
Create a class and extend `BaseFragment`  
Override `onCreateView` and initialize `layout` before inflate the view
```java
public class HomeFragment extends BaseFragment {
   ...
   @Override
       public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           this.layout = R.layout.fragment_home;
           return super.onCreateView(inflater, container, savedInstanceState);
       }
   ...
}
```
Then use the fragment transaction method in `Utils` class
```java
Utils.fragmentTransaction(new HomeFragment(), R.id.flContent, HomeFragment.TAG, true, getSupportFragmentManager());
```

BasicFragment provide the rest interface and the butterknife binder

### Preference Helper
To save values in preferences use
```java
PreferenceHelper.setString(myKey, myValue, context);
```
And to retrieve a value use
```java
PreferenceHelper.getString(myKey, defaultValue, context);
```
PreferenceHelper support the following types:
* `String`
* `Set<String>`
* `int`
* `float`
* `long`
* `boolean`
