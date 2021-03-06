/*
 * Really Simple Context Middleware (RSCM)
 *
 * Copyright (c) 2012-2013 The RSCM Team
 *
 * This file is part of the RSCM: the Really Simple Context Middleware for ANDROID. More information about the project
 * is available at: http://code.google.com/p/rscm
 *
 * The RSCM is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any
 * later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this software.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package org.aspectsense.rscm.runtime.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * User: Nearchos Paspallis
 * Date: 10/8/11
 * Time: 12:54 PM
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper
{
    public static final String TAG = "org.aspectsense.rscm.runtime.db.DatabaseOpenHelper";

    public DatabaseOpenHelper(final Context context, final String databaseName, final int version)
    {
        super(context, databaseName, null, version);
    }

    public static final String DB_CREATE_CONTEXT_TABLE =
            "CREATE TABLE " + DatabaseMetadata.ContextValuesTableMetadata.TABLE_NAME + " (" +
                    DatabaseMetadata.ContextValuesTableMetadata._ID + " INTEGER PRIMARY KEY, " +
                    DatabaseMetadata.ContextValuesTableMetadata.TIMESTAMP + " INTEGER NOT NULL, " +
                    DatabaseMetadata.ContextValuesTableMetadata.SCOPE + " TEXT, " +
                    DatabaseMetadata.ContextValuesTableMetadata.VALUE_AS_JSON + " TEXT);";

    @Override public void onCreate(final SQLiteDatabase db)
    {
        db.execSQL(DB_CREATE_CONTEXT_TABLE);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseMetadata.ContextValuesTableMetadata.TABLE_NAME);

        this.onCreate(db);
    }
}