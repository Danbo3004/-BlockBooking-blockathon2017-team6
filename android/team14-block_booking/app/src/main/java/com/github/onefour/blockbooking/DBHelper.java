package com.github.onefour.blockbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;

/**
 * Created by shop on 11/07/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private String DATABASE_PATH;
    private SQLiteDatabase sqLiteDatabase = null;
    private final Context mContext;
    private String password, walletName;

    public DBHelper(Context context, String name) {
        super(context, name,null,1);
        this.DATABASE_PATH = (context.getDatabasePath(name)).getPath();
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DataCons.DATABASE_TABLE + "("
                + DataCons.ID + " INTEGER PRIMARY KEY," + DataCons.PASSWORD + " TEXT,"
                + DataCons.WALLET_NAME + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openIt() throws IOException{
        this.getReadableDatabase();
        sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH,null,SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase!=null){
            sqLiteDatabase.close();
        }
        super.close();
    }
    // Adding new password
    public void addWallet(String password,String walletName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataCons.PASSWORD, password); // Contact pass
        values.put(DataCons.WALLET_NAME, walletName); // Contact walletname

        // Inserting Row
        db.insert(DataCons.DATABASE_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public String getPassword(){
        String p = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+DataCons.PASSWORD+", "+DataCons.WALLET_NAME+" FROM "+ DataCons.DATABASE_TABLE
                +" WHERE ID = '"+1+"'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do{
                p = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return p;
    }
    public String getWalletName(){
        String w = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+DataCons.PASSWORD+", "+DataCons.WALLET_NAME+" FROM "+ DataCons.DATABASE_TABLE
                +" WHERE ID = '"+1+"'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do{
                w = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return w;
    }
}
