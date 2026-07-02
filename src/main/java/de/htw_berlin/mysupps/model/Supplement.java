package de.htw_berlin.mysupps.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private String dosage;

    private String intakeTime;

    private String notes;

    private boolean taken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
