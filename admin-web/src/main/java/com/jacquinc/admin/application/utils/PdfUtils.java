package com.jacquinc.admin.application.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * pdf导出工具
 */
@Component
public class PdfUtils {

    private static String openOfficeHome;

    private static String openOfficePath;

    @Value("${app.openOfficeHome}")
    public void setOpenOfficeHome(String openOfficeHome) {
        PdfUtils.openOfficeHome = openOfficeHome;
    }

    @Value("${app.openOfficePath}")
    public void setOpenOfficePath(String openOfficePath) {
        PdfUtils.openOfficePath = openOfficePath;
    }

    public static Font fontChinese24b;
    public static Font fontChinese22;
    public static Font fontChinese22b;
    public static Font fontChinese20;
    public static Font fontChinese18;
    public static Font fontChinese18b;
    public static Font fontChinese16;
    public static Font fontChinese14;
    public static Font fontChinese14b;
    public static Font fontChinese13;
    public static Font fontChinese12;

    public static void initFontChinese() throws IOException, DocumentException {
        if (fontChinese24b == null) {
            // 字体设置
            BaseFont baseFont = BaseFont.createFont("/static/font/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            fontChinese24b = new Font(baseFont, 24, Font.BOLD); //小一粗
            fontChinese22 = new Font(baseFont, 22); // 二号
            fontChinese22b = new Font(baseFont, 22, Font.BOLD); // 二号粗
            fontChinese22 = new Font(baseFont, 20);
            fontChinese18 = new Font(baseFont, 18); // 小二
            fontChinese18b = new Font(baseFont, 18, Font.BOLD); // 小二粗
            fontChinese16 = new Font(baseFont, 16); // 三号
            fontChinese14 = new Font(baseFont, 14); // 四号
            fontChinese14b = new Font(baseFont, 14, Font.BOLD); // 四号粗
            fontChinese13 = new Font(baseFont, 13); // 小四
            fontChinese12 = new Font(baseFont, 12); // 五号
        }
    }

    /** 段落
     * @param content   内容
     * @param font      字体
     * @param leading   行高
     * @param alignment 对齐
     * @param firstLineIndent 缩进（字体大小翻倍）
     * @return
     */
    public static Paragraph generatorParagraph(String content, Font font, Float leading, Integer alignment, Float firstLineIndent) {
        Paragraph paragraph = new Paragraph(content, font);
        if (leading != null) {
            paragraph.setLeading(leading);
        }
        if (alignment != null) {
            paragraph.setAlignment(alignment);
        }
        if (firstLineIndent != null) {
            paragraph.setFirstLineIndent(firstLineIndent);
        }
        return paragraph;
    }

    /**
     * 空的段落
     * @param leading
     * @param alignment
     * @param firstLineIndent
     * @return
     */
    public static Paragraph generatorEmptyParagraph(Float leading, Integer alignment, Float firstLineIndent) {
        Paragraph paragraph = new Paragraph();
        if (leading != null) {
            paragraph.setLeading(leading);
        }
        if (alignment != null) {
            paragraph.setAlignment(alignment);
        }
        if (firstLineIndent != null) {
            paragraph.setFirstLineIndent(firstLineIndent);
        }
        return paragraph;
    }

    /**
     * 最小文本
     * @param content
     * @param font
     * @param thickness
     * @param yPosition
     * @return
     */
    public static Chunk generatorChunk(String content, Font font, Float thickness, Float yPosition) {
        Chunk chunk = new Chunk(content, font);
        if (thickness != null) {
            chunk.setUnderline(thickness, yPosition);
        }
        return chunk;
    }

    /**
     * 最小文本拼接成段落
     * @param paragraph
     * @param chunks
     * @return
     */
    public static Paragraph generatorParagraphs(Paragraph paragraph, Chunk... chunks) {
        Phrase phrase = new Phrase();
        phrase.addAll(Arrays.asList(chunks));
        paragraph.add(phrase);
        return paragraph;
    }

    /**
     * table的单元格
     * @param table
     * @param font
     * @param content
     * @param colspan
     * @param rowspan
     * @param minimumHeight
     * @param horizontalAlignment
     * @return
     */
    public static PdfPCell getCell(PdfPTable table, Font font, String content, Integer colspan, Integer rowspan,
                                    Float minimumHeight, Integer horizontalAlignment, Integer verticalAlignment) {
        Phrase phrase = new Phrase(content, font);
        PdfPCell cell = new PdfPCell(phrase);
        if (colspan != null) {
            cell.setColspan(colspan);
        }
        if (rowspan != null) {
            cell.setRowspan(rowspan);
        }
        if (horizontalAlignment != null) {
            cell.setHorizontalAlignment(horizontalAlignment);
        }
        if (minimumHeight == null) {
            cell.setMinimumHeight(table.getDefaultCell().getMinimumHeight());
        } else {
            cell.setMinimumHeight(minimumHeight);
        }
        if (verticalAlignment != null) {
            cell.setVerticalAlignment(verticalAlignment);
        }
        return cell;
    }

    public static String getArrayCheck(String[] arrays, String content, Integer numberOfSpaces) {
        StringBuilder sb = new StringBuilder();
        for (String str : arrays) {
            sb.append(content == null ? "□" : content.contains(str) ? "■" : "□")
                    .append(str).append(String.format("%" + numberOfSpaces + "s", ""));
        }
        return sb.toString();
    }

    /**
     * 文档转换成pdf
     *
     * @param filePath 文档路径
     * @return 返回转换后pdf路径
     */
    public static String document2pdf(String filePath) {
        File file = new File(filePath);
        /* 转换之后的文件名 */
        File pdfFile = new File(openOfficePath + "/converter/" +
                file.getPath().split("/")[file.getPath().split("/").length - 1] + ".pdf");
        File fileParent = pdfFile.getParentFile();//返回的是File类型,可以调用exsit()等方法
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        if (pdfFile.exists()) {
            pdfFile.delete();
        }
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        configuration.setOfficeHome(new File(openOfficeHome));
        configuration.setPortNumber(8100);
        configuration.setTaskExecutionTimeout(1000 * 60);
        configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24);
        OfficeManager officeManager = configuration.buildOfficeManager();
        officeManager.start();
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        converter.getFormatRegistry();
        try {
            converter.convert(file, pdfFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            officeManager.stop();
        }
        return pdfFile.getPath();
    }

//    private static final float DEFAULT_DPI = 105;
//    private static final String DEFAULT_IMAGE_FORMAT = ".png";
//
//    /**
//     * pdf截取每页内容图片
//     *
//     * @param pdfPath       pdf路径
//     * @param imageFilePath 图片存放路径
//     * @return 返回图片数量
//     */
//    public static int pdf2image(String pdfPath, String imageFilePath) {
//        File file = new File(imageFilePath);
//        if (file.exists()) {
//            file.delete();
//        }
//        file.mkdirs();
//        BufferedImage imageResult;
//        int pages = 0;
//        try {
//            PDDocument pdDocument = PDDocument.load(new File(pdfPath));
//            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
//            pages = pdDocument.getNumberOfPages();
//            int width;
//            int shiftHeight = 0;
//            int[] singleImgRGB;
//            for (int i = 0; i < pages; i++) {
//                BufferedImage image = pdfRenderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);
//                width = image.getWidth();
//                int imageHeight = image.getHeight();
//                imageResult = new BufferedImage(width, imageHeight, BufferedImage.TYPE_INT_RGB);
//                singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
//                // 写入流中
//                imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
//                // 写图片
//                ImageIO.write(imageResult, DEFAULT_IMAGE_FORMAT.replace(".", ""),
//                        new File(imageFilePath + (i + 1) + DEFAULT_IMAGE_FORMAT));
//            }
//            pdDocument.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return pages;
//    }
}
