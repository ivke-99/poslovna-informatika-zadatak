package pi.likvidatura.service.mapper;

import org.mapstruct.Mapper;
import pi.likvidatura.domain.Uplata;
import pi.likvidatura.service.dto.UplataDTO;

@Mapper(componentModel = "spring", uses = { StavkaIzvodaMapper.class, IzlaznaFakturaMapper.class })
public interface UplataMapper extends EntityMapper<UplataDTO, Uplata>{

}
