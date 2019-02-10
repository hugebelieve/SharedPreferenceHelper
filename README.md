# Shared Prefrence Helper
An android libray which help you set and get shared preferences with easy functions, and without worrying about commit() and apply() usage.

Include the following line of code in your app gradle file
## implementation 'com.hugebelieve.sharedpreferencelibrary:Shared-Preference-Helper:1.0.0'

#Examples
Set shareed preference value
'''
new SharedPref(this).putData(key,value);
```
Key is string and value can be string, int, Float, Long, Boolean

Get Shared preference value
```
new SharedPref(this).getDataBool(key);
new SharedPref(this).getDataString(key);
new SharedPref(this).getDataString(key,defaultValue);
new SharedPref(this).getDataInt(key);
new SharedPref(this).getDataLong(key);
new SharedPref(this).getDataFloat(key);
```
