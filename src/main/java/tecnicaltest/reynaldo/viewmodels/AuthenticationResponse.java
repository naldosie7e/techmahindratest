package tecnicaltest.reynaldo.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tecnicaltest.reynaldo.models.User;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    private final String jwt;
    private final User user;

}