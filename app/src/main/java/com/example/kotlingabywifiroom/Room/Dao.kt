package com.example.kotlingabywifiroom.Room

//import com.example.kotlingabywifiroom.Parent.Items
//import com.example.kotlingabywifiroom.Parent.Parent
//
//@Dao
//interface PDao {
//    @Query("select * from Item")
//    fun getParents(): LiveData<List<Item>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert( parents : Item)
//}

//@Database(entities = [Item::class], version = 1,exportSchema = false)
//abstract class ParentDatabase: RoomDatabase() {
////    abstract val mparentDao: PDao
//}
//
//private lateinit var INSTANCE: ParentDatabase
//
//fun getDatabase(context: Context): ParentDatabase {
//    synchronized(ParentDatabase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(context.applicationContext,
//                ParentDatabase::class.java,
//                "Parents").build()
//            Log.d(TAG,"ANA BL GETDATABASE")
//        }
//    }
//    return INSTANCE
//}
