package com.itri.sqliteinsertsearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHP extends SQLiteOpenHelper {
    public DBHP( Context context) {
        super(context, "Gjun", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE teacher(id TEXT, name TEXT, phoneNo TEXT, address TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //database version upgrade
        db.execSQL("DROP TABLE IF EXISTS teacher;");
        onCreate(db);
    }
    public void  insertDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO teacher VALUES('1', 'Derek', '0912345678', 'Taipei');");
        db.execSQL("INSERT INTO teacher VALUES('2', 'Merry', '0912345678', 'Taipei');");
        db.execSQL("INSERT INTO teacher VALUES('3', 'Tom', '0912345678', 'Hsinchu');");
        db.execSQL("INSERT INTO teacher VALUES('4', 'Jerry', '0912345678', 'Taipei');");
        db.execSQL("INSERT INTO teacher VALUES('5', 'Angus', '0912345678', 'Taichung');");
    }
    public void insertDB(String id, String name, String phoneNo, String address){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO teacher VALUES(' "+id+" ', ' "+name+"', ' "+phoneNo+"', ' "+address+" ');");
    }

    public long insertDBMethod(DbData data){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", data.getId());
        values.put("name", data.getName());
        values.put("phoneNo", data.getPhoneNo());
        values.put("address", data.getAddress());
        long rowid = db.insert("teacher", null, values);
        return rowid;
    }

    public void updateDB(DbData data){
        SQLiteDatabase db = getWritableDatabase();
        String id = data.getId();
        String name = data.getName();
        String phoneNo = data.getPhoneNo();
        String address = data.getAddress();
        db.execSQL("UPDATE teacher SET id ='"+id+"',name = '"+name+"',phoneNo = '"+phoneNo+"',address = '"+address+"'  WHERE id = '"+id+"';");
    }

    public void updateDB(String id, String name, String phoneNo, String address){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE teacher SET id ='"+id+"',name = '"+name+"',phoneNo = '"+phoneNo+"',address = '"+address+"'  WHERE id = '"+id+"';");
    }
    public int  updateMethod(DbData data){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", data.getId());
        values.put("name", data.getName());
        values.put("phoneNo", data.getPhoneNo());
        values.put("address", data.getAddress());
        String whereClause = "id = '" + data.getId()+"' ";
        int count = db.update("teacher", values, whereClause, null);
        return count;
    }

    public  void deleteDB(DbData data){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM teacher WHERE id='"+data.getId()+"';");
    }

    public  void deleteDB(String address){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM teacher WHERE address='"+address+"';");
    }

    public int deleteDBMethod(DbData data){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "name = '"+data.getName()+"' ";
        int count = db.delete("teacher", whereClause,null);
        return count;
    }
    public void insertDB(DbData data){
        SQLiteDatabase db = getWritableDatabase();
        String id = data.getId();
        String name = data.getName();
        String phoneNo = data.getPhoneNo();
        String address = data.getAddress();
        db.execSQL("INSERT INTO teacher VALUES(' "+id+" ', ' "+name+"', ' "+phoneNo+"', ' "+address+" ');");
    }



    public ArrayList<String> queryDB(){
           SQLiteDatabase db =  getWritableDatabase();
           Cursor cursor =  db.rawQuery("SELECT * FROM teacher;", null);
           ArrayList<String> list = new ArrayList<String>();
           while(cursor.moveToNext()){
               int num = cursor.getColumnCount();
               String msgString = "";
               for (int i = 0; i < num; i++) {
                    msgString = msgString + cursor.getString(i) + "\t";
               }
               list.add(msgString);
           }
           return list;
    }

    public ArrayList<DbData> queryALL(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM teacher;", null);
        ArrayList<DbData> list = new ArrayList<DbData>();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String address = cursor.getString(3);
            DbData data = new DbData(id, name, phone, address);
            list.add(data);
        }
        return list;
    }

    public ArrayList<DbData>keyWordQuery(String keyword){
        SQLiteDatabase db = getWritableDatabase();
        String selectionArgs[] = {"%"+keyword+"%"};
        Cursor cursor =  db.rawQuery("SELECT * FROM teacher WHERE address LIKE ?;", selectionArgs);
        ArrayList<DbData> list = new ArrayList<DbData>();
        while(cursor.moveToNext()){
            String id =  cursor.getString(0);
            String name =  cursor.getString(1);
            String phoneNo =  cursor.getString(2);
            String address =  cursor.getString(3);
            DbData data =  new DbData(id, name, phoneNo, address);
            list.add(data);
        }
        return  list;
    }
    public ArrayList<DbData> keyWordQuery(DbData data){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<DbData> list = new ArrayList<>();
        String columns[] = {"id", "name", "phoneNo", "address"};
        String selection = "address= '"+data.getAddress()+"' ";
        String orderBy = "id";
        Cursor cursor =  db.query("teacher", columns, selection, null, null, null, orderBy);
        while(cursor.moveToNext()){
            String s[] = new String[cursor.getColumnCount()];
            for(int i = 0; i < cursor.getColumnCount(); i++){
                s[i] = cursor.getString(i);
            }
            DbData dbData =  new DbData(s[0], s[1], s[2], s[3]);
            list.add(dbData);
        }
        return list;
    }

    public ArrayList<DbData> queryAll(Context context){
        SQLiteDatabase db = context.openOrCreateDatabase("YPU.db", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM teacher;", null);
        ArrayList<DbData> list = new ArrayList<>();
        while(cursor.moveToNext()){
            String s[] = new String[cursor.getColumnCount()];
            for(int i = 0; i < cursor.getColumnCount(); i++){
                s[i] = cursor.getString(i);
            }
            DbData dbData =  new DbData(s[0], s[1], s[2], s[3]);
            list.add(dbData);
        }
        return list;
    }
}
