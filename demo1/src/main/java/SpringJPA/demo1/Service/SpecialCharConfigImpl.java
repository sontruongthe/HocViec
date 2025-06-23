package SpringJPA.demo1.Service;

import SpringJPA.demo1.Empty.SpecialCharConfig;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigDTO;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigQueryDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SpecialCharConfigImpl {


    void delete(Long id);


    SpecialCharConfig save(@Valid SpecialCharConfig specialCharConfig);



    SpecialCharConfig findById(Long id);

    SpecialCharConfig updateSpecialConfigChar(Long id, @Valid SpecialCharConfig specialCharConfig);


    SpecialCharConfigDTO getConfigsByClientV2(SpecialCharConfigQueryDTO queryDTO);


    SpecialCharConfigDTO findNameChar(SpecialCharConfigQueryDTO specialCharConfigQueryDTO, Pageable pageable);
}