package com.jacquinc.admin.common.web;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jacquinc.admin.application.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common/api")
@ApiSort(2)
@Api(tags = "公共接口")
public class CommonApiController extends BaseController {

}
