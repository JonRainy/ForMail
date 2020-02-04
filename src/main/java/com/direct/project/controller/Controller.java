package com.direct.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.direct.project.dto.HttpResult;
import com.direct.project.service.LicenceCodeService;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;


@RestController
public class Controller {

    private final static Logger logger = LoggerFactory.getLogger(Controller.class);


    @Autowired
    LicenceCodeService licenceCodeService;

    @GetMapping(path = "/bind/licence")
    public HttpResult bindLicence(@RequestParam("licenceCode") String licenceCode,
                                  @RequestParam("deviceCode") String deviceCode) {
        try {
            Boolean res = licenceCodeService.bindLicenceCode(licenceCode, deviceCode);
            if (BooleanUtils.isFalse(res)) {
                return HttpResult.ofFail("绑定失败: licenceCode 不存在");
            }
            return HttpResult.ofSuccess("绑定成功", true);
        } catch (Exception e) {
            return HttpResult.ofFail("绑定失败: "+ e);
        }
    }

    @GetMapping(path = "/clean/licence")
    public HttpResult cleanLicence(@RequestParam("userMail") String userMail) {
        try {
            licenceCodeService.cleanLicenceCode(userMail);
            return HttpResult.ofSuccess("重新生成成功", null);
        } catch (Exception e) {
            return HttpResult.ofFail("重新生成失败: "+ e);
        }
    }


    @GetMapping(path = "/check/licence")
    public HttpResult cleanLicence(@RequestParam("licenceCode") String licenceCode,
                                   @RequestParam("deviceCode") String deviceCode) {
        try {
            Boolean res = licenceCodeService.queryLicenceCode(licenceCode, deviceCode);
            return HttpResult.ofSuccess("check success", res);
        } catch (Exception e) {
            return HttpResult.ofFail("check fail: "+ e);
        }
    }


    @GetMapping(path = "/create/licence")
    public HttpResult createLicence(@RequestParam("userMail") String userMail,
                                    @RequestParam("codeCount") Integer cnt) {
        try {
            licenceCodeService.createLicenceCode(userMail, cnt);
            return HttpResult.ofSuccess("生成成功", null);
        } catch (Exception e) {
            return HttpResult.ofFail("生成失败: "+ e);
        }
    }

    @GetMapping(path = "/count")
    public HttpResult countUser() {
        try {

            int res = licenceCodeService.countUser();
            return HttpResult.ofSuccess("", res);
        } catch (Exception e) {
            return HttpResult.ofFail(""+ e);
        }
    }
}
