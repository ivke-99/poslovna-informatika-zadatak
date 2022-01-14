package pi.likvidatura.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pi.likvidatura.domain.PoslovniPartner;
import pi.likvidatura.domain.Preduzece;
import pi.likvidatura.service.dto.PoslovniPartnerDTO;
import pi.likvidatura.service.dto.PreduzeceDTO;

public interface PoslovniPartnerMapper extends EntityMapper<PoslovniPartnerDTO, PoslovniPartner>{
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PoslovniPartnerDTO toDtoId(PoslovniPartner partner);
}
