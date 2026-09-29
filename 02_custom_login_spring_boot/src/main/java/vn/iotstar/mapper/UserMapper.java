package vn.iotstar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import vn.iotstar.dto.UserDTO;
import vn.iotstar.entity.User;

/**
 * MapStruct mapper for User and UserDTO.
 * Uses abstract class pattern to ensure full compatibility with Spring Framework 7.
 */
@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {

    @Mapping(target = "roleName", source = "role.name")
    public abstract UserDTO toDTO(User user);

    public UserDTO toDto(User user) {
        return toDTO(user);
    }
}
