package com.ed.btscanner.database;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ed.btscanner.database.model.RSSIEntryModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ehsan Waris on 4/20/2020.
 */

public class BTScannerDatabase {

    String TAG = "BTScannerDatabase" ;
    public static final String Table = "rssi_test";

    public static final String ID = "id";

    public static final String TEST_NAME = "test_name";

    public static final String TIME_STAMP = "time_stamp";

    public static final String RSSI_VALUE = "rssi_value";

    public static final String DISTANCE = "distance";

    public static final String ORIENTATION = "orientation";

    public static final String DEVICE_NAME = "device_name";


    public void CreateTable(SQLiteDatabase db) {

        String query = "CREATE TABLE " + Table +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                "," + TEST_NAME + " text" +
                "," + TIME_STAMP + " INTGER" +
                "," + RSSI_VALUE + " INTGER" +
                "," + DISTANCE + " REAL" +
                "," + ORIENTATION + " text" +
                "," + DEVICE_NAME + " text" +
                ")";

        db.execSQL(
                query
        );

        Log.i(TAG,query);
    }



//    public void AddProductItem(SQLiteDatabase db, SubSubMainMenu product)
//    {
//
//        ItemDb itemDb= new ItemDb();
//        for(ItemsDto item : product.Items)
//        {
//            item.pID = product.id;
//            itemDb.AddItem(db,item);
//        }
//    }


    public long addEntry(SQLiteDatabase db, RSSIEntryModel entry) {
        long i = -1;


        Log.i(TAG,"Adding entry");
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEST_NAME, entry.getTest_name());
        contentValues.put(TIME_STAMP, entry.getTime_stamp());
        contentValues.put(RSSI_VALUE, entry.getRssi_value());
        contentValues.put(ORIENTATION, entry.getOrientation());
        contentValues.put(DISTANCE, entry.getDistance());
        contentValues.put(DEVICE_NAME, entry.getDevice_name());


        i = db.insert(Table, null, contentValues);


        Log.i(TAG, i + "");
        return i;
    }

    public List<RSSIEntryModel> GETALLEntries(SQLiteDatabase db) {
        Cursor res = db.rawQuery("SELECT * FROM " + Table , null);
        res.moveToFirst();
        List<RSSIEntryModel> entries= new ArrayList<>();
        while(res.moveToNext()){
        if (res.isAfterLast() == false) {
            RSSIEntryModel entry = new RSSIEntryModel();

            entry.setId(res.getInt(res.getColumnIndex(this.ID)));
            entry.setDevice_name(res.getString(res.getColumnIndex(this.DEVICE_NAME)));
            entry.setDistance(res.getDouble(res.getColumnIndex(this.DISTANCE)));
            entry.setOrientation(res.getString(res.getColumnIndex(this.ORIENTATION)));
            entry.setRssi_value(res.getInt(res.getColumnIndex(this.RSSI_VALUE)));
            entry.setTime_stamp(res.getInt(res.getColumnIndex(this.TIME_STAMP)));
            entry.setTest_name(res.getString(res.getColumnIndex(this.TEST_NAME)));


            entries.add(entry);
        }
        }
        return entries;
    }
//
//
//    public boolean UpdateProductById(SQLiteDatabase db, Product product) {
//
//
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(name, product.getname());
//        contentValues.put(addedname, product.getAddedname());
//        contentValues.put(productName, product.getName());
//        contentValues.put(description, product.getDescription());
//        contentValues.put(url, product.getImage());
//        contentValues.put(categoryName, product.getCategoryName());
//        contentValues.put(productId, product.getProductId());
//        contentValues.put(upc, product.getUpc());
//        contentValues.put(price, product.getPrice());
//        contentValues.put(condition, product.getCondition());
//
//        Log.i(testTag,product.getAddedname()+" name");
//        db.update(Table, contentValues, this.id + " = ? ", new String[]{Integer.toString(product.id)});
////        Log.i("AlarmsValueShowing", "Updated" + productDb.Hive_ID);
//
//        return true;
//    }
//
//
//    String testTag = "storedProduct";
//    public ArrayList<Product> GetAllProduct(SQLiteDatabase db) {
//        ArrayList<Product> array_list = new ArrayList<>();
//        Cursor res = db.rawQuery("select * from " + Table + "", null);
//        res.moveToFirst();
//        ItemDb itemDb= new ItemDb();
//
//        while (res.isAfterLast() == false) {
//            Product product = new Product();
//
//
//            product.id = res.getInt(res.getColumnIndex(this.id));
//            product.setname( res.getInt(res.getColumnIndex(name)));
//            product.setAddedQuantiy( res.getInt(res.getColumnIndex(addedname)));
//            product.name = res.getString(res.getColumnIndex(productName));
//            product.description = res.getString(res.getColumnIndex(description));
//            product.image = res.getString(res.getColumnIndex(url));
//            product.categoryName = res.getString(res.getColumnIndex(categoryName));
//            product.productId = res.getString(res.getColumnIndex(productId));
//            product.upc = res.getString(res.getColumnIndex(upc));
//            product.setPrice(res.getString(res.getColumnIndex(price)));
//            product.condition = res.getString(res.getColumnIndex(condition));
//
//
//            product.Items = itemDb.GetAllProductItems(db,product.id);
//
//            array_list.add(product);
//            res.moveToNext();
//        }
//        return array_list;
//    }
//
//    public boolean DeleteProductById(SQLiteDatabase db, int id)
//    {
//        ItemDb itemDb= new ItemDb();
//        itemDb.DeleteAllProductItems(db,id);
//        return db.delete(Table, this.id + "=" + id, null) > 0;
//    }
}
