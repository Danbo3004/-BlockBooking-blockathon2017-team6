package com.github.onefour.blockbooking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.math.BigInteger;

public class GenerationActivity extends AppCompatActivity implements com.github.onefour.blockbooking.GenerationContract.View {

    public static final String TAG = GenerationActivity.class.getName();

    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 0;

    private com.github.onefour.blockbooking.GenerationContract.Presenter mWalletPresenter;

    private Button mGenerateWalletButton;

    private String mWalletAddress;

    private EditText mPassword;

    private File path;

    private View create, exist;
    private SQLiteDatabase sqLiteDatabase;
    private int minimumGasLimit = 21000;
    private BigInteger gasLimit = new BigInteger(String.valueOf(minimumGasLimit));

    static GenerationActivity activityA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generation);
        activityA=this;
        Web3j web3j = Web3jFactory.build(new HttpService("http://www.blockathon.asia:8545/"));
        path = getFilesDir();
        create = findViewById(R.id.create);
        exist = findViewById(R.id.exist);

        final TextView wallet1 = (TextView)findViewById(R.id.address);

        //always check for internet:
        int permissionCheck = ContextCompat.checkSelfPermission(GenerationActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    GenerationActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_WRITE_STORAGE);
        }

        File[] contents = path.listFiles();
        if (contents.length<2) {
            //time to create new wallet:
            mPassword = (EditText) findViewById(R.id.password);
            mGenerateWalletButton = (Button) findViewById(R.id.generate_wallet_button);
            mGenerateWalletButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWalletPresenter = new com.github.onefour.blockbooking.GenerationPresenter(GenerationActivity.this,
                            mPassword.getText().toString(), GenerationActivity.this);
                    mWalletPresenter.generateWallet(mPassword.getText().toString());
                    //Intent intent = new Intent(GenerationActivity.this, WalletActivity.class);
                    //intent.putExtra("WalletAddress", mWalletAddress);
                    //startActivity(intent);
                    wallet1.setText(mWalletAddress);
                    create.setVisibility(View.INVISIBLE);
                    exist.setVisibility(View.VISIBLE);
                }
            });
            Button go = (Button)findViewById(R.id.good_to_go_butt);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(GenerationActivity.this, SearchPage.class);
                    startActivity(i);
                }
            });

        } else{
            Intent i = new Intent(GenerationActivity.this, SearchPage.class);
            startActivity(i);
        }   /** //direct to search page:
            create.setVisibility(View.INVISIBLE);
            exist.setVisibility(View.VISIBLE);

            DBHelper d = new DBHelper(this, DataCons.DATABASE_NAME);

            mWalletPresenter = new GenerationPresenter(GenerationActivity.this,
                    d.getPassword(),GenerationActivity.this);
            mWalletPresenter.loadWallet(d.getPassword(),d.getWalletName());
            //Intent intent = new Intent(GenerationActivity.this, WalletActivity.class);
            //intent.putExtra("WalletAddress", mWalletAddress);
            //startActivity(intent);
            wallet.setText(mWalletAddress);
            TextView message = (TextView)findViewById(R.id.mess);
            try {
                Greeter greeter = Greeter.load("0x024b64940518779068e57352F3bDDdE08E4D9c40",web3j,
                        WalletUtils.loadCredentials(
                                d.getPassword(),
                                d.getWalletName()),getGasPrice(),gasLimit);
                //TransactionReceipt transactionReceipt = greeter.changeGreeting(new Utf8String("Unfuckable")).get(3, TimeUnit.MINUTES);
                //Future<Utf8String> greeting = greeter.greet();
                //Utf8String greetingUtf8 = greeting.get();
                //message.setText(greetingUtf8.getValue());
                //String result = greetingUtf8.getValue();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CipherException e) {
                e.printStackTrace();
            }
        }*/

    }

    private BigInteger getGasPrice() {
        //int gasPriceGwei = gasPriceSeekBar.getProgress();
        //BigInteger gasPriceWei = BigInteger.valueOf(gasPriceGwei + 1000000000L);
        //Log.d("wat", "getGasPrice: " + String.valueOf(gasPriceGwei));
        return BigInteger.valueOf(100000 + 1000000000L);
    }

    @Override
    public void setPresenter(com.github.onefour.blockbooking.GenerationContract.Presenter presenter) {
        mWalletPresenter = presenter;
    }

    @Override
    public void showGeneratedWallet(String address) {
        mWalletAddress = address;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_WRITE_STORAGE: {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                } else {
                    //mWalletPresenter.generateWallet(mPassword.getText().toString());
                }
                break;
            }
        }
    }
    public static GenerationActivity getInstance(){
        return   activityA;
    }
}
