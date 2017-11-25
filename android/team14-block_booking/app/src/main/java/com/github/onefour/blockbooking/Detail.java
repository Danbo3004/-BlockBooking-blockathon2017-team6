package com.github.onefour.blockbooking;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Purchase_sol_PriceChart;
import com.Purchase_sol_Purchase;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.AdminFactory;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Detail extends AppCompatActivity{
    private Button options,full,basic;
    private int minimumGasLimit = 21000000;
    private BigInteger gasLimit = new BigInteger(String.valueOf(minimumGasLimit));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Navy Flowers Hotel");
        setSupportActionBar(toolbar);
        File pathhh = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +
                "/me/UTC--2017-11-25T09-47-35.515Z--199757fcaacd6877400b94de949f92b24df333a1");
        Toast.makeText(this,pathhh.length()+"",Toast.LENGTH_LONG).show();

        final Admin web3j = AdminFactory.build(new HttpService("http://www.blockathon.asia:8545"));

        //final Web3j web3j = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/cn5K9sjky3BpCrTPxnru"));
        //final Web3j web3j = Web3jFactory.build(new HttpService());

        final View includedLayout = findViewById(R.id.news_title);

        final View signed = includedLayout.findViewById(R.id.done);
        final View radio_quest = includedLayout.findViewById(R.id.radio_quest);
        options = (Button)includedLayout.findViewById(R.id.options);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_quest.setVisibility(View.VISIBLE);
                Toast.makeText(Detail.this,"basic clicked!"+"",Toast.LENGTH_SHORT).show();
                //File pathh = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/me/");
                //if (!pathh.exists()) {
                //    pathh.mkdir();
                //}
                //DBHelper d = new DBHelper(Detail.this, DataCons.DATABASE_NAME);
                Request<?, NewAccountIdentifier> acc =  web3j.personalNewAccount("!@superpassword");
                //PersonalUnlockAccount acc = null;
                //try {
                //    acc = web3j.personalUnlockAccount("5ad558987acfdba1f3e0f7aa4790fe264353b451", "passphrase",BigInteger.valueOf(3600)).send();
                //} catch (IOException e) {
                //    e.printStackTrace();
                //}
                //Credentials credentials=null;
                //try {
                //    credentials = WalletUtils.loadCredentials(
                //            "987412365",
                //            pathh+"/me/UTC--2017-11-25T09-47-35.515Z--199757fcaacd6877400b94de949f92b24df333a1");
                //} catch (IOException e) {
                //    e.printStackTrace();
                //} catch (CipherException e) {
                //    e.printStackTrace();
                //}
                Credentials credentials = Credentials.create("959613eb037fa1f042596d612cc33706ef57394a0838b4d354735c8435c316c1");
                //try {
                //    credentials = WalletUtils.loadCredentials(
                //            d.getPassword(),
                //            d.getWalletName());
                //} catch (IOException e) {
                //    e.printStackTrace();
                //} catch (CipherException e) {
                //    e.printStackTrace();
                //}
                Log.d("ADDRESS_PC",credentials.getAddress());
                RemoteCall<Purchase_sol_PriceChart> priceChart = Purchase_sol_PriceChart.deploy(web3j,credentials,
                        Contract.GAS_PRICE,Contract.GAS_LIMIT,//getGasPrice(),gasLimit,
                        new BigDecimal("5.0E+16").toBigInteger());
                try {
                    Log.d("ADDRESS_PC",priceChart.sendAsync().get().getContractAddress());//getGasPrice()+" "+credentials.getEcKeyPair().getPublicKey());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String adressPC = null;
                try {
                    adressPC = priceChart.send().getContractAddress();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("ADDRESS_PC",adressPC);
                Purchase_sol_PriceChart PC = Purchase_sol_PriceChart.load(adressPC,
                        web3j,credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT);//getGasPrice(),gasLimit);

                Log.d("ADDRESS_PCC1",PC.getContractAddress());

                RemoteCall<Purchase_sol_Purchase> purchaseRC = null;
                try {
                    purchaseRC = Purchase_sol_Purchase.deploy(web3j,credentials,
                            Contract.GAS_PRICE, Contract.GAS_LIMIT, PC.getBasePrice().send(),PC.getContractAddress());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //PC.getContractAddress());

                String adressP = null;
                try {
                    adressP = purchaseRC.send().getContractAddress();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("ADDRESS_PC",adressP+"adressP");
                Purchase_sol_Purchase purchase = Purchase_sol_Purchase.load(adressP//"0x547a84CADE12Bc5d6A9b3e75B9410250F4cEC135"
                        ,web3j,credentials,Contract.GAS_PRICE, Contract.GAS_LIMIT);//getGasPrice(),gasLimit);
                Log.d("ADDRESS_PCC2",purchase.getContractAddress());

                Button r_con = (Button) includedLayout.findViewById(R.id.yes_radio);
                Button r_can = (Button) includedLayout.findViewById(R.id.no_radio);
                r_con.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        radio_quest.setVisibility(View.GONE);
                        signed.setVisibility(View.VISIBLE);
                    }
                });
                r_can.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signed.setVisibility(View.GONE);
                    }
                });
            }

        });
        final View full_quest = includedLayout.findViewById(R.id.full_quest);
        final View basic_quest = includedLayout.findViewById(R.id.basic_quest);
        full = (Button)includedLayout.findViewById(R.id.full);
        basic = (Button)includedLayout.findViewById(R.id.basic);

        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                full_quest.setVisibility(View.VISIBLE);
                Button b_full = (Button)includedLayout.findViewById(R.id.yes_full);
                b_full.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        full_quest.setVisibility(View.GONE);
                        signed.setVisibility(View.VISIBLE);
                    }
                });
                Button b_full_no = (Button)includedLayout.findViewById(R.id.no_full);
                b_full_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        full_quest.setVisibility(View.GONE);
                    }
                });
            }
        });
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basic_quest.setVisibility(View.VISIBLE);
                Button b_basic = (Button)includedLayout.findViewById(R.id.yes_basic);
                b_basic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        basic_quest.setVisibility(View.GONE);
                        signed.setVisibility(View.VISIBLE);
                    }
                });
                Button b_basic_no = (Button)includedLayout.findViewById(R.id.no_basic);
                b_basic_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        basic_quest.setVisibility(View.GONE);
                    }
                });
            }
        });
        final TextView narrator = (TextView)includedLayout.findViewById(R.id.narrator);
        final View booking = includedLayout.findViewById(R.id.booking);
        final Button head = (Button)includedLayout.findViewById(R.id.heading);
        final Button stay = (Button)includedLayout.findViewById(R.id.staying);
        final Button left = (Button)includedLayout.findViewById(R.id.left);
        signed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking.setVisibility(View.GONE);
                narrator.setText("You choose BASIC package!");
                head.setVisibility(View.VISIBLE);
                v.setVisibility(View.GONE);
            }
        });
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                narrator.setText("You are staying here!");
                stay.setVisibility(View.VISIBLE);
                v.setVisibility(View.GONE);
            }
        });
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                narrator.setText("You are leaving now, see you soon!");
                left.setVisibility(View.VISIBLE);
                v.setVisibility(View.GONE);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking.setVisibility(View.VISIBLE);
                narrator.setText("Choose your Block Booking type:");
                v.setVisibility(View.GONE);
            }
        });
    }

    private BigInteger getGasPrice() {
        int gasPriceGwei = 200;
        BigInteger gasPriceWei = BigInteger.valueOf(gasPriceGwei + 100000);
        Log.d("wat", "getGasPrice: " + String.valueOf(gasPriceGwei));
        return gasPriceWei;
    }
}
