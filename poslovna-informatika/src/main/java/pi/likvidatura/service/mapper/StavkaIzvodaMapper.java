package pi.likvidatura.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pi.likvidatura.domain.StavkaIzvoda;
import pi.likvidatura.service.dto.StavkaIzvodaDTO;

/**
 * Mapper for the entity {@link StavkaIzvoda} and its DTO {@link StavkaIzvodaDTO}.
 */
@Mapper(componentModel = "spring", uses = { DnevnoStanjeMapper.class })
public interface StavkaIzvodaMapper extends EntityMapper<StavkaIzvodaDTO, StavkaIzvoda> {
    @Mapping(target = "dnevnoStanje", source = "dnevnoStanje", qualifiedByName = "id")
    StavkaIzvodaDTO toDto(StavkaIzvoda s);
}
