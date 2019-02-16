package com.example.administrator.demo_zkl01.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.administrator.demo_zkl01.startfragmentpage.homepage.bean.DaoSearch;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAO_SEARCH".
*/
public class DaoSearchDao extends AbstractDao<DaoSearch, Long> {

    public static final String TABLENAME = "DAO_SEARCH";

    /**
     * Properties of entity DaoSearch.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CommodityName = new Property(1, String.class, "commodityName", false, "COMMODITY_NAME");
        public final static Property MasterPic = new Property(2, String.class, "masterPic", false, "MASTER_PIC");
        public final static Property Price = new Property(3, int.class, "price", false, "PRICE");
        public final static Property SaleNum = new Property(4, int.class, "saleNum", false, "SALE_NUM");
    }


    public DaoSearchDao(DaoConfig config) {
        super(config);
    }
    
    public DaoSearchDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAO_SEARCH\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"COMMODITY_NAME\" TEXT," + // 1: commodityName
                "\"MASTER_PIC\" TEXT," + // 2: masterPic
                "\"PRICE\" INTEGER NOT NULL ," + // 3: price
                "\"SALE_NUM\" INTEGER NOT NULL );"); // 4: saleNum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAO_SEARCH\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DaoSearch entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String commodityName = entity.getCommodityName();
        if (commodityName != null) {
            stmt.bindString(2, commodityName);
        }
 
        String masterPic = entity.getMasterPic();
        if (masterPic != null) {
            stmt.bindString(3, masterPic);
        }
        stmt.bindLong(4, entity.getPrice());
        stmt.bindLong(5, entity.getSaleNum());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DaoSearch entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String commodityName = entity.getCommodityName();
        if (commodityName != null) {
            stmt.bindString(2, commodityName);
        }
 
        String masterPic = entity.getMasterPic();
        if (masterPic != null) {
            stmt.bindString(3, masterPic);
        }
        stmt.bindLong(4, entity.getPrice());
        stmt.bindLong(5, entity.getSaleNum());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DaoSearch readEntity(Cursor cursor, int offset) {
        DaoSearch entity = new DaoSearch( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // commodityName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // masterPic
            cursor.getInt(offset + 3), // price
            cursor.getInt(offset + 4) // saleNum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DaoSearch entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCommodityName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMasterPic(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPrice(cursor.getInt(offset + 3));
        entity.setSaleNum(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DaoSearch entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DaoSearch entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DaoSearch entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
