package com.lxf.eye.show.conf;

import com.xxl.conf.core.annotation.XxlConf;
import org.springframework.stereotype.Component;

@Component
public class EyeShowConf {
    @XxlConf("default.version")
    public String version;
}
