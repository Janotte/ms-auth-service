package br.net.supptech.authservice.dtos;

import br.net.supptech.authservice.models.PermissionModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDto {

    @JsonView(RoleDto.RoleJsonView.CreateAndUpdateRole.class)
    private UUID permissionId;
    private String name;
    private String Description;

    public PermissionModel toModel(PermissionModel permissionModel) {
        permissionModel.setName(this.name.toUpperCase());
        permissionModel.setDescription(this.getDescription());
        return permissionModel;
    }
}
