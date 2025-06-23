package SpringJPA.demo1.Service;


import SpringJPA.demo1.Empty.SpecialCharConfig;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigDTO;
import SpringJPA.demo1.Repository.SpecialCharConfigRepository;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigQueryDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import java.util.*;

@Transactional
@Service
public class SpecialCharConfigService implements SpecialCharConfigImpl {

    @Autowired
    private SpecialCharConfigRepository specialCharConfigRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public SpecialCharConfig save(SpecialCharConfig specialCharConfig) {
        return specialCharConfigRepository.save(specialCharConfig);
    }


    @Override
    public void delete(Long id) {
        specialCharConfigRepository.deleteById(id);
    }

    @Override
    public SpecialCharConfig findById(Long id) {
        return specialCharConfigRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Không thấy id"));


    }

    @Override
    public SpecialCharConfig updateSpecialConfigChar(Long id, SpecialCharConfig specialCharConfig) {
        // Tìm bản ghi theo ID
        SpecialCharConfig existingConfig = specialCharConfigRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bản ghi với ID: " + id));

        // Cập nhật các trường với dữ liệu mới từ specialCharConfig
        existingConfig.setSpecialChar(specialCharConfig.getSpecialChar());
        existingConfig.setReplaceChar(specialCharConfig.getReplaceChar());
        existingConfig.setDataLocation(specialCharConfig.getDataLocation());
        existingConfig.setWebView(specialCharConfig.getWebView());
        existingConfig.setParaStatus(specialCharConfig.getParaStatus());
        existingConfig.setMsgType(specialCharConfig.getMsgType());
        existingConfig.setFieldId(specialCharConfig.getFieldId());
        existingConfig.setFieldCode(specialCharConfig.getFieldCode());
        existingConfig.setMsgDirection(specialCharConfig.getMsgDirection());
        existingConfig.setXmlTag(specialCharConfig.getXmlTag());
        existingConfig.setActiveStatus(specialCharConfig.getActiveStatus());
        existingConfig.setClientName(specialCharConfig.getClientName());
        existingConfig.setClientCode(specialCharConfig.getClientCode());
        existingConfig.setChannelPayment(specialCharConfig.getChannelPayment());
        existingConfig.setRowNumber(specialCharConfig.getRowNumber());
        existingConfig.setJsonData(specialCharConfig.getJsonData());

        // Lưu bản ghi đã cập nhật
        return specialCharConfigRepository.save(existingConfig);
    }
    //Specification
    @Override
    public SpecialCharConfigDTO findNameChar(SpecialCharConfigQueryDTO specialCharConfigQueryDTO, Pageable pageable) {

        Page<SpecialCharConfig> page = specialCharConfigRepository.findAll(new Specification<SpecialCharConfig>() {
            @Override
            public Predicate toPredicate(Root<SpecialCharConfig> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                // Điều kiện lọc
                if (specialCharConfigQueryDTO.getClientName() != null && !specialCharConfigQueryDTO.getClientName().trim().isEmpty()) {
                    String clientName = specialCharConfigQueryDTO.getClientName().trim().toLowerCase();
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("clientName")), "%" + clientName + "%"));
                }

                if (specialCharConfigQueryDTO.getSpecialChar() != null && !specialCharConfigQueryDTO.getSpecialChar().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("specialChar"), specialCharConfigQueryDTO.getSpecialChar()));
                }

                if (specialCharConfigQueryDTO.getMsgType() != null && !specialCharConfigQueryDTO.getMsgType().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("msgType"), specialCharConfigQueryDTO.getMsgType()));
                }

                if (specialCharConfigQueryDTO.getParaStatus() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("paraStatus"), specialCharConfigQueryDTO.getParaStatus()));
                }

                if (specialCharConfigQueryDTO.getActiveStatus() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("activeStatus"), specialCharConfigQueryDTO.getActiveStatus()));
                }

                if (specialCharConfigQueryDTO.getFieldId() != null && !specialCharConfigQueryDTO.getFieldId().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("fieldId"), specialCharConfigQueryDTO.getFieldId()));
                }

                if (specialCharConfigQueryDTO.getClientCode() != null && !specialCharConfigQueryDTO.getClientCode().isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("clientCode"), specialCharConfigQueryDTO.getClientCode()));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        }, pageable);


        SpecialCharConfigDTO response= new SpecialCharConfigDTO();
        response.setContent(page.getContent());
        response.setCurrentPage(page.getNumber() + 1);
        response.setTotalPages(page.getTotalPages());
        response.setTotalItems((int) page.getTotalElements()); // Thêm totalItems
        return response;
    }

    //procerduce
    @Override
    public SpecialCharConfigDTO getConfigsByClientV2(SpecialCharConfigQueryDTO queryDTO) {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getSpecialCharConfigByClientWithCount");

        // Đăng ký và set giá trị cho các tham số IN
        query.setParameter("p_client_name", queryDTO.getClientName());

        query.setParameter("p_special_char", queryDTO.getSpecialChar());

        query.setParameter("p_msg_type", queryDTO.getMsgType());

        query.setParameter("p_para_status", queryDTO.getParaStatus());

        query.setParameter("p_active_status", queryDTO.getActiveStatus());

        query.setParameter("p_field_id", queryDTO.getFieldId());

        query.setParameter("p_client_code", queryDTO.getClientCode());

        query.setParameter("p_page_number", queryDTO.getPageNumber());

        query.setParameter("p_page_size", queryDTO.getPageSize());



        // Thực thi procedure
        query.execute();

        // Lấy kết quả
        List<SpecialCharConfig> configs = query.getResultList();
        Integer total = (Integer) query.getOutputParameterValue("p_total");

        // Tính toán phân trang
        int totalPages = (total > 0 && queryDTO.getPageSize() > 0)
                ? (int) Math.ceil((double) total / queryDTO.getPageSize())
                : 0;

        // Đóng gói DTO trả về
        SpecialCharConfigDTO dto = new SpecialCharConfigDTO();
        dto.setContent(configs);
        dto.setTotalItems(total != null ? total : 0);
        dto.setCurrentPage(queryDTO.getPageNumber());
        dto.setTotalPages(totalPages);

        return dto;
    }





}