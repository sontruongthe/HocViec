package SpringJPA.demo1.Empty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_ACCOUNTS")
@Getter
@Setter
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "para_special_char_seq")
    @SequenceGenerator(name = "para_special_char_seq", sequenceName = "para_special_char_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @Column(name = "ROLES", length = 255)
    private String roles;
}
