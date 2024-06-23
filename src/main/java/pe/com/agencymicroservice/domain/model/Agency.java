package pe.com.agencymicroservice.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agency")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "name",nullable = false)
    private String Name;
    @Column(name = "description",nullable = false)
    private String Description;
    @Column(name = "phone_number",nullable = false)
    private String PhoneNumber;
    @Column(name = "email",nullable = false)
    private String Email;
    @Column(name = "address",nullable = false)
    private String Address;
}
