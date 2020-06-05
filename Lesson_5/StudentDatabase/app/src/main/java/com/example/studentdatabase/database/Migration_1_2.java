package com.example.studentdatabase.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration_1_2 extends Migration {
    /**
     * Creates a new migration between {@code startVersion} and {@code endVersion}.
     *
     * @param startVersion The start version of the database.
     * @param endVersion   The end version of the database after this migration is applied.
     */
    public Migration_1_2(int startVersion, int endVersion) {
        super(1, 2);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        database. execSQL("ALTER TABLE Student " +
                "ADD COLUMN dateBirth INTEGER DEFAULT 0") ;
        database. execSQL("ALTER TABLE Student " +
                "ADD COLUMN city TEXT") ;
        database. execSQL("ALTER TABLE Student " +
                "ADD COLUMN street TEXT") ;
        database. execSQL("ALTER TABLE Student " +
                "ADD COLUMN post_code TEXT") ;
        database. execSQL("CREATE TABLE IF NOT EXISTS Email " +
                "(id INTEGER NOT NULL PRIMARY KEY, " +
                "student_id INTEGER NOT NULL REFERENCES " +
                "Student(id) ON DELETE CASCADE, " +
                "email TEXT) ") ;
        database. execSQL("UPDATE Student " +
                "SET city = ' Санкт-Петербург' , " +
                "street = ' Дворцовая площадь' ") ;
    }

}
