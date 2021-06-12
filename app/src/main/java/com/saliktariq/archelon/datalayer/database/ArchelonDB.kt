package com.saliktariq.archelon.datalayer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saliktariq.archelon.datalayer.dao.*
import com.saliktariq.archelon.datalayer.entity.*

@Database(entities = [Authentication::class, Photos::class, ActivitySequence::class, AdultEmergence::class,Location::class,MorningSurvey::class,Nest::class],version = 3, exportSchema = false)
abstract class ArchelonDB: RoomDatabase(){
    abstract val authenticationDao :AuthenticationAccessObject
    abstract val photosDao :PhotosAccessObject
    abstract val activitySequenceDao:ActivitySequenceAccessObject
    abstract val adultEmergenceDao:AdultEmergenceAccessObject
    abstract val locationDao:LocationAccessObject
    abstract val morningSurveyDao:MorningSurveyAccessObject
    abstract val nestDao:NestAccessObject


    companion object{
        @Volatile
        private var INSTANCE: ArchelonDB? = null

        fun getInstance(context: Context): ArchelonDB {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArchelonDB::class.java,
                        "archelon_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
                            }
        }
    }

}