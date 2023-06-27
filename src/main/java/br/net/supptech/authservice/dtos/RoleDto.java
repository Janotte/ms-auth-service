package br.net.supptech.authservice.dtos;

import br.net.supptech.authservice.models.RoleModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {

    public interface RoleJsonView {
        interface CreateAndUpdateRole {
        }
    }

    @JsonView(UserDto.UserJsonView.CreateUser.class)
    private UUID roleId;
    private String name;
    private Set<PermissionDto> permissions;

    public RoleModel toModel(RoleModel roleModel) {
        roleModel.setName(this.name.toUpperCase());
        return roleModel;
    }
}
