package com.LightFeather.Supervisor.Module.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class NewNotification {

        @NotBlank(message = "First name is mandatory")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
        private String firstName;

        @NotBlank(message = "Last name is mandatory")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
        private String lastName;

        @Email(message = "Email should be valid")
        private String email;

        @Pattern(regexp = "^[0-9]{10}$", message = "Phone number should be a 10-digit number")
        private String phoneNumber;

        @NotNull(message = "Supervisor is mandatory") //Supervisors
        private String supervisor;


        private boolean preferEmail;
        // getter and setter methods for preference field
        public boolean getPreference() {
                return preferEmail;
        }

        public void setPreference(boolean preference) {
                this.preferEmail = preference;
        }


}
