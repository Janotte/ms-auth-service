package br.net.supptech.authservice.specification;

import br.net.supptech.authservice.models.UserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecsTemplate {
    @And({
            @Spec(path = "username", spec = Like.class),
            @Spec(path = "fullName", spec = Like.class),
            @Spec(path = "email", spec = Like.class),
            @Spec(path = "userStatus", spec = Equal.class),
            @Spec(path = "createdAt", spec = Between.class),
            @Spec(path = "updatedAt", spec = Between.class)
    })
    public interface UserSpecs extends Specification<UserModel> {
    }
}
