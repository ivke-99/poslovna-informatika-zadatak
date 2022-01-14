package pi.likvidatura.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pi.likvidatura.domain.IzlaznaFaktura;
import pi.likvidatura.service.dto.IzlaznaFakturaDTO;

/**
 * Mapper for the entity {@link IzlaznaFaktura} and its DTO {@link IzlaznaFakturaDTO}.
 */
@Mapper(componentModel = "spring", uses = { PoslovnaGodinaMapper.class })
public interface IzlaznaFakturaMapper extends EntityMapper<IzlaznaFakturaDTO, IzlaznaFaktura> {
    @Mapping(target = "poslovnaGodina", source = "poslovnaGodina", qualifiedByName = "id")
    IzlaznaFakturaDTO toDto(IzlaznaFaktura s);
}
