package SpringJPA.demo1.Service;

import SpringJPA.demo1.Empty.SpecialCharConfig;
import SpringJPA.demo1.Repository.SpecialCharRepository;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigDTO;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigQueryDTO;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecicalCharConfigDTOV2;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class SpecialCharService {
    @Autowired
    private SpecialCharRepository specialCharRepository;


    public List<SpecialCharConfig>findAll(){
        return specialCharRepository.findAll();



    }

    public SpecialCharConfig findById(Long id) {
       return  specialCharRepository.findByID(id);

    }

    public SpecialCharConfig save(SpecialCharConfig specialCharConfig) {
        return specialCharRepository.save(specialCharConfig);
    }

    public void remove(Long id) {
        boolean deleted = specialCharRepository.remove(id);
        if (!deleted) {
            throw new NoSuchElementException("Không tìm thấy SpecialCharConfig với id: " + id);
        }
    }




    public SpecialCharConfig update(Long id, SpecialCharConfig specialCharConfig) {
        return specialCharRepository.update(id,specialCharConfig);


    }

    public SpecicalCharConfigDTOV2 findpageCharClient(SpecialCharConfigQueryDTO specialCharConfigQueryDTO, Pageable pageable) {
        return specialCharRepository.findpageCharClient(specialCharConfigQueryDTO,pageable);
    }
}
