package com.direct.project.service;

public interface LicenceCodeService {

    Boolean bindLicenceCode(String licenceCode, String deviceCode);

    Boolean queryLicenceCode(String licenceCode, String deviceCode);

    Boolean cleanLicenceCode(String userMail);

    Boolean createLicenceCode(String userMail, Integer codeCount);

}
