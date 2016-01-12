package cworks.hipster.web.rest.mapper;

import cworks.hipster.domain.*;
import cworks.hipster.web.rest.dto.AppDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity App and its DTO AppDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppMapper {

    AppDTO appToAppDTO(App app);

    App appDTOToApp(AppDTO appDTO);
}
