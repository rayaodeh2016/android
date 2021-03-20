package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Data;
import Model.Food;
import Utils.Utils;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Utils.RollTable + " ("
                + Utils.Roll_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Utils.Roll_NAME + " TEXT"
                + ")");


        db.execSQL("CREATE TABLE " + Utils.TABLE_NAME + " ("
                + Utils.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Utils.COL_NAME + " TEXT UNIQUE, "
                + Utils.COL_EMAIL + " TEXT UNIQUE ,"
                + Utils.COL_PASSWORD + " TEXT,"
                + Utils.Roll + " TEXT" + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.RollTable);

        onCreate(db);
    }

    public long addUser(Data user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COL_NAME, user.getName());
        values.put(Utils.COL_EMAIL, user.getEmail());
        values.put(Utils.COL_PASSWORD, user.getPassword());
        values.put(Utils.Roll, "User");
        // Inserting Row
        long id = db.insert(Utils.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public long addUser1(Data user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COL_NAME, user.getName());
        values.put(Utils.COL_EMAIL, user.getEmail());
        values.put(Utils.COL_PASSWORD, user.getPassword());
        values.put(Utils.Roll, user.getRoll());
        // Inserting Row
        long id = db.insert(Utils.TABLE_NAME, null, values);
        db.close();

        return id;
    }


    public Data getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Utils.TABLE_NAME,
                new String[]{Utils.COL_ID,
                        Utils.COL_NAME, Utils.COL_EMAIL
                        , Utils.COL_PASSWORD,
                        Utils.Roll},
                Utils.COL_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Data data = new Data(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
        );

        return data;
    }

    public String  getRoll(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Utils.TABLE_NAME,
                new String[]{
                        Utils.Roll},
                Utils.COL_EMAIL + "=?", new String[]{email},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();



        return cursor.getString(cursor.getColumnIndex(Utils.Roll));
    }

    public String  getName(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Utils.TABLE_NAME,
                new String[]{
                        Utils.COL_EMAIL},
                Utils.COL_EMAIL + "=?", new String[]{email},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();



        return cursor.getString(cursor.getColumnIndex(Utils.COL_NAME));
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */

    public List<Data> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                Utils.COL_ID,
                Utils.COL_EMAIL,
                Utils.COL_NAME,
                Utils.COL_PASSWORD,
                Utils.Roll
        };
        // sorting orders
        String sortOrder =
                Utils.COL_NAME + " ASC";
        List<Data> userList = new ArrayList<Data>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table

        Cursor cursor = db.query(Utils.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data user = new Data();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Utils.COL_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(Utils.COL_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(Utils.COL_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(Utils.COL_PASSWORD)));
                user.setRoll(cursor.getString(cursor.getColumnIndex(Utils.Roll)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }

    public List<Data> getAllCustomer(String roll) {
        // array of columns to fetch
        String[] columns = {
                Utils.COL_ID,
                Utils.COL_EMAIL,
                Utils.COL_NAME,
                Utils.COL_PASSWORD,
                Utils.Roll
        };
        // sorting orders
        String sortOrder =
                Utils.COL_NAME + " ASC";
        List<Data> userList = new ArrayList<Data>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table

        Cursor cursor = db.query(Utils.TABLE_NAME, //Table to query
                columns,    //columns to return
                Utils.Roll + "=?",
                new String[]{roll}   ,     //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data user = new Data();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Utils.COL_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(Utils.COL_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(Utils.COL_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(Utils.COL_PASSWORD)));
                user.setRoll(cursor.getString(cursor.getColumnIndex(Utils.Roll)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }


    public void updateUser(Data user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COL_NAME, user.getName());
        values.put(Utils.COL_EMAIL, user.getEmail());
        values.put(Utils.COL_PASSWORD, user.getPassword());
        values.put(Utils.Roll, user.getRoll());

        // updating row
        db.update(Utils.TABLE_NAME, values, Utils.COL_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();

    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(Utils.TABLE_NAME, Utils.COL_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();
    }


    public boolean checkUser(String email) {
        // array of columns to fetch
        String[] columns = {
                Utils.COL_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = Utils.COL_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};

        Cursor cursor = db.query(Utils.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /*public void deleteUser(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM " + Utils.TABLE_NAME + " WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);

        statement.execute();
        database.close();
    }*/


    /**
     * This method to check user exist or not
     */
    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                Utils.COL_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = Utils.COL_EMAIL + " = ?" + " AND " + Utils.COL_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions

        Cursor cursor = db.query(Utils.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

}