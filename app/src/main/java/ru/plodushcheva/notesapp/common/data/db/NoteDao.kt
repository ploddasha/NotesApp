package ru.plodushcheva.notesapp.common.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteDB)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteDB>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteDB?

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: Int)

    @Query("UPDATE notes SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM notes WHERE isFavorite = 1")
    fun getFavoriteNotes(): Flow<List<NoteDB>>
}
