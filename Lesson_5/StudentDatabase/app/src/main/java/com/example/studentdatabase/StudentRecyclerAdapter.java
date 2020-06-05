package com.example.studentdatabase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentdatabase.data.Student;

import java.util.List;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.ViewHolder> {

    private Activity activity;
    //Источник данных
    private EducationSource dataSource;
    //Позиция в списке, на которой было нажато меню
    private long menuPosition;

    public StudentRecyclerAdapter(EducationSource dataSource, Activity activity) {
        this. dataSource = dataSource;
        this. activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater. from(parent. getContext() )
                . inflate(R.layout.item, parent, false) ;
        ViewHolder vh = new ViewHolder(v) ;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
// Заполняем данными записи на экране
        List<Student> students = dataSource. getStudents() ;
        Student student = students. get(position) ;
        holder.studentLastName.setText(student.lastName) ;
        holder.studentFirstName.setText(student.firstName) ;
// Тут определяем, какой пункт меню был нажат
        holder.cardView.setOnLongClickListener(view -> {
            menuPosition = position;
            return false;
        } ) ;
// Регистрируем контекстное меню
        if (activity != null) {
            activity. registerForContextMenu(holder.cardView) ;
        }
    }

    @Override
    public int getItemCount() {
        return (int) dataSource.getCountStudents() ;
    }

    public long getMenuPosition() {
        return menuPosition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentLastName;
        TextView studentFirstName;
        View cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            studentLastName = cardView. findViewById(R. id. textStudentLastName) ;
            studentFirstName = cardView. findViewById(R. id. textStudenFirstName) ;
        }
    }
}
