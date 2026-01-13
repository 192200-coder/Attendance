package com.example.attendance_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance_system.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
