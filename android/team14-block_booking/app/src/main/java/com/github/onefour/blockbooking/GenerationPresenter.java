package com.github.onefour.blockbooking;

import android.content.Context;
import android.util.Log;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by onefour on 29.06.17.
 */

public class GenerationPresenter implements com.github.onefour.blockbooking.GenerationContract.Presenter {

    private final com.github.onefour.blockbooking.GenerationContract.View mWalletGenerationView;

    private String mPassword;
    private Context mContext;
    public GenerationPresenter(com.github.onefour.blockbooking.GenerationContract.View walletGenerationView, String password, Context c) {
        mWalletGenerationView = walletGenerationView;
        mPassword = password;
        mContext = c;
    }

    @Override
    public void generateWallet(final String password) {
        String fileName;
        try {
            File path = mContext.getFilesDir();
            if (!path.exists()) {
                path.mkdir();
            }
                fileName = WalletUtils.generateLightNewWalletFile(password, new File(String.valueOf(path)));
                //record by database:
            DBHelper d = new DBHelper(mContext, DataCons.DATABASE_NAME);
            d.addWallet(password,path+ "/" + fileName);
                Log.e("TAG", "generateWallet: " + path+ "/" + fileName);
                Credentials credentials =
                        WalletUtils.loadCredentials(
                                d.getPassword(),
                                d.getWalletName());
                mWalletGenerationView.showGeneratedWallet(credentials.getAddress());
                Log.e("TAG", "generateWallet: " + credentials.getAddress() + " " + credentials.getEcKeyPair().getPublicKey());
            Log.d("ADDRESS_PC",credentials.getAddress() + " "+password);
        } catch (NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | IOException
                | CipherException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadWallet(String password,String walletName) {
        try {
            Credentials credentials =
                    WalletUtils.loadCredentials(
                            password,
                            walletName);
            mWalletGenerationView.showGeneratedWallet(walletName);
            Log.e("TAG", "generateWallet: " + credentials.getAddress() + " " + credentials.getEcKeyPair().getPublicKey());

        } catch ( IOException
                | CipherException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        generateWallet(mPassword);
    }
}
