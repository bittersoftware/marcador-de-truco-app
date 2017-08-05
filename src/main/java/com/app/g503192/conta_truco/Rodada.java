package com.app.g503192.conta_truco;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by G503192 on 24-Jul-17.
 */

public class Rodada implements Parcelable  {

   private int mRound;
   private int mScore1;
   private int mScore2;
   private int mVictory1;
   private int mVictory2;
   private String mTeam1;
   private String mTeam2;


    public Rodada(Parcel input) {

        mRound = input.readInt();
        mScore1 = input.readInt();
        mScore2 = input.readInt();
        mVictory1 = input.readInt();
        mVictory2 = input.readInt();
        mTeam1 = input.readString();
        mTeam2 = input.readString();

    }


    public Rodada(int Round, int Score1, int Score2, int Victory1, int Victory2, String Team1, String Team2 ){
        mRound = Round;
        mScore1 = Score1;
        mScore2 = Score2;
        mVictory1 = Victory1;
        mVictory2 = Victory2;
        mTeam1 = Team1;
        mTeam2 = Team2;
    }



    public int getRound (){
        return  mRound;
    }


    public int getmScore1 (){
        return  mScore1;
    }

    public int getmScore2 (){
        return  mScore2;
    }

    public int getVictory1 (){
        return  mVictory1;
    }

    public int getVictory2 (){
        return  mVictory2;
    }

    public String getTeam1 () { return mTeam1;}

    public String getTeam2 () { return mTeam2;}

    public void setTeam1 (String Team1) { mTeam1 = Team1;}

    public void setTeam2 (String Team2) { mTeam2 = Team2;}



    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRound);
        dest.writeInt(mScore1);
        dest.writeInt(mScore2);
        dest.writeInt(mVictory1);
        dest.writeInt(mVictory2);
        dest.writeString(mTeam1);
        dest.writeString(mTeam2);
    }

    public static final Parcelable.Creator<Rodada> CREATOR = new Parcelable.Creator<Rodada>() {
        public Rodada createFromParcel(Parcel in) {
            return new Rodada(in);
        }
        public Rodada[] newArray(int size) {
            return new Rodada[size];
        }
    };


    }


