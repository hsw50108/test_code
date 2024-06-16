package me.dongwook.dayonetest.repository;

import me.dongwook.dayonetest.model.StudentFail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentFailRepository extends JpaRepository<StudentFail, Integer> {

}
