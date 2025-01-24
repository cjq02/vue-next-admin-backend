/* eslint-disable */
/*
* 后端服务生成，不可修改
* */
const Enum = {
    <#list list as enumerate>

    /*
    * ${enumerate.desc}
    * */
    ${enumerate.name}: {
        <#list enumerate.codeList as item>
        /* ${item.name}<#if item.remark??> - ${item.remark}</#if> */
        ${item.key}: '${item.code}',
        </#list>
    },
    </#list>

};

export default Enum;