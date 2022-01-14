package pi.likvidatura.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import pi.likvidatura.domain.DnevnoStanje;
import pi.likvidatura.service.dto.DnevnoStanjeDTO;

/**
 * Mapper for the entity {@link DnevnoStanje} and its DTO {@link DnevnoStanjeDTO}.
 */
@Mapper(componentModel = "spring", uses = { BankarskiRacunMapper.class })
public interface DnevnoStanjeMapper extends EntityMapper<DnevnoStanjeDTO, DnevnoStanje> {
    @Mapping(target = "bankarskiRacun", source = "bankarskiRacun", qualifiedByName = "id")
    DnevnoStanjeDTO toDto(DnevnoStanje s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DnevnoStanjeDTO toDtoId(DnevnoStanje dnevnoStanje);
}
