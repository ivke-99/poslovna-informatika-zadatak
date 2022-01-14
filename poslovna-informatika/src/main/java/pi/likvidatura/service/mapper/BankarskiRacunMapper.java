package pi.likvidatura.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import pi.likvidatura.domain.BankarskiRacun;
import pi.likvidatura.service.dto.BankarskiRacunDTO;

/**
 * Mapper for the entity {@link BankarskiRacun} and its DTO {@link BankarskiRacunDTO}.
 */
@Mapper(componentModel = "spring", uses = { PreduzeceMapper.class, BankaMapper.class })
public interface BankarskiRacunMapper extends EntityMapper<BankarskiRacunDTO, BankarskiRacun> {
    @Mapping(target = "preduzece", source = "preduzece", qualifiedByName = "id")
    @Mapping(target = "banka", source = "banka", qualifiedByName = "id")
    BankarskiRacunDTO toDto(BankarskiRacun s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BankarskiRacunDTO toDtoId(BankarskiRacun bankarskiRacun);
}
