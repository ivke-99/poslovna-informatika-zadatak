package pi.likvidatura.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import pi.likvidatura.domain.Banka;
import pi.likvidatura.service.dto.BankaDTO;

/**
 * Mapper for the entity {@link Banka} and its DTO {@link BankaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankaMapper extends EntityMapper<BankaDTO, Banka> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BankaDTO toDtoId(Banka banka);
}
