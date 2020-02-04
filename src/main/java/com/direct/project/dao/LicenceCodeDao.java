package com.direct.project.dao;

import com.direct.project.dto.LicenceCodeDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenceCodeDao {

    void insert(@Param("lcodes")List<LicenceCodeDTO> licenceCodeDTOs);

    Integer countByUserMail(@Param("userMail") String userMail);

    Integer deleteByUserMail(@Param("userMail") String user);

    void bindLiceneCode(@Param("code")String code, @Param("deviceCode")String deviceCode);

    List<LicenceCodeDTO> queryLiceneCodeForUpdate(@Param("code")String user);

    Boolean checkLiceneCode(@Param("code")String code, @Param("deviceCode")String deviceCode);

    Integer countUser();

}
