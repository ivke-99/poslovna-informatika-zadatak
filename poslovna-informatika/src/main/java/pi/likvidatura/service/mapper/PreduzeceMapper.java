package pi.likvidatura.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import pi.likvidatura.domain.Preduzece;
import pi.likvidatura.service.dto.PreduzeceDTO;

/**
 * Mapper for the entity {@link Preduzece} and its DTO {@link PreduzeceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PreduzeceMapper extends EntityMapper<PreduzeceDTO, Preduzece> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PreduzeceDTO toDtoId(Preduzece preduzece);
}
