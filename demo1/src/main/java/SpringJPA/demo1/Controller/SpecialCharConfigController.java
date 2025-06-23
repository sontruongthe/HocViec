package SpringJPA.demo1.Controller;

import SpringJPA.demo1.Empty.SpecialCharConfig;
import SpringJPA.demo1.Service.SpecialCharConfigImpl;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigDTO;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigQueryDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api")
public class SpecialCharConfigController {

    @Autowired
    private SpecialCharConfigImpl specialCharConfigImpl;


    @PostMapping("/addChar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SpecialCharConfig> save(@Valid @RequestBody SpecialCharConfig specialCharConfig) {
        SpecialCharConfig savedConfig = specialCharConfigImpl.save(specialCharConfig);
        return ResponseEntity.ok(savedConfig);
    }


    @GetMapping("specialChar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SpecialCharConfig>specialCharId(@PathVariable Long id) {
        SpecialCharConfig specialCharConfig = specialCharConfigImpl.findById(id);
        return ResponseEntity.ok(specialCharConfig);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        specialCharConfigImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SpecialCharConfig>updateSpecialChar(@PathVariable Long id, @Valid @RequestBody SpecialCharConfig specialCharConfig){
        SpecialCharConfig specialCharConfig1=specialCharConfigImpl.updateSpecialConfigChar(id,specialCharConfig);
        return ResponseEntity.ok(specialCharConfig1);
    }

    //Specification
    @PostMapping("/query")
    public ResponseEntity<SpecialCharConfigDTO> findNameChar(@RequestBody SpecialCharConfigQueryDTO specialCharConfigQueryDTO) {
        Pageable pageable = PageRequest.of(specialCharConfigQueryDTO.getPageNumber()-1, specialCharConfigQueryDTO.getPageSize()); // Page bắt đầu từ 0 trong Spring Data
        SpecialCharConfigDTO configs = specialCharConfigImpl.findNameChar(specialCharConfigQueryDTO, pageable);
        return ResponseEntity.ok(configs);
    }

    //procerduce
    @PostMapping("/procedure/specialcharconfigV2")
    public ResponseEntity<SpecialCharConfigDTO> getSpecialCharConfig2(
            @RequestBody SpecialCharConfigQueryDTO queryDTO) {
        SpecialCharConfigDTO configs = specialCharConfigImpl.getConfigsByClientV2(queryDTO);
        return ResponseEntity.ok(configs);
    }

}