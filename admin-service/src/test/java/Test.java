public class Test {

    public static void main(String[] args) {
        String oldSql = " SELECT t.*, c.name as \"corp_name\", c.id as \"corp_id\", (select string_agg(r.name, ',')\n" +
                " FROM t_jj_sys_user_role ur\n" +
                " LEFT JOIN t_jj_sys_role r\n" +
                " ON r.id = ur.role_id\n" +
                " WHERE ur.user_id = t.id )as role_name\n" +
                " FROM t_jj_sys_user t\n" +
                " LEFT JOIN t_jj_sys_corp c\n" +
                " ON c.id = t.corp_id\n" +
                " WHERE 1=1 AND COALESCE(t.del_flag, '0') = '0' ORDER BY t.create_ts DESC NULLS LAST\n" +
                " LIMIT ? offset (?-1)";
        String newSql = null;
        String limit = null;
        int orderByIdx = oldSql.toLowerCase().indexOf("order by");
        int limitIdx = oldSql.toLowerCase().indexOf("limit");
        String limitStr = oldSql.substring(limitIdx);
        if (orderByIdx > 0) {
            newSql = oldSql.substring(0, orderByIdx);
        }
        String sortBy = "corp_name";
        String sortType = "desc";
        newSql = String.format("select * from (%s) t order by %s %s %s", newSql, sortBy, sortType, limitStr);
        System.out.println(newSql);
    }

}
