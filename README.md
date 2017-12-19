# Kotpref Encrypt Support

Encrypt Support of [Kotpref](https://github.com/chibatching/Kotpref)

This is a support library on basis of Kotpref, you should use this with it.

### Install
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
And more detail in the source code, you can see it 

