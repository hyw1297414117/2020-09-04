package com.common.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PDFItextUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(PDFItextUtils.class);

    // 标准字体
    public static Font NORMALFONT;
    // 加粗字体
    public static Font BOLDFONT;
    //固定高
    public static float fixedHeight = 27f;
    //间距
    public static int spacing = 5;

    static {
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            NORMALFONT = new Font(bfChinese, 10, Font.NORMAL);
            BOLDFONT = new Font(bfChinese, 14, Font.BOLD);
        } catch (Exception e) {
            LOGGER.error("设置字体错误！错误信息：{}", e);
            e.printStackTrace();
        }

    }

    /**
     * 新建标准段落
     * @param text 段落内容
     * @return
     */
    public static Paragraph createNormalParagraph(String text){
        Paragraph elements = new Paragraph(text, NORMALFONT);
        elements.setSpacingAfter(spacing);
        return elements;
    }

    /**
     * 新建加粗段落
     * @param text	段落内容
     * @return
     */
    public static Paragraph createBoldParagraph(String text) {
        Paragraph elements = new Paragraph(text, BOLDFONT);
        elements.setSpacingAfter(spacing);
        elements.setAlignment(Paragraph.ALIGN_CENTER);
        return elements;
    }

    /**
     * 隐藏表格边框线
     * @param cell	单元格
     */
    public static void disableBorderSide(PdfPCell cell){
        if (cell != null) {
            cell.disableBorderSide(1);
            cell.disableBorderSide(2);
            cell.disableBorderSide(4);
            cell.disableBorderSide(8);
        }
    }

    /**
     * 创建固定高度得单元格
     * @return
     */
    public static PdfPCell createPdfPCell() {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(fixedHeight);
        return cell;
    }

    /**
     * 创建居中得单元格
     * @return
     */
    public static PdfPCell createCenterPdfPCell() {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(fixedHeight);
        return cell;
    }

    /**
     * 创建指定背景颜色得单元格
     * @param color
     * @return
     */
    public static PdfPCell createCenterPdfPCell(BaseColor color) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(fixedHeight);
        cell.setBackgroundColor(color);
        return cell;
    }

    /**
     * 创建指定文字得单元格
     * @param text
     * @return
     */
    public static PdfPCell createCenterPdfPCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, NORMALFONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(fixedHeight);
        return cell;
    }

    /**
     * 创建指定背景颜色和文字得单元格
     * @param text
     * @return
     */
    public static PdfPCell createCenterPdfPCell(String text, BaseColor color) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, NORMALFONT));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(fixedHeight);
        cell.setBackgroundColor(color);
        return cell;
    }

    /**
     * 创建指定宽度得表格
     * @param widths	列宽数组
     * @return
     */
    public static PdfPTable createPdfPTable(float[] widths) {
        PdfPTable pdfPTable = new PdfPTable(widths);
        pdfPTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        return pdfPTable;
    }

    /**
     * 创建居左表格
     * @param len	表格列数
     * @return
     */
    public static PdfPTable createLeftPdfPTable(int len) {
        PdfPTable pdfPTable = new PdfPTable(len);
        pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        return pdfPTable;
    }

    /**
     * 创建居左表格
     * @param len	表格列数
     * @return
     */
    public static PdfPTable createLeftPdfPTable(float[] columnWidths) {
        PdfPTable pdfPTable = new PdfPTable(columnWidths);
        pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        return pdfPTable;
    }

    /**
     * 往指定pdf文件中加入虚线分割线
     * @param document
     * @throws DocumentException
     */
//    public static void createPdfPTableSplitLine(Document document) throws DocumentException {
//        //CustomCell 为自制表格
//        CustomCell border = new CustomCell();
//        PdfPTable table = new PdfPTable(1);
//        table.setSpacingAfter(5f);
//        table.setWidthPercentage(100);
//        PdfPCell cell = new PdfPCell(new Phrase("表格作为分割线"));
//        cell.setFixedHeight(10f);
//        cell.setBorder(Rectangle.NO_BORDER);
//        cell.setCellEvent(border);
//        table.addCell(cell);
//        document.add(table);
//    }

}
