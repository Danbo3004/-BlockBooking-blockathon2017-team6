package com.github.onefour.blockbooking;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

public class Detail extends AppCompatActivity{
    private Button basic;
    private final static String greeterContractAddressRopsten = "0x024b64940518779068e57352F3bDDdE08E4D9c40";
    private int minimumGasLimit = 21000000;
    private BigInteger gasLimit = new BigInteger(String.valueOf(minimumGasLimit));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Navy Flowers Hotel");
        setSupportActionBar(toolbar);

        final Web3j web3j = Web3jFactory.build(new HttpService("http://www.blockathon.asia:8545/"));
        //final Web3j web3j = Web3jFactory.build(new HttpService("https://ropsten.infura.io/cn5K9sjky3BpCrTPxnru"));

        View includedLayout = findViewById(R.id.news_title);

        basic = (Button)includedLayout.findViewById(R.id.basic);
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Detail.this,"basic clicked!"+"",Toast.LENGTH_SHORT).show();
                //DBHelper d = new DBHelper(Detail.this, DataCons.DATABASE_NAME);
                File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/me");
                if (!path.exists()) {
                    path.mkdir();
                }
                Greeter_sol_greeter greeter = null;
                try {
                    greeter = Greeter_sol_greeter.load(greeterContractAddressRopsten, web3j, WalletUtils.loadCredentials(
                            "987412365",
                            path+"/me/UTC--2017-11-25T09-47-35.515Z--199757fcaacd6877400b94de949f92b24df333a1")
                            , getGasPrice(), gasLimit);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CipherException e) {
                    e.printStackTrace();
                }
                RemoteCall<TransactionReceipt> transactionReceipt = null;
                    transactionReceipt = greeter.newGreeting("unfuckable");
                try {
                    Toast.makeText(Detail.this,transactionReceipt.send().getFrom()+"ABC",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                /**Greeter_sol_greeter greeter = null;
                try {
                    greeter = Greeter_sol_greeter.load(greeterContractAddressRopsten, web3j, WalletUtils.loadCredentials(
                            d.getPassword(),
                            d.getWalletName()), getGasPrice(), gasLimit);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CipherException e) {
                    e.printStackTrace();
                }
                RemoteCall<String> greeting = greeter.greet();
                //Utf8String greetingUtf8 = null;
                Toast.makeText(Detail.this,greeting+"ABC",Toast.LENGTH_LONG).show();*/
                }

        });


    }

    private BigInteger getGasPrice() {
        int gasPriceGwei = 7500000;
        BigInteger gasPriceWei = BigInteger.valueOf(gasPriceGwei + 1000000000L);
        Log.d("wat", "getGasPrice: " + String.valueOf(gasPriceGwei));
        return gasPriceWei;
    }
}
