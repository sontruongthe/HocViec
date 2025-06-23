package SpringJPA.demo1.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {
    private boolean success;

    private String token;

    private String role;

    private String message;

    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AuthResponse(boolean success, String token, String role, String message) {
        this.success = success;
        this.token = token;
        this.role = role;
        this.message = message;
    }
}
