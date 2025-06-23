package SpringJPA.demo1.Controller;

import SpringJPA.demo1.Empty.SpecialCharConfig;
import SpringJPA.demo1.Service.SpecialCharService;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigDTO;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigQueryDTO;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecicalCharConfigDTOV2;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@RestController
@RequestMapping("/API")
public class SpecialCharController {
    @Autowired
    private SpecialCharService specialCharService;
    @GetMapping("listSpecialChar")
    public ResponseEntity<List<SpecialCharConfig>>listSpecialChar(){
        List<SpecialCharConfig> specialCharConfigs = specialCharService.findAll();
        return new ResponseEntity<>(specialCharConfigs, HttpStatus.OK);
    }
    @GetMapping("specialChar/{id}")
    public ResponseEntity<SpecialCharConfig>specialCharId(@PathVariable Long id){
        SpecialCharConfig specialCharConfig = specialCharService.findById(id);
        if(specialCharConfig == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(specialCharConfig,HttpStatus.OK);
    }
    @PostMapping("add-specialChar")
    public ResponseEntity<SpecialCharConfig>addSpecialChar(@RequestBody SpecialCharConfig specialCharConfig){
        SpecialCharConfig saveSpecialChar = specialCharService.save(specialCharConfig);
        return  ResponseEntity.status(HttpStatus.CREATED).body(saveSpecialChar);
    }
    // API mới: Update SpecialCharConfig
    @PutMapping("update-specialChar/{id}")
    public ResponseEntity<SpecialCharConfig> updateSpecialChar(
            @PathVariable Long id,
            @Valid @RequestBody SpecialCharConfig specialCharConfig) {
        try {
            SpecialCharConfig updatedConfig = specialCharService.update(id, specialCharConfig);
            return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSpecialChar(@PathVariable Long id) {
        try {
            specialCharService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("search")
    public ResponseEntity<SpecicalCharConfigDTOV2>findCharName(@RequestBody SpecialCharConfigQueryDTO specialCharConfigQueryDTO){
        Pageable pageable = PageRequest.of(specialCharConfigQueryDTO.getPageNumber()-1, specialCharConfigQueryDTO.getPageSize());
        SpecicalCharConfigDTOV2 specialCharConfigs=specialCharService.findpageCharClient(specialCharConfigQueryDTO,pageable);
        return ResponseEntity.ok(specialCharConfigs);
    }


}