package br.net.supptech.authservice.dtos;

import br.net.supptech.authservice.models.PermissionModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDto {

    @NotBlank(groups = {RoleDto.View.CreateAndUpdate.class})
    @JsonView(RoleDto.View.CreateAndUpdate.class)
    private UUID permissionId;
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;
    @NotBlank
    @Size(min = 20, max = 255)
    private String Description;

    public PermissionModel toModel(PermissionModel permissionModel) {
        permissionModel.setName(this.name.toUpperCase());
        permissionModel.setDescription(this.getDescription());
        return permissionModel;
    }
}
