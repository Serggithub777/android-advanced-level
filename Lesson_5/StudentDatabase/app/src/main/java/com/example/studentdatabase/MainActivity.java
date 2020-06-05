package com.example.studentdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.studentdatabase.dao.EducationDao;
import com.example.studentdatabase.data.Student;
import com.example.studentdatabase.data.StudentEmail;

public class MainActivity extends AppCompatActivity {
    private StudentRecyclerAdapter adapter;
    private EducationSource educationSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R. id. toolbar) ;
        setSupportActionBar(toolbar) ;
        initRecyclerView() ;
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R. id. recycler_view) ;
        recyclerView. setHasFixedSize(true) ;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) ;
        recyclerView. setLayoutManager(layoutManager) ;
        EducationDao educationDao = App
                . getInstance()
                . getEducationDao() ;
        educationSource = new EducationSource(educationDao) ;
        adapter = new StudentRecyclerAdapter(educationSource, this) ;
        recyclerView. setAdapter(adapter) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu) ;
        return true;
    }

    @
            Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest. xml
        int id = item. getItemId() ;
            // Добавление новой записи
        if (id == R. id. action_add) {
            educationSource. addStudent(new
                    RandomStudent(getResources() ) . rndStudent() ) ;
            adapter. notifyDataSetChanged() ;
            return true;
        }return super. onOptionsItemSelected(item) ;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu. ContextMenuInfo menuInfo) {
        super. onCreateContextMenu(menu, v, menuInfo) ;
        MenuInflater inflater = getMenuInflater() ;
        inflater. inflate(R. menu. context_menu, menu) ;
    }
    @
            Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R. id. view_context:
                Student viewStudent = educationSource
                        . getStudents()
                        . get((int) adapter. getMenuPosition() ) ;
                StudentEmail studentEmail =
                        educationSource. getStudentEmail(viewStudent. id) ;
                StudentCardDialogFragment.create(studentEmail) . show(getSupportFragmentManager() ,
                        "StudentCard") ;
                return true;
            // Добавляем запись
            case R.id.add_context:
                // Получаем студента со случайными данными
                Student studentForInsert = new RandomStudent(getResources())
                        .rndStudent();
                // Добавляем студента
                educationSource.addStudent(studentForInsert);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.update_context:
                // Изменяем имя и фамилию студента
                Student oldStudent = educationSource
                        . getStudents()
                        . get((int) adapter. getMenuPosition() ) ;
                Student studentForUpdate = new RandomStudent(getResources() )
                        . rndUpdateStudent(oldStudent) ;
                educationSource. updateStudent(studentForUpdate) ;
                adapter. notifyItemChanged((int) adapter. getMenuPosition() ) ;
                return true;
            case R. id. remove_context:
                 // Удаляем запись из базы
                Student studentForRemove = educationSource
                        . getStudents()
                        . get((int) adapter. getMenuPosition() ) ;
                educationSource. removeStudent(studentForRemove. id) ;
                adapter. notifyItemRemoved((int) adapter. getMenuPosition() ) ;
                return true;
        }return super. onContextItemSelected(item) ;
    }
}

