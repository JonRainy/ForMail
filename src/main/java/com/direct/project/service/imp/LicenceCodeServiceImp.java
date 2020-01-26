package com.direct.project.service.imp;

import com.alibaba.fastjson.JSON;
import com.direct.project.dao.LicenceCodeDao;
import com.direct.project.dto.EmailDTO;
import com.direct.project.dto.LicenceCodeDTO;
import com.direct.project.service.LicenceCodeService;
import com.direct.project.service.SendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LicenceCodeServiceImp implements LicenceCodeService {

    private final static Logger logger = LoggerFactory.getLogger(LicenceCodeServiceImp.class);

    @Autowired
    LicenceCodeDao dao;

    @Autowired
    SendMailService mailService;

    @Override
    public Boolean queryLicenceCode(String licenceCode, String deviceCode) {
        return dao.checkLiceneCode(licenceCode, deviceCode);
    }


    @Override
    public Boolean bindLicenceCode(String licenceCode, String deviceCode) {
        List<LicenceCodeDTO> empty = dao.queryLiceneCodeForUpdate(licenceCode);

        if (empty.isEmpty()) {
            return false;
        } else {
            dao.bindLiceneCode(empty.get(0).getCode(), deviceCode);
            return true;
        }
    }

    @Override
    public Boolean cleanLicenceCode(String userMail) {
        int num = dao.countByUserMail(userMail);
        if (num == 0) {
            return true;
        }

        dao.deleteByUserMail(userMail);
        return createLicenceCode(userMail, num);
    }

    @Override
    public Boolean createLicenceCode(String userMail, Integer codeCount) {

        List<LicenceCodeDTO> licenceCodeDTOs = new LinkedList<>();
        for (int i=0; i<codeCount; i++) {
            LicenceCodeDTO licenceCodeDTO = new LicenceCodeDTO();
            licenceCodeDTO.setCode(UUID.randomUUID().toString());
            licenceCodeDTO.setUserMail(userMail);

            licenceCodeDTOs.add(licenceCodeDTO);
        }

        dao.insert(licenceCodeDTOs);


        List<String> codes = licenceCodeDTOs.stream().map(LicenceCodeDTO::getCode)
                .collect(Collectors.toList());
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setContent(JSON.toJSONString(codes));
        emailDTO.setSendTo(userMail);
        mailService.sendMail(emailDTO);
        return true;
    }


}
