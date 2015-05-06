package com.example.nicolas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nicolas.model.Club;

import java.util.ArrayList;

/**
 * Created by Nicolas on 02/05/2015.
 */
public class ClubBDD {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "clubs.db";
    private static final String TABLE_CLUB = "clubs";

    private SQLiteDatabase mBdd;
    private BDDSQLite mMyBdd;

    public ClubBDD(Context context)
    {
        this.mMyBdd = new BDDSQLite(context, DATABASE_NAME, null, VERSION);
    }

    public void open()
    {
        mBdd = this.mMyBdd.getWritableDatabase();
    }

    public void close()
    {
        mBdd.close();
    }

    public SQLiteDatabase getDataBase()
    {
        return mBdd;
    }

    public long insertClub(Club club)
    {
        ContentValues values = new ContentValues();

        values.put("name", club.getNom());
        values.put("local", club.getLocal());
        values.put("icon", club.getIcon());
        values.put("website", club.getWebsite());

        return mBdd.insert(TABLE_CLUB, null, values);
    }

    public int clearClub() { return mBdd.delete(TABLE_CLUB, null, null); }

    public int removeClub(int idClub)
    {
        return mBdd.delete(TABLE_CLUB, "id = " + idClub, null);
    }

    public Club getClubByTitle(String name)
    {
        String request = "SELECT id,name,local,icon,website FROM " + TABLE_CLUB +
                " WHERE name like ?";

        Cursor c = mBdd.rawQuery(request, new String[] { name } );

        return cursorToClub(c);
    }

    public ArrayList<Club> getClubs()
    {
        String request = "SELECT id,name,local,icon,website FROM " + TABLE_CLUB + " ORDER BY name ASC";

        ArrayList<Club> clubList = new ArrayList<Club>();

        Cursor c = mBdd.rawQuery(request, null);

        if(c.getCount() == 0)
            return null;

        if(c.moveToFirst()) {
            do {
                Club club = new Club();

                club.setId(c.getInt(0));
                club.setNom(c.getString(1));
                club.setLocal(c.getString(2));
                club.setIcon(c.getInt(3));
                club.setWebsite(c.getString(4));

                clubList.add(club);
            }
            while(c.moveToNext());
        }

        c.close();

        return clubList;
    }

    public Club cursorToClub(Cursor c)
    {
        if(c.getCount() == 0)
            return null;

        c.moveToFirst();

        Club club = new Club();

        club.setId(c.getInt(0));
        club.setNom(c.getString(1));
        club.setLocal(c.getString(2));
        club.setIcon(c.getInt(3));
        club.setWebsite(c.getString(4));

        c.close();

        return club;
    }
}
