package com.pulsecare.backend.module.specialization.model;

import com.pulsecare.backend.module.user.module.doctordetail.model.DoctorDetail;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "specializations")
    @ToString.Exclude
    private Set<DoctorDetail> doctors;
}

