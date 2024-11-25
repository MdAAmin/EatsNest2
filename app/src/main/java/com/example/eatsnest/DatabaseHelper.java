package com.example.eatsnest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Common Column Names
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";

    // Product Table Column Names
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_PRICE = "price";
    public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
    public static final String COLUMN_PRODUCT_IMAGE = "image";

    // Payment Table Column Names
    public static final String COLUMN_CARD_NUMBER = "card_number";
    public static final String COLUMN_EXPIRY_DATE = "expiry_date";
    public static final String COLUMN_CVV = "cvv";
    public static final String COLUMN_AMOUNT = "amount";

    // Review Table Column Names
    public static final String COLUMN_RESTAURANT_NAME = "restaurant_name";
    public static final String COLUMN_REVIEW = "review";
    public static final String COLUMN_RATING = "rating";

    // Orders Table Column Names
    public static final String COLUMN_ORDER_PRODUCT_NAME = "product_name";
    public static final String COLUMN_ORDER_PRICE = "o_price";
    public static final String COLUMN_ORDER_QUANTITY = "o_quantity";

    // Database Name and Version
    private static final String DATABASE_NAME = "eatsnest.db";
    private static final int DATABASE_VERSION = 4;  // Incremented version number

    // Table Names
    private static final String TABLE_ADMIN = "admin";
    private static final String TABLE_USER = "user";
    private static final String TABLE_PRODUCT = "product";
    private static final String TABLE_PAYMENT = "payment";
    private static final String TABLE_REVIEW = "review";
    private static final String TABLE_ORDERS = "orders";  // Added orders table name

    // Default Admin Credentials
    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "11";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADMIN_TABLE = "CREATE TABLE " + TABLE_ADMIN + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_ADMIN_TABLE);

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PHONE + " TEXT,"
                + COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_PRODUCT_PRICE + " REAL,"
                + COLUMN_PRODUCT_QUANTITY + " INTEGER,"
                + COLUMN_PRODUCT_IMAGE + " BLOB" + ")";
        db.execSQL(CREATE_PRODUCT_TABLE);

        String CREATE_PAYMENT_TABLE = "CREATE TABLE " + TABLE_PAYMENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CARD_NUMBER + " TEXT,"
                + COLUMN_EXPIRY_DATE + " TEXT,"
                + COLUMN_CVV + " TEXT,"
                + COLUMN_AMOUNT + " TEXT" + ")";
        db.execSQL(CREATE_PAYMENT_TABLE);

        String CREATE_REVIEW_TABLE = "CREATE TABLE " + TABLE_REVIEW + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_RESTAURANT_NAME + " TEXT,"
                + COLUMN_REVIEW + " TEXT,"
                + COLUMN_RATING + " INTEGER" + ")";
        db.execSQL(CREATE_REVIEW_TABLE);

        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ORDER_PRODUCT_NAME + " TEXT,"
                + COLUMN_ORDER_PRICE + " REAL,"
                + COLUMN_ORDER_QUANTITY + " INTEGER" + ")";
        db.execSQL(CREATE_ORDERS_TABLE);

        // Add a default admin user
        addAdmin(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_USER + " ADD COLUMN " + COLUMN_EMAIL + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_USER + " ADD COLUMN " + COLUMN_PHONE + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_USER + " ADD COLUMN " + COLUMN_ADDRESS + " TEXT");
        }

        if (oldVersion < 3) {
            db.execSQL("CREATE TABLE " + TABLE_PAYMENT + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_CARD_NUMBER + " TEXT,"
                    + COLUMN_EXPIRY_DATE + " TEXT,"
                    + COLUMN_CVV + " TEXT,"
                    + COLUMN_AMOUNT + " TEXT" + ")");
        }

        if (oldVersion < 4) {
            String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ORDER_PRODUCT_NAME + " TEXT,"
                    + COLUMN_ORDER_PRICE + " REAL,"
                    + COLUMN_ORDER_QUANTITY + " INTEGER" + ")";
            db.execSQL(CREATE_ORDERS_TABLE);
        }
    }

    private void addAdmin(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, DatabaseHelper.DEFAULT_ADMIN_USERNAME);
        values.put(COLUMN_PASSWORD, DatabaseHelper.DEFAULT_ADMIN_PASSWORD);
        db.insert(TABLE_ADMIN, null, values);
    }

    public void addUser(String username, String password, String email, String phone, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_ADDRESS, address);
        db.insert(TABLE_USER, null, values);
    }

    public boolean checkAdmin(String username, String password) {
        return checkCredentials(TABLE_ADMIN, username, password);
    }

    public boolean checkUser(String username, String password) {
        return checkCredentials(TABLE_USER, username, password);
    }

    private boolean checkCredentials(String tableName, String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public void insertProduct(String name, double price, int quantity, byte[] imageByteArray) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, name);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(COLUMN_PRODUCT_IMAGE, imageByteArray);
        db.insert(TABLE_PRODUCT, null, values);
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PRODUCT, null, null, null, null, null, null);
    }

    public Cursor getProductByName(String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_PRICE, COLUMN_PRODUCT_QUANTITY, COLUMN_PRODUCT_IMAGE};
        String selection = COLUMN_PRODUCT_NAME + "=?";
        String[] selectionArgs = {productName};
        return db.query(TABLE_PRODUCT, columns, selection, selectionArgs, null, null, null);
    }

    public boolean deleteProduct(String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCT, COLUMN_PRODUCT_NAME + "=?", new String[]{productName});
        db.close();
        return true;
    }

    public void updateProduct(int id, String name, double price, int quantity, byte[] imageByteArray) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, name);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(COLUMN_PRODUCT_IMAGE, imageByteArray);
        db.update(TABLE_PRODUCT, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public boolean insertReview(String restaurantName, String review, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESTAURANT_NAME, restaurantName);
        values.put(COLUMN_REVIEW, review);
        values.put(COLUMN_RATING, rating);
        db.insert(TABLE_REVIEW, null, values);
        return true;
    }

   

    public void insertOrder(String productName, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_PRODUCT_NAME, productName);
        values.put(COLUMN_ORDER_PRICE, price);
        values.put(COLUMN_ORDER_QUANTITY, quantity);
        db.insert(TABLE_ORDERS, null, values);
    }

    public Cursor getAllOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ORDERS, null, null, null, null, null, null);
    }

    public void insertPayment(String cardNum, String expDate, String cvvCode, String amt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CARD_NUMBER, cardNum);
        values.put(COLUMN_EXPIRY_DATE, expDate);
        values.put(COLUMN_CVV, cvvCode);
        values.put(COLUMN_AMOUNT, amt);
        db.insert(TABLE_PAYMENT, null, values);
        db.close();  // Close the database connection
    }

}
