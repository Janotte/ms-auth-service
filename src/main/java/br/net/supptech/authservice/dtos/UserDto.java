package br.net.supptech.authservice.dtos;

import br.net.supptech.authservice.enums.UserStatus;
import br.net.supptech.authservice.models.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private UUID userId;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String cellPhoneNumber;
    private Set<RoleDto> roles;

    public UserModel toModel() {
        UserModel userModel = new UserModel();
        userModel.setUsername(this.username);
        userModel.setFullName(this.fullName);
        userModel.setEmail(this.email);
        userModel.setPassword(this.password);
        userModel.setCellPhoneNumber(cellPhoneNumber);
        userModel.setUserStatus(UserStatus.CREATED);
        return userModel;
    }

    public UserModel toUpdateModel(UserModel userModel) {
        userModel.setUsername(this.username);
        userModel.setFullName(this.fullName);
        userModel.setEmail(this.email);
        userModel.setCellPhoneNumber(cellPhoneNumber);
        return userModel;
    }
}
