package com.chernov.ivan.myroutines

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

//    private lateinit var dao: ProgramDao
//    private lateinit var database: AppDatabase
//
//    @Before
//    fun createDb() {
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
//            .allowMainThreadQueries()
//            .build()
//        dao = database.programDao()!!
//    }
//
//    @Test
//    fun createNotes() {
//        dao.insertAll(SampleDataProvider.getNotes())
//        val count = dao.getCount()
//        assertEquals(count, SampleDataProvider.getNotes().size)
//    }
//
//    @Test
//    fun insertNote() {
//        val note = NoteEntity()
//        note.text = "some text"
//        dao.insertNote(note)
//        val savedNote = dao.getNoteById(1)
//        assertEquals(savedNote?.id ?: 0, 1)
//    }
//
//    @After
//    fun closeDb() {
//        database.close()
//    }
}