package SpringJPA.demo1.Repository;

import SpringJPA.demo1.Empty.SpecialCharConfig;
import SpringJPA.demo1.Empty.MsgTypeSummary;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigDTO;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigProjection;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigQueryDTO;
import SpringJPA.demo1.SpecialCharConfigDTO.SpecicalCharConfigDTOV2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SpecialCharRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void persist(final SpecialCharConfig specialCharConfig){
        entityManager.persist(specialCharConfig);
    }
    public SpecialCharConfig findByID(Long id){
        return entityManager.find(SpecialCharConfig.class,id);
    }

    public List<SpecialCharConfig> findAll(){
        return entityManager.createQuery("From SpecialCharConfig").getResultList();
    }


    public SpecialCharConfig save(SpecialCharConfig specialCharConfig) {
        entityManager.persist(specialCharConfig);
        return specialCharConfig;
    }

    public boolean remove(Long id) {
        SpecialCharConfig specialCharConfig = entityManager.find(SpecialCharConfig.class, id);
        if (specialCharConfig != null) {
            entityManager.remove(specialCharConfig);
            return true;
        }
        return false;
    }

    public SpecialCharConfig update(Long id, SpecialCharConfig specialCharConfig) {
        SpecialCharConfig existingConfig=entityManager.find(SpecialCharConfig.class,id);
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
        // Lưu thay đổi vào cơ sở dữ liệu
        entityManager.merge(existingConfig);
        return existingConfig;


    }
    //Search
    public SpecicalCharConfigDTOV2 findpageCharClient(SpecialCharConfigQueryDTO queryDTO, Pageable pageable) {

        StringBuilder jpql = new StringBuilder(
                "SELECT NEW SpringJPA.demo1.SpecialCharConfigDTO.SpecialCharConfigProjection(" +
                        "s.id, s.specialChar, s.replaceChar, s.dataLocation, s.webView, s.paraStatus, " +
                        "s.msgType, s.fieldId, s.fieldCode, s.msgDirection, s.xmlTag, s.activeStatus, " +
                        "s.clientName, s.clientCode, s.channelPayment, s.rowNumber, s.jsonData, " +
                        "m.description, m.activeStatus) " +
                        "FROM SpecialCharConfig s " +
                        "LEFT JOIN MsgTypeSummary m ON s.msgType = m.msgType"
        );
        StringBuilder countJpql = new StringBuilder(
                "SELECT COUNT(s) FROM SpecialCharConfig s " +
                        "LEFT JOIN MsgTypeSummary m ON s.msgType = m.msgType"
        );

        List<String> conditions = new ArrayList<>();
        if (queryDTO.getClientName() != null && !queryDTO.getClientName().trim().isEmpty()) {
            conditions.add("UPPER(s.clientName) LIKE UPPER(:clientName)");
        }
        if (queryDTO.getSpecialChar() != null && !queryDTO.getSpecialChar().trim().isEmpty()) {
            conditions.add("s.specialChar = :specialChar");
        }
        if (queryDTO.getMsgType() != null && !queryDTO.getMsgType().trim().isEmpty()) {
            conditions.add("s.msgType = :msgType");
        }
        if (queryDTO.getParaStatus() != null) {
            conditions.add("s.paraStatus = :paraStatus");
        }
        if (queryDTO.getActiveStatus() != null) {
            conditions.add("s.activeStatus = :activeStatus");
        }
        if (queryDTO.getFieldId() != null && !queryDTO.getFieldId().trim().isEmpty()) {
            conditions.add("s.fieldId = :fieldId");
        }
        if (queryDTO.getClientCode() != null && !queryDTO.getClientCode().trim().isEmpty()) {
            conditions.add("s.clientCode = :clientCode");
        }

        if (!conditions.isEmpty()) {
            jpql.append(" WHERE ").append(String.join(" AND ", conditions));
            countJpql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        jpql.append(" ORDER BY s.id");

        TypedQuery<SpecialCharConfigProjection> query = entityManager.createQuery(jpql.toString(), SpecialCharConfigProjection.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql.toString(), Long.class);

        if (queryDTO.getClientName() != null && !queryDTO.getClientName().trim().isEmpty()) {
            query.setParameter("clientName", "%" + queryDTO.getClientName().toUpperCase() + "%");
            countQuery.setParameter("clientName", "%" + queryDTO.getClientName().toUpperCase() + "%");
        }
        if (queryDTO.getSpecialChar() != null && !queryDTO.getSpecialChar().trim().isEmpty()) {
            query.setParameter("specialChar", queryDTO.getSpecialChar());
            countQuery.setParameter("specialChar", queryDTO.getSpecialChar());
        }
        if (queryDTO.getMsgType() != null && !queryDTO.getMsgType().trim().isEmpty()) {
            query.setParameter("msgType", queryDTO.getMsgType());
            countQuery.setParameter("msgType", queryDTO.getMsgType());
        }
        if (queryDTO.getParaStatus() != null) {
            query.setParameter("paraStatus", queryDTO.getParaStatus());
            countQuery.setParameter("paraStatus", queryDTO.getParaStatus());
        }
        if (queryDTO.getActiveStatus() != null) {
            query.setParameter("activeStatus", queryDTO.getActiveStatus());
            countQuery.setParameter("activeStatus", queryDTO.getActiveStatus());
        }
        if (queryDTO.getFieldId() != null && !queryDTO.getFieldId().trim().isEmpty()) {
            query.setParameter("fieldId", queryDTO.getFieldId());
            countQuery.setParameter("fieldId", queryDTO.getFieldId());
        }
        if (queryDTO.getClientCode() != null && !queryDTO.getClientCode().trim().isEmpty()) {
            query.setParameter("clientCode", queryDTO.getClientCode());
            countQuery.setParameter("clientCode", queryDTO.getClientCode());
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        Long totalItems = countQuery.getSingleResult();
        int totalPages = (int) Math.ceil((double) totalItems / pageable.getPageSize());

        SpecicalCharConfigDTOV2 response = new SpecicalCharConfigDTOV2();
        response.setContent(query.getResultList());
        response.setCurrentPage(pageable.getPageNumber() + 1);
        response.setTotalItems(totalItems.intValue());
        response.setTotalPages(totalPages);
        return response;
    }


}
