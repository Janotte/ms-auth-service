package br.net.supptech.authservice.dtos;

import br.net.supptech.authservice.models.RoleModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {

    public interface View {
        interface CreateAndUpdate {
        }
    }

    @NotNull(groups = {UserDto.View.CreateUser.class})
    @JsonView(UserDto.View.CreateUser.class)
    private UUID roleId;
    @NotBlank(groups = {View.CreateAndUpdate.class})
    @Size(min = 3, max = 30, groups = {View.CreateAndUpdate.class})
    private String name;
    @NotEmpty(groups = {View.CreateAndUpdate.class})
    private Set<PermissionDto> permissions;

    public RoleModel toModel(RoleModel roleModel) {
        roleModel.setName(this.name.toUpperCase());
        return roleModel;
    }
}
