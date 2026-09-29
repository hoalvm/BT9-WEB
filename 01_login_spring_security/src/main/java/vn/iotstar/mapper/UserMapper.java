package vn.iotstar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import vn.iotstar.dto.UserDTO;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;

/**
 * MapStruct mapper — uses abstract class (instead of interface) to avoid
 * Spring Framework 7 AutowiredAnnotationBeanPostProcessor reflection issue
 * with private helper methods referencing entity types by simple name.
 */
@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {

    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "roleName", source = "role.name")
    public abstract UserDTO toDto(User entity);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract User toEntity(UserDTO dto);
}
