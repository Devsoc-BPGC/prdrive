package com.macbitsgoa.prdrive.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.Student;
import com.macbitsgoa.prdrive.viewholders.ResidentViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ResidentAdapter extends RecyclerView.Adapter<ResidentViewHolder> {

    private List<Student> students;

    public ResidentAdapter(String hostel) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Student> query = realm.where(Student.class).equalTo("hostel", hostel);
        RealmResults<Student> results = query.findAll();
        students = realm.copyFromRealm(results);
        realm.close();
    }

    @Override
    public ResidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResidentViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_format_resident, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentViewHolder holder, int position) {
        holder.populate(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
