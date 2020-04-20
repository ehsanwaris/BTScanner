package com.ed.btscanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ed.btscanner.database.model.RSSIEntryModel;

import java.util.List;


/**
 * Created by Ehsan Waris on 4/20/2020.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "btscanner.db";
    public static final int Version = 2;
    public static final String TAG = "DB";

    private static DBHelper mInstance = null;
    public static DBHelper get(Context context){
        if(mInstance == null) mInstance = new DBHelper(context.getApplicationContext());
        return mInstance;
    }
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, Version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BTScannerDatabase.Table);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        btScannerDatabase.CreateTable(db);
    }


    protected final BTScannerDatabase btScannerDatabase = new BTScannerDatabase();

    public long AddRSSIEntry(RSSIEntryModel entryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        long i = btScannerDatabase.addEntry(db, entryModel);
        Log.i(TAG, i + "");
        return i;
    }
    public List<RSSIEntryModel> GetAllRSSIEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
       List<RSSIEntryModel> entryModelList = btScannerDatabase.GETALLEntries(db);
        return entryModelList;
    }
//    public void AddProductItem(Product product) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        productDb.AddProductItem(db, product);
//    }
////

////
//    public boolean UpdateProductById(Product product) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return productDb.UpdateProductById(db, product);
//
//    }
////
//    public ArrayList<Product> GetAllProduct() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return productDb.GetAllProduct(db);
//    }
//    public boolean DeleteProductById(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return productDb.DeleteProductById(db, id);
//    }
//    public boolean DeleteItemByProductId(String productId) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        ItemDb itemDb = new ItemDb();
//
//        return itemDb.DeleteAllProductItems(db, productId);
//    }



}
