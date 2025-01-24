/* eslint-disable */
/*
* 后端服务生成，不可修改
* */
const Code = {
    <#list list as enumerate>

    /*
    * ${enumerate.desc}
    * */
    ${enumerate.name}: {
        <#list enumerate.codeList as item>
        /* ${item.configName?js_string}<#if item.remark??> - ${item.remark?js_string}</#if> */
        '<#if item.configCode?matches("^[0-9]*$")>_</#if>${item.configCode}': '${item.configCode}',
        </#list>
    },
    </#list>

};

export default Code;
