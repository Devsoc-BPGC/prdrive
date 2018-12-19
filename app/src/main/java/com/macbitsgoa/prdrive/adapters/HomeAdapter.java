package com.macbitsgoa.prdrive.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.prdrive.R;
import com.macbitsgoa.prdrive.Student;
import com.macbitsgoa.prdrive.viewholders.HomeViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private List<String> hostelNames;

    public HomeAdapter() {
        hostelNames = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Student> query = realm.where(Student.class).distinct("hostel");
        RealmResults<Student> results = query.findAll();
        for (Student s : results) {
            hostelNames.add(s.hostel);
        }
        realm.close();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_format_home, parent, false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.populate(hostelNames.get(position));
    }

    @Override
    public int getItemCount() {
        return hostelNames.size();
    }
}
