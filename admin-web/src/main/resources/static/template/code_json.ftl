{
  <#list list as enumerate>
  "${enumerate.name}": {
    "typeName": "${enumerate.desc}",
    "list": [
      <#list enumerate.codeList as item>
      {
        "configCode": "${item.configCode}",
        "configName": "${item.configName?js_string}",
        "remark": "<#if item.remark??>${item.remark?js_string}</#if>"
      }<#sep>,</#sep>
      </#list>
    ]
  }<#sep>,</#sep>
  </#list>
}
