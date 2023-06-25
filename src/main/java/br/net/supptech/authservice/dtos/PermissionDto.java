package br.net.supptech.authservice.dtos;

import br.net.supptech.authservice.models.PermissionModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDto {

    private UUID permissionId;
    private String name;
    private String Description;

    public PermissionModel toModel() {
        PermissionModel permissionModel = new PermissionModel();
        permissionModel.setName(this.name.toUpperCase());
        permissionModel.setDescription(this.getDescription());
        return permissionModel;
    }

    public PermissionModel toUpdateModel(PermissionModel permissionModel) {
        permissionModel.setName(this.name.toUpperCase());
        permissionModel.setDescription(this.getDescription());
        return permissionModel;
    }
}
