# Kotpref Encrypt Support

Encrypt Support of [Kotpref](https://github.com/chibatching/Kotpref)

This is a support library on basis of Kotpref, you should use this with it.

### Install

```groovy
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```
```groovy
dependencies {
    compile 'com.github.fly7632785:KotprefEncryptSupport:1.0.0'
}
```

### Set up

Pass the application context to Kotpref

```kotlin
class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // add Encrypt Support 
        Kotpref.ecGson = Gson()
        Kotpref.cipherAdapter = SharedPrefCipherAdapter(applicationContext)
    }
}
```
### Declare preference model
```
    var password by ecStringPref("jafirPass")
    var code1 by ecNullableStringPref()
    var isMan by ecBooleanPref(true)
    var age1 by ecIntPref(23)
    var highScore1 by ecLongPref(1111111111L)
    var rate1 by ecFloatPref(0.5555f)
    var person1 by ecGsonPref(Person("g jafir", 21))
    var avatar21 by ecGsonPref(Avatar())
    var avatar22 by ecGsonNullablePref(Avatar())
```
###  Advanced

If you want to custom your Cipher rules

Just implement CipherAdapter's encrypt and decrypt funs, like:

```
class SharedPrefCipherAdapter @Throws(Exception::class)
constructor(context: Context) : CipherAdapter {
    private val secretKey: SecretKey

    init {
        this.secretKey = AESUtil.generateKey(context)
    }

    override fun encrypt(raw: String): String {
        return AESUtil.execEncrypted(secretKey, raw)
    }

    override fun decrypt(encode: String): String {
        return AESUtil.execDecrypted(secretKey, encode)
    }
}
```
And more detail in the source code, you can see it 


#### Default Encrypt Rules
AES encrypt content, and PBE encrypt secret key 
#### Xml
```xml
<map>
    <long name="highScore" value="3901" />
    <float name="rate" value="0.4" />
    <string name="password">WUb7wV8SS18d9hEvUt8kPg==&#10;    </string>
    <string name="age1">vELsGwmt5Bhz1WkAuasEHA==&#10;    </string>
    <string name="avatar22">rN29eRFNgIlf6yIAV9cptoyabAkqmDqDtf6S4ElzPWIVS1YRMXw2avvYbyJseOZEOBqVE9kAAARV&#10;T4MpZ31fAw==&#10;    </string>
    <string name="avatar1">null</string>
    <string name="avatar21">rN29eRFNgIlf6yIAV9cpttcgywAfWQ9P21mqhkpLjhty0xyusdIZtGLibaD5gzdExLQhyLF2BbIR&#10;Vz7hM0a0KA==&#10;    </string>
    <string name="avatar">{&quot;icon&quot;:&quot;lion&quot;,&quot;updated_at&quot;:&quot;Dec 19, 2017 11:13:28 PM&quot;}</string>
    <string name="person1">gA4aAC4rCqoo9Vz3VCBgVtnerDMep/WhMsoUK736qJ4=&#10;    </string>
    <string name="code">451B65F6-EF95-4C2C-AE76-D34535F51B3B</string>
    <string name="isMan">gi8S6qu7Sklcx0oiYDGnsw==&#10;    </string>
    <set name="prizes">
        <string>New Born</string>
    </set>
    <int name="age" value="2" />
    <string name="highScore1">L5E9zu5NO5AQGFVZBmmHTA==&#10;    </string>
    <string name="name">chibatching Jr</string>
    <string name="rate1">Pa0WPZj6Of7DV8S4LYfp2g==&#10;    </string>
    <string name="code1">FZbcxuspUwL0HUdYvuQ6ltT/nWL+e5d3ZXtfTfPXGcccThyKavFb+7iB1bR8PGF6&#10;    </string>
    <string name="gameLevel">EASY</string>
</map>

```
