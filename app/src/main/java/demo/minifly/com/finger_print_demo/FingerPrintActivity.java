package demo.minifly.com.finger_print_demo;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import demo.minifly.com.R;
import demo.minifly.com.request_demo.BaseActivity;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerPrintActivity extends BaseActivity {

    private KeyGenerator mKeyGenerator ;
    public final static String KEY_NAME = "auther";


    FingerprintManager manager;
    KeyguardManager mKeyManager;
    KeyStore mKeyStore;
    Cipher mCipher;

    private final static int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 0;
    private final static String TAG = "finger_log";

    private TextView textView;
    private Button btn_finger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        initView();
    }

    public void initView() {
        manager = (FingerprintManager) this.getSystemService(Context.FINGERPRINT_SERVICE);


        InvocationHandler invocationHandler;
        Proxy poxy;


        //获取钥匙管理者
        mKeyManager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);

        //找到按钮控件
        Button btn_finger = (Button) findViewById(R.id.btn_activity_main_finger);
        textView = (TextView) findViewById(R.id.finger_print_txt);

        /**
         * 创建非对称加密
         */
        createKey();


        //为按钮设置点击事件
        btn_finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinger()) {
                    Toast.makeText(FingerPrintActivity.this, "请进行指纹识别", Toast.LENGTH_LONG).show();
                    /**
                     * 使用非对称加密进行
                     */
                    startListening(new FingerprintManager.CryptoObject(mCipher));
                } else{
                    textView.setText("您的手机暂不支持指纹识别");
                    return;
                }
            }
        });

    }

    /**
     * 产生秘钥对
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createKey() {
        try {
            // 创建KeyGenerator对象
            mKeyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            mKeyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    // 设置需要用户验证
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            // 生成key
            mKeyGenerator.generateKey();
        } catch (NoSuchProviderException | NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
        /**
         * 存放keystore
         */
        if (mKeyStore == null) {
            try {
                mKeyStore = KeyStore.getInstance("AndroidKeyStore");
                mKeyStore.load(null);

                SecretKey key = (SecretKey) mKeyStore.getKey(KEY_NAME, null);
                mCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                mCipher.init(Cipher.ENCRYPT_MODE, key);

                Log.e(TAG,"成功的创建了一个keystore");
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
    }

    /**  设备条件判断
     - 设备是否支持指纹识别
     - 设备是否处于安全保护中（有指纹识别的手机，在使用指纹识别的时候，还需要强制设置密码或者图案解锁，如果未设置的话是不许使用指纹识别的）
     - 设备是否已经注册过指纹（如果用户未使用过这个指纹技术，那么只能提示用户到系统设置里面去设置）指纹识别API调用**/
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isFinger() {
        //判断当前手机版本
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "没有指纹识别权限", Toast.LENGTH_SHORT).show();
                return false;
            }

            Log(TAG, "有指纹权限");

            //判断硬件是否支持指纹识别
            if (!manager.isHardwareDetected()) {
                Toast.makeText(this, "没有指纹识别模块", Toast.LENGTH_SHORT).show();
                return false;
            }

            Log(TAG, "有指纹模块");

            //判断 是否开启锁屏密码

            if (!mKeyManager.isKeyguardSecure()) {
                Toast.makeText(this, "没有开启锁屏密码", Toast.LENGTH_SHORT).show();
                return false;
            }

            Log(TAG, "已开启锁屏密码");

            //判断是否有指纹录入
            if (!manager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "没有录入指纹", Toast.LENGTH_SHORT).show();
                return false;
            }

            Log(TAG, "已录入指纹");
            return true;
    }


    /**该对象提供了取消操作的能力。创建该对象也很简单，使用 new CancellationSignal() 就可以了。**/
    CancellationSignal mCancellationSignal = new CancellationSignal();


    /**回调方法**/
    FingerprintManager.AuthenticationCallback mSelfCancelled = new FingerprintManager.AuthenticationCallback() {
        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            // 验证出错回调 指纹传感器会关闭一段时间,在下次调用authenticate时,会出现禁用期(时间依厂商不同30,1分都有)

            Toast.makeText(FingerPrintActivity.this, errString, Toast.LENGTH_SHORT).show();
            showAuthenticationScreen();
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            // 验证帮助回调
            Toast.makeText(FingerPrintActivity.this, helpString, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {  //验证成功
            /**
             * 验证成功只是说已经在手机中有了这个指纹库而已，不能说就是可以在后台验证通过了。
             */
//            Toast.makeText(FingerPrintActivity.this, "指纹识别成功 parameter:" + result.toString(), Toast.LENGTH_SHORT).show();

            try {
                FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
                Method method = FingerprintManager.class.getDeclaredMethod("getEnrolledFingerprints");
                Object obj = method.invoke(fingerprintManager);
                if (obj != null) {
                    Class<?> clazz = Class.forName("android.hardware.fingerprint.Fingerprint");
                    Method getFingerId = clazz.getDeclaredMethod("getFingerId");
                    for (int i = 0; i < ((List) obj).size(); i++) {
                        Object item = ((List) obj).get(i);
                        if (null == item) {
                            continue;
                        }

                        Log.d(TAG, "fingerId: " + getFingerId.invoke(item));
                        Toast.makeText(FingerPrintActivity.this, "指纹识别成功 fingerid:" + getFingerId.invoke(item), Toast.LENGTH_SHORT).show();
                    }

                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onAuthenticationFailed() {
            // 验证失败  指纹验证失败后,指纹传感器不会立即关闭指纹验证,系统会提供5次重试的机会,即调用5次onAuthenticationFailed后,才会调用onAuthenticationError

            Toast.makeText(FingerPrintActivity.this, "指纹识别失败", Toast.LENGTH_SHORT).show();
        }
    };


    /**如果支持一系列的条件，可以认证回调，参数是加密对象**/
    public void startListening(FingerprintManager.CryptoObject cryptoObject) {
        //判断是否添加指纹识别权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "没有指纹识别权限", Toast.LENGTH_SHORT).show();
            return;
        }
        /**参数分别是:防止第三方恶意攻击的包装类,CancellationSignal对象,flags,回调对象,handle**/
        manager.authenticate(cryptoObject, mCancellationSignal, 0, mSelfCancelled, null);
    }

    /**
     * 停止监听
     */
    public void stopListening() {

    }

    /**
     * 如果识别失败次数过多,则转入输入解锁密码界面，
     */
    private void showAuthenticationScreen() {

        Intent intent = mKeyManager.createConfirmDeviceCredentialIntent("finger", "测试指纹识别");
        if (intent != null) {
            startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "识别成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "识别失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void Log(String tag, String msg) {
        Log.d(tag, msg);
    }

}
