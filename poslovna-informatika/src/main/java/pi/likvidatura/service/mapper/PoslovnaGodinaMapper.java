package pi.likvidatura.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import pi.likvidatura.domain.PoslovnaGodina;
import pi.likvidatura.service.dto.PoslovnaGodinaDTO;

/**
 * Mapper for the entity {@link PoslovnaGodina} and its DTO {@link PoslovnaGodinaDTO}.
 */
@Mapper(componentModel = "spring", uses = { PreduzeceMapper.class })
public interface PoslovnaGodinaMapper extends EntityMapper<PoslovnaGodinaDTO, PoslovnaGodina> {
    @Mapping(target = "preduzece", source = "preduzece", qualifiedByName = "id")
    PoslovnaGodinaDTO toDto(PoslovnaGodina s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PoslovnaGodinaDTO toDtoId(PoslovnaGodina poslovnaGodina);
}
