package com.jacquinc.admin.application.utils;

import com.google.common.collect.Maps;
import com.jacquinc.admin.utils.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@SuppressWarnings("unchecked")
public class HtmlToExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(HtmlToExcelUtil.class);

    private final static String FONT_NAME = "宋体";
    private final static String FONT_SIZE_ATTR = "font-size";
    private final static short FONT_SIZE_12 = 12;
    private final static String TEXT_ALIGN = "text-align";
    private final static String BACKGROUND_COLOR = "background-color";
    private final static String FONT_WEIGHT = "font-weight";

    /**
     * html表格转excel
     *
     * @param tableHtml 如
     *                  <table>
     *                  ..
     *                  </table>
     */
    public static HSSFWorkbook table2Excel(String tableHtml) {
        Map<String, HSSFCellStyle> styleMap = new HashMap<>();
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFCellStyle titleStyle = getTitleStyle(wb);
        if (!styleMap.containsKey("title")) {
            styleMap.put("title", titleStyle);
        }
        HSSFCellStyle contentStyle = getContentStyle(wb);
        if (!styleMap.containsKey("content")) {
            styleMap.put("content", contentStyle);
        }

        HSSFSheet sheet = wb.createSheet();
        sheet.setDefaultRowHeight((short) 800);
        List<CrossRangeCellMeta> crossRowEleMetaList = new ArrayList<CrossRangeCellMeta>();
        int rowIndex = 0;
        List<Element> theadLs = new ArrayList<>();
        try {
            tableHtml = tableHtml.replaceAll("&", "&amp;");
            Document data = DocumentHelper.parseText(tableHtml);
            // 生成表头
            Element table = data.getRootElement();
            if (table != null) {
                Element thead = table.element("thead");
                if (thead != null) {
                    List<Element> trLs = thead.elements("tr");
                    for (Element trEle : trLs) {
                        HSSFRow row = sheet.createRow(rowIndex);
                        sheet.setColumnWidth(rowIndex, 4000);
                        List<Element> thLs = trEle.elements("th");
                        if (theadLs.size() == 0) {
                            theadLs = new ArrayList<>(thLs);
                        }
                        makeRowCell(wb, thLs, rowIndex, row, 0, "title", crossRowEleMetaList, styleMap);
                        rowIndex++;
                    }
                }
                // 生成表体
                Element tbody = table.element("tbody");
                if (tbody != null) {
                    List<Element> trLs = tbody.elements("tr");
                    for (Element trEle : trLs) {
                        HSSFRow row = sheet.createRow(rowIndex);
                        sheet.setColumnWidth(rowIndex, 4000);
                        List<Element> thLs = trEle.elements("th");
                        int cellIndex = makeRowCell(wb, thLs, rowIndex, row, 0, "title", crossRowEleMetaList, styleMap);
                        List<Element> tdLs = trEle.elements("td");
                        makeRowCell(wb, tdLs, rowIndex, row, cellIndex, "content", crossRowEleMetaList, styleMap);
                        rowIndex++;
                    }
                }
                // 合并表头
                for (CrossRangeCellMeta crossRowEleMeta : crossRowEleMetaList) {
                    CellRangeAddress cra = new CellRangeAddress(crossRowEleMeta.getFirstRow(), crossRowEleMeta.getLastRow(), crossRowEleMeta.getFirstCol(), crossRowEleMeta.getLastCol());
                    RegionUtil.setBorderTop(BorderStyle.THIN, cra, sheet);
                    RegionUtil.setBorderBottom(BorderStyle.THIN, cra, sheet);
                    RegionUtil.setBorderLeft(BorderStyle.THIN, cra, sheet);
                    RegionUtil.setBorderRight(BorderStyle.THIN, cra, sheet);
                    sheet.addMergedRegion(cra);
                }
            }
            List<Element> labels = data.getRootElement().elements("label");
            if (labels != null && labels.size() > 0) {
                HSSFRow row = sheet.createRow(rowIndex);
                int cellIndex = 0;
                for (Element element : labels) {
                    HSSFCell cell = row.createCell(cellIndex);
                    cell.setCellStyle(contentStyle);
                    cell.setCellValue(element.getStringValue());
                    cellIndex++;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return wb;
    }

    /**
     * @param thLs              th或者td集合
     * @param rowIndex          行号
     * @param row               POI行对象
     * @param styleKey         样式
     * @param crossRowEleMetaLs 跨行元数据集合
     * @return 整型
     */
    private static int makeRowCell(HSSFWorkbook workbook, List<Element> thLs, int rowIndex, HSSFRow row, int startCellIndex,
                                   String styleKey, List<CrossRangeCellMeta> crossRowEleMetaLs, Map<String, HSSFCellStyle> styleMap) {
        int i = startCellIndex;
        for (int eleIndex = 0; eleIndex < thLs.size(); i++, eleIndex++) {
            int captureCellSize = getCaptureCellSize(rowIndex, i, crossRowEleMetaLs);
            while (captureCellSize > 0) {
                for (int j = 0; j < captureCellSize; j++) {// 当前行跨列处理（补单元格）
                    row.createCell(i);
                    i++;
                }
                captureCellSize = getCaptureCellSize(rowIndex, i, crossRowEleMetaLs);
            }
            Element tdEle = thLs.get(eleIndex);
            String val = tdEle.getStringValue();
            if (StringUtils.isBlank(val)) {
                Element e = tdEle.element("a");
                if (e != null) {
                    val = e.getTextTrim();
                }
                e = tdEle.element("label");
                if (e != null) {
                    val = e.getTextTrim();
                }
            }
            HSSFCell c = row.createCell(i);
            String dataType = tdEle.attributeValue("data-type");
            // 表格嵌套处理
            if (tdEle.element("table") != null && tdEle.element("table").element("tbody").elements().size() > 0) {
                StringBuilder cellValue = new StringBuilder();
                for (int tableIndex = 0; tableIndex < tdEle.element("table").element("tbody").elements().size(); tableIndex++) {
                    Element tableTrEle = (Element) tdEle.element("table").element("tbody").elements().get(tableIndex);
                    if (tableIndex == 0) {
                        cellValue = new StringBuilder(tableTrEle.element("td").getText());
                    } else {
                        cellValue.append("\r\n").append(tableTrEle.element("td").getText());
                    }
                }
                c.setCellValue(cellValue.toString());
            } else {
                if (StringUtils.isNotEmpty(dataType) && dataType.equals("1")) {
                    c.setCellValue(val);
                } else {
                    if (NumberUtils.isCreatable(val)) {
                        c.setCellValue(Double.parseDouble(val));
                        c.setCellType(CellType.NUMERIC);
                    } else {
                        c.setCellValue(val);
                    }
                }
            }
            c.setCellStyle(handleCellStyle(workbook, tdEle, styleKey, styleMap));

            int rowSpan = NumberUtils.toInt(tdEle.attributeValue("rowspan") == null ? tdEle.attributeValue("rowSpan") : tdEle.attributeValue("rowspan"), 1);
            int colSpan = NumberUtils.toInt(tdEle.attributeValue("colspan") == null ? tdEle.attributeValue("colSpan") : tdEle.attributeValue("colspan"), 1);
            if (rowSpan > 1 || colSpan > 1) { // 存在跨行或跨列

                crossRowEleMetaLs.add(new CrossRangeCellMeta(rowIndex, i, rowSpan, colSpan));
            }
            if (colSpan > 1) {// 当前行跨列处理（补单元格）
                for (int j = 1; j < colSpan; j++) {
                    i++;
                    row.createCell(i);
                }
            }
        }
        return i;
    }

    /**
     * 获得因rowSpan占据的单元格
     *
     * @param rowIndex          行号
     * @param colIndex          列号
     * @param crossRowEleMetaLs 跨行列元数据
     * @return 当前行在某列需要占据单元格
     */
    private static int getCaptureCellSize(int rowIndex, int colIndex, List<CrossRangeCellMeta> crossRowEleMetaLs) {
        int captureCellSize = 0;
        for (CrossRangeCellMeta crossRangeCellMeta : crossRowEleMetaLs) {
            if (crossRangeCellMeta.getFirstRow() < rowIndex && crossRangeCellMeta.getLastRow() >= rowIndex) {
                if (crossRangeCellMeta.getFirstCol() <= colIndex && crossRangeCellMeta.getLastCol() >= colIndex) {
                    captureCellSize = crossRangeCellMeta.getLastCol() - colIndex + 1;
                }
            }
        }
        return captureCellSize;
    }

    private static HSSFCellStyle handleCellStyle(HSSFWorkbook wb, Element tdEle, String styleKey, Map<String, HSSFCellStyle> styleMap) {
        String tdStyle = tdEle.attributeValue("style"), extendKey = "";
        if (!StringUtils.isEmpty(tdStyle)) {
            String[] tdStyleList = StringUtils.split(tdStyle, ";");
            Map<String, String> map = Maps.newHashMap();
            for (String str : tdStyleList) {
                if (StringUtils.isEmpty(str.trim())) {
                    continue;
                }
                String[] styleAttr = StringUtils.split(str, ":");
                map.put(styleAttr[0].trim(), styleAttr[1]);
            }
            if (map.containsKey(TEXT_ALIGN)) {
                extendKey += TEXT_ALIGN;
            }
            if (map.containsKey(BACKGROUND_COLOR)) {
                extendKey += BACKGROUND_COLOR;
            }
            if (map.containsKey(FONT_WEIGHT)) {
                extendKey += FONT_WEIGHT;
            }
            if (map.containsKey(FONT_SIZE_ATTR)) {
                extendKey += FONT_SIZE_ATTR;
            }
            if (StringUtils.isEmpty(extendKey)) {
                return styleMap.get(styleKey);
            } else {
                if (styleMap.containsKey(styleKey + "_" + extendKey)) {
                    return styleMap.get(styleKey + "_" + extendKey);
                }
            }
            HSSFCellStyle cloneCellStyle = cloneCellStyleFrom(wb, styleMap.get(styleKey));
            for (String str : tdStyleList) {
                if (StringUtils.isEmpty(str.trim())) {
                    continue;
                }
                String[] styleAttr = StringUtils.split(str, ":");
                map.put(styleAttr[0].trim(), styleAttr[1]);
            }
            if (map.containsKey(TEXT_ALIGN)) {
                String textAlign = map.get(TEXT_ALIGN).trim();
                cloneCellStyle.setAlignment(getAlignMap().get(textAlign));
            }
            if (map.containsKey(BACKGROUND_COLOR)) {
                String backgroundColor = map.get(BACKGROUND_COLOR).trim();
                HSSFColor color = getColor(wb, backgroundColor);
                cloneCellStyle.setFillForegroundColor(color.getIndex());
                cloneCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
            if (map.containsKey(FONT_WEIGHT)) {
                HSSFFont font = cloneCellStyle.getFont(wb);
                font.setBold(true);
            }
            if (map.containsKey(FONT_SIZE_ATTR)) {
                String fontSize = map.get(FONT_SIZE_ATTR).trim();
                HSSFFont font = cloneCellStyle.getFont(wb);
                font.setFontHeightInPoints(Short.parseShort(fontSize.replace("px", "")));
            }
            styleMap.put(styleKey + "_" + extendKey, cloneCellStyle);
            return cloneCellStyle;
        }
        return styleMap.get(styleKey);
    }

    public static HSSFColor getColor(HSSFWorkbook workbook, String color) {
        List<String> colors = Arrays.asList(color.replace("rgb(", "").replace(")", "").split(","));
        byte r = (byte) Integer.parseInt(colors.get(0).trim());
        byte g = (byte) Integer.parseInt(colors.get(1).trim());
        byte b = (byte) Integer.parseInt(colors.get(2).trim());
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor hssfColor = null;
        try {
            hssfColor = palette.findColor(r, g, b);
            if (hssfColor == null) {
                palette.setColorAtIndex(HSSFColor.HSSFColorPredefined.LAVENDER.getIndex(), r, g, b);
                hssfColor = palette.getColor(HSSFColor.HSSFColorPredefined.LAVENDER.getIndex());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return hssfColor;
    }

    private static Map<String, HorizontalAlignment> getAlignMap() {
        Map<String, HorizontalAlignment> textAlignMap = Maps.newHashMap();
        textAlignMap.put("left", HorizontalAlignment.LEFT);
        textAlignMap.put("center", HorizontalAlignment.CENTER);
        textAlignMap.put("right", HorizontalAlignment.RIGHT);
        return textAlignMap;
    }

    private static HSSFCellStyle cloneCellStyleFrom(HSSFWorkbook workbook, HSSFCellStyle cellStyle) {
        HSSFCellStyle hssfCellStyle;
        hssfCellStyle = workbook.createCellStyle();
        hssfCellStyle.cloneStyleFrom(cellStyle);
        HSSFFont font = workbook.createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeightInPoints(FONT_SIZE_12);
        hssfCellStyle.setFont(font);
        return hssfCellStyle;
    }

    /**
     * 获得标题样式
     *
     * @param workbook 工作簿
     * @return 样式
     */
    private static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
        short titleBackGroundColor = HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex();
        HSSFCellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setWrapText(true);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(titleBackGroundColor);// 背景色
        HSSFFont font = workbook.createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeightInPoints(FONT_SIZE_12);
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * 获得内容样式
     *
     * @param wb 工作簿
     * @return 样式
     */
    private static HSSFCellStyle getContentStyle(HSSFWorkbook wb) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setWrapText(true);
        HSSFFont font = wb.createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeightInPoints(FONT_SIZE_12);
        style.setFont(font);
        return style;
    }

    /**
     * 跨行元素元数据
     */
    public static class CrossRangeCellMeta {

        public CrossRangeCellMeta(int firstRowIndex, int firstColIndex, int rowSpan, int colSpan) {
            super();
            this.firstRowIndex = firstRowIndex;
            this.firstColIndex = firstColIndex;
            this.rowSpan = rowSpan;
            this.colSpan = colSpan;
        }

        private int firstRowIndex;
        private int firstColIndex;
        private int rowSpan;// 跨越行数
        private int colSpan;// 跨越列数

        int getFirstRow() {
            return firstRowIndex;
        }

        int getLastRow() {
            return firstRowIndex + rowSpan - 1;
        }

        int getFirstCol() {
            return firstColIndex;
        }

        int getLastCol() {
            return firstColIndex + colSpan - 1;
        }

        int getColSpan() {
            return colSpan;
        }
    }
}
