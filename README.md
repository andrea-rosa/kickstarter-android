# kickstarter-android
An Android kickstarter project including the following libraries:

* [ButterKnife](http://jakewharton.github.io/butterknife/)
* [Retrofit](http://square.github.io/retrofit/)
* [Picasso](http://square.github.io/picasso/)
* [Parceler](https://github.com/johncarl81/parceler)
* [Dagger2](http://google.github.io/dagger/)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)

### Usage
Clone the repo
```bash
git clone https://github.com/andrea-rosa/kickstarter-android.git
```
Then open it in Android Studio and wait for gradle build

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

BasicFragment provide the rest interface, the otto bus and the butterknife binder

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
