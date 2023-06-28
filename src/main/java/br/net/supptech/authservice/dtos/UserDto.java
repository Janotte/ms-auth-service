package br.net.supptech.authservice.dtos;

import br.net.supptech.authservice.enums.UserStatus;
import br.net.supptech.authservice.models.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    public interface View {
        interface SignUp{}
        interface CreateUser{}
        interface UserUpdate{}
        interface UserPasswordUpdate{}
        interface UserImageUpdate{}
    }

    private UUID userId;
    @NotBlank(groups = {View.CreateUser.class, View.SignUp.class, View.UserUpdate.class})
    @Size(min = 3, max = 30, groups = {View.CreateUser.class, View.SignUp.class, View.UserUpdate.class})
    @JsonView({View.CreateUser.class, View.SignUp.class, View.UserUpdate.class})
    private String username;
    @NotBlank(groups = {View.CreateUser.class, View.SignUp.class, View.UserUpdate.class})
    @Size(min = 10, max = 80, groups = {View.CreateUser.class, View.SignUp.class, View.UserUpdate.class})
    @JsonView({View.CreateUser.class, View.SignUp.class, View.UserUpdate.class})
    private String fullName;
    @NotBlank(groups = {View.CreateUser.class, View.SignUp.class})
    @Size(max = 50, groups = {View.CreateUser.class, View.SignUp.class})
    @Email(groups = {View.CreateUser.class, View.SignUp.class})
    @JsonView({View.CreateUser.class, View.SignUp.class})
    private String email;
    @NotBlank(groups = {View.CreateUser.class, View.SignUp.class, View.UserPasswordUpdate.class})
    @Size(min = 8, max = 50, groups = {View.CreateUser.class, View.SignUp.class, View.UserPasswordUpdate.class})
    @JsonView({View.CreateUser.class, View.SignUp.class, View.UserPasswordUpdate.class})
    private String password;
    @NotBlank(groups = {View.UserPasswordUpdate.class})
    @JsonView({View.UserPasswordUpdate.class})
    private String OldPassword;
    @Size(min = 9, max = 20, groups = {View.CreateUser.class, View.SignUp.class, View.UserUpdate.class})
    @JsonView({View.CreateUser.class, View.SignUp.class, View.UserUpdate.class})
    private String cellPhoneNumber;
    @NotBlank(groups = {View.UserImageUpdate.class})
    @Size(max = 255,groups = {View.UserImageUpdate.class})
    @JsonView(View.UserImageUpdate.class)
    private String imageUrl;
    @NotEmpty(groups = {View.CreateUser.class})
    @JsonView(View.CreateUser.class)
    private Set<RoleDto> roles;

    public UserModel toModel() {
        UserModel userModel = new UserModel();
        userModel.setUsername(this.username);
        userModel.setFullName(this.fullName);
        userModel.setEmail(this.email);
        userModel.setPassword(this.password);
        userModel.setCellPhoneNumber(this.cellPhoneNumber);
        userModel.setUserStatus(UserStatus.CREATED);
        return userModel;
    }

    public UserModel toUpdateModel(UserModel userModel) {
        userModel.setUsername(this.username);
        userModel.setFullName(this.fullName);
        userModel.setCellPhoneNumber(this.cellPhoneNumber);
        return userModel;
    }

    public UserModel toUpdatePasswordModel(UserModel userModel) {
        userModel.setPassword(this.password);
        return userModel;
    }

    public UserModel toUpdateImageModel(UserModel userModel) {
        userModel.setImageUrl(this.imageUrl);
        return userModel;
    }
}
