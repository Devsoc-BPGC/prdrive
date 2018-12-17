package com.macbitsgoa.prdrive;

import com.google.firebase.database.DataSnapshot;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Model for a student.
 *
 * @author Rushikesh Jogdand.
 */
public class Student extends RealmObject {
    public String name;

    @PrimaryKey
    public String id;

    @Index
    public String hostel;

    @Index
    public String roomNo;

    public Student() {
    }

    public Student(final String name, final String id, final String hostel, final String roomNo) {
        this.name = name;
        this.id = id;
        this.hostel = hostel;
        this.roomNo = roomNo;
    }
}
