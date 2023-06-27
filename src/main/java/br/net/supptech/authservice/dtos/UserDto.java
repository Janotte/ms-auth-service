package br.net.supptech.authservice.dtos;

import br.net.supptech.authservice.enums.UserStatus;
import br.net.supptech.authservice.models.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    public interface UserJsonView {
        interface SignUp{}
        interface CreateUser{}
        interface UserUpdate{}
        interface UserPasswordUpdate{}
        interface UserImageUpdate{}
    }

    private UUID userId;
    @JsonView({UserJsonView.CreateUser.class, UserJsonView.SignUp.class, UserJsonView.UserUpdate.class})
    private String username;
    @JsonView({UserJsonView.CreateUser.class, UserJsonView.SignUp.class, UserJsonView.UserUpdate.class})
    private String fullName;
    @JsonView({UserJsonView.CreateUser.class, UserJsonView.SignUp.class})
    private String email;
    @JsonView({UserJsonView.CreateUser.class, UserJsonView.SignUp.class, UserJsonView.UserPasswordUpdate.class})
    private String password;
    @JsonView({UserJsonView.UserPasswordUpdate.class})
    private String OldPassword;
    @JsonView({UserJsonView.CreateUser.class, UserJsonView.SignUp.class, UserJsonView.UserUpdate.class})
    private String cellPhoneNumber;
    @JsonView(UserJsonView.UserImageUpdate.class)
    private String imageUrl;
    @JsonView(UserJsonView.CreateUser.class)
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
