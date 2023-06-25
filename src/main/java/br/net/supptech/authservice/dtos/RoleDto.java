package br.net.supptech.authservice.dtos;

import br.net.supptech.authservice.models.RoleModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {
    private UUID roleId;
    private String name;
    private Set<PermissionDto> permissions;

    public RoleModel toModel() {
        RoleModel roleModel = new RoleModel();
        roleModel.setName(this.name.toUpperCase());
        return roleModel;
    }

    public RoleModel toUpdateModel(RoleModel roleModel) {
        roleModel.setName(this.name.toUpperCase());
        return roleModel;
    }
}
