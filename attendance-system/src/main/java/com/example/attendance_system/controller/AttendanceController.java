package com.example.attendance_system.controller;

import com.example.attendance_system.entity.Attendance;
import com.example.attendance_system.entity.User;
import com.example.attendance_system.repository.AttendanceRepository;
import com.example.attendance_system.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceController(AttendanceRepository attendanceRepository,
            UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestParam String type) {
        // Obtener usuario del contexto de seguridad (JWT)
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setTimestamp(LocalDateTime.now());
        attendance.setType(type); // "IN" or "OUT"
        attendanceRepository.save(attendance);

        return ResponseEntity.ok("Attendance marked: " + type);
    }
}