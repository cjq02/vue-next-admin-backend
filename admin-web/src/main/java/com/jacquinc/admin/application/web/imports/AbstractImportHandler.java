package com.jacquinc.admin.application.web.imports;

import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.exception.BusinessException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author cjq
 * created on  2020/09/01
 */
@SuppressWarnings("ALL")
public abstract class AbstractImportHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 导入
     *
     * @param sheet 工作簿
     * @param user  user
     */
    public abstract void execute(HttpServletRequest request, Sheet sheet, String currentUserId);

    protected boolean emptyRowCheck(Row row) {
        boolean blankFlag = true;
        if (null == row) {
            return blankFlag;
        }
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                blankFlag = false;
                break;
            }
        }
        // 判断整行是否为空，是的话跳出外层循环
        return blankFlag;
    }

    protected String getCell(Row row, List<Object> colList) {
        try {
            if (getRequired(colList)) {
                return Optional.ofNullable(getCellValue(row.getCell(getIndex(colList))))
                        .orElseThrow(() -> new BusinessException(getName(colList) + "不能为空"));
            }
            return Optional.ofNullable(getCellValue(row.getCell(getIndex(colList)))).orElse("");
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    protected Date getDateCell(Row row, List<Object> colList) {
        try {
            if (getRequired(colList)) {
                if (null == row.getCell(getIndex(colList))) {
                    throw new BusinessException(getName(colList) + "不能为空");
                }
                return row.getCell(getIndex(colList)).getDateCellValue();
            }
            Cell cell = row.getCell(getIndex(colList));
            if (null == cell || (CellType.STRING == cell.getCellTypeEnum() && StringUtils.isEmpty(cell.getStringCellValue()))) {
                return null;
            }
            return cell.getDateCellValue();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    protected BigDecimal getBigDecimal(Row row, List<Object> colList) {
        try {
            if (getRequired(colList)) {
                if (null == row.getCell(getIndex(colList)) || 0 == row.getCell(getIndex(colList)).getNumericCellValue()) {
                    throw new BusinessException(getName(colList) + "不能为空，不能是0");
                }
                return new BigDecimal(row.getCell(getIndex(colList)).getNumericCellValue());
            }
            Cell cell = row.getCell(getIndex(colList));
            if (null == cell || (CellType.STRING == cell.getCellTypeEnum() && StringUtils.isEmpty(cell.getStringCellValue()))) {
                return null;
            }
            return new BigDecimal(cell.getNumericCellValue());
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 数值类型判断null的特殊处理
     * 需要先将单元格转成字符串类型，判断完后再转回数值类型
     * @param row
     * @param colList
     * @return
     */
    protected BigDecimal getBigDecimalWithZero(Row row, List<Object> colList) {
        try {
            if (getRequired(colList)) {
                if (null == row.getCell(getIndex(colList)) || CellType.BLANK == row.getCell(getIndex(colList)).getCellTypeEnum()) {
                    throw new BusinessException(getName(colList) + "不能为空");
                }
                return new BigDecimal(row.getCell(getIndex(colList)).getNumericCellValue());
            }
            Cell cell = row.getCell(getIndex(colList));
            if (null == cell || (CellType.STRING == cell.getCellTypeEnum() && StringUtils.isEmpty(cell.getStringCellValue()))) {
                return null;
            }
            return new BigDecimal(cell.getNumericCellValue());
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    protected Integer getInteger(Row row, List<Object> colList) {
        try {
            if (getRequired(colList)) {
                if (null == row.getCell(getIndex(colList)) || 0 == row.getCell(getIndex(colList)).getNumericCellValue()) {
                    throw new BusinessException(getName(colList) + "不能为空");
                }
                return Double.valueOf(row.getCell(getIndex(colList)).getNumericCellValue()).intValue();
            }
            Cell cell = row.getCell(getIndex(colList));
            if (null == cell || (CellType.STRING == cell.getCellTypeEnum() && StringUtils.isEmpty(cell.getStringCellValue()))) {
                return null;
            }
            return Double.valueOf(cell.getNumericCellValue()).intValue();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    protected Integer getIntegerWithZero(Row row, List<Object> colList) {
        try {
            if (getRequired(colList)) {
                if (null == row.getCell(getIndex(colList)) || CellType.BLANK == row.getCell(getIndex(colList)).getCellTypeEnum()) {
                    throw new BusinessException(getName(colList) + "不能为空");
                }
                Cell cell = row.getCell(getIndex(colList));
                return Double.valueOf(row.getCell(getIndex(colList)).getNumericCellValue()).intValue();
            }
            Cell cell = row.getCell(getIndex(colList));
            if (null == cell || (CellType.STRING == cell.getCellTypeEnum() && StringUtils.isEmpty(cell.getStringCellValue()))) {
                return null;
            }
            return Double.valueOf(cell.getNumericCellValue()).intValue();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    protected Integer getIndex(List<Object> colList) {
        return (int)colList.get(0);
    }

    protected String getName(List<Object> colList) {
        return colList.get(1).toString();
    }

    protected Boolean getRequired(List<Object> colList) {
        return colList.size() == 2 ? false : (boolean)colList.get(2);
    }

    private String getCellValue(Cell cell) {
        String cellValue = null;

        if (cell != null) {
            if (CellType.STRING == cell.getCellTypeEnum()) {
                cellValue = cell.getStringCellValue();
            } else if (CellType.NUMERIC == cell.getCellTypeEnum()) {
                cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
            }
        }
        return cellValue;
    }

    // 导入表格头部列名校验
    public void checkHead(String name, Sheet sheet) throws ClassNotFoundException, IllegalAccessException, BusinessException {
        Class<?> clazz = Class.forName("com.jacquinc.admin.application.web.imports." + name + "ImportHandler");
        Class<?>[] innerClasses = clazz.getDeclaredClasses();
        for (Class<?> innerClass : innerClasses) {
            if (Modifier.isStatic(innerClass.getModifiers())) {
                for (Field field : innerClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    List<Object> colMap = (List<Object>) field.get(innerClass);
                    String colMapName = getName(colMap);
                    String excelColName = sheet.getRow(0).getCell(getIndex(colMap)).getStringCellValue();
                    if (excelColName.startsWith("*")) {
                        excelColName = excelColName.substring(1);
                    }
                    if (!colMapName.equals(excelColName)) {
                        throw new BusinessException("模版错误，请下载模版重试！");
                    }
                }
            }
        }
    }
}
