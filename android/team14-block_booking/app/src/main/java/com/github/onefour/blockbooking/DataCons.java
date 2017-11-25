package com.github.onefour.blockbooking;

import android.os.Environment;

/**
 * Created by shop on 11/07/2017.
 */

public class DataCons {
    public static final String APP_NAME = "Chemoscope";
    public static final String FILE_NAME = "strigix_chemoscope_alpha.db";

    public static final String APP_PATH = Environment.getExternalStorageDirectory().toString() + "/"+APP_NAME+"/";

    public static final String DATABASE_NAME = "privacy.db";
    public static final String DATABASE_TABLE = "privacy";
    public static final String ID = "id";
    public static final String PASSWORD = "password";
    public static final String WALLET_NAME = "walletname";

}
