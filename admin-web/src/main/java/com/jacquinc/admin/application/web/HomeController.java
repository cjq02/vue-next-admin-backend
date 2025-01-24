package com.jacquinc.admin.application.web;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@ApiSort(4)
@Api(tags = "首页")
public class HomeController extends BaseController {


}
