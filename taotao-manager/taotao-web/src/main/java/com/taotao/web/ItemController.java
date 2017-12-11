package com.taotao.web;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ItemController {

    @Autowired
    ItemService service;
    private List<TbItem> outToExcel;
    private ArrayList<TbItem> list;
    private SimpleDateFormat dateFormat;

    private TbItem tbItem;
    private FileOutputStream fout;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCellStyle style;
    private HSSFCell cell;


    //添加商品
    @RequestMapping("/item/save")
    @ResponseBody
    public Map<String, Object> saveItem(TbItem item, String desc, String itemParams) {
        //System.out.println(itemParams);
        service.saveItem ( item, desc, itemParams );
        //service.saveItem(item,desc,itemParams);
        Map<String, Object> map = new HashMap<> ();
        map.put ( "status", 200 );
        return map;
    }

    //删除商品
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public Map<String, Object> deleteItem(String ids) {
     //   System.out.println ( ids );
        service.deleteItem ( ids );
        Map<String, Object> map = new HashMap<> ();
        map.put ( "status", 200 );
        return map;
    }

    //修改商品
    @RequestMapping("rest/page/item-edit")
    public String editItem() {

        return "item-edit";
    }

    //查询商品描述
    @RequestMapping("/rest/item/query/item/desc/{id}")
    @ResponseBody
    public Map<String, Object> queryItemDescByItemId(@PathVariable long id) {
        //System.out.println(id);
        TbItemDesc desc = service.queryItemDescByItemId ( id );
        //System.out.println("desc"+desc);
        Map<String, Object> map = new HashMap<> ();
        map.put ( "status", 200 );
        map.put ( "data", desc );
        return map;
    }

    //修改商品
    @RequestMapping("/rest/item/update")
    @ResponseBody
    public Map<String, Object> updateItem(TbItem item, String desc, String itemParams) {
     //   System.out.println ( item );
     //   System.out.println ( desc );
     //   System.out.println ( itemParams );
     //   System.out.println ( 123 );

        service.updateItem ( item, desc, itemParams );

        Map<String, Object> map = new HashMap<> ();
        map.put ( "status", 200 );
        return map;
    }

    //商品下架
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public Map<String, Object> updateItemStatus(String ids) {
        //System.out.println(ids);
        service.updateItemStatus ( ids );
        Map<String, Object> map = new HashMap<> ();
        map.put ( "status", 200 );
        return map;
    }

    //商品上架
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public Map<String, Object> updateItemStatusToOne(String ids) {
        //System.out.println(ids);
        service.updateItemStatusToOne ( ids );
        Map<String, Object> map = new HashMap<> ();
        map.put ( "status", 200 );
        return map;
    }

    //删除商品
//	@RequestMapping("/rest/item/delete")
//	@ResponseBody
//	public Map<String , Object> deleteItem(String ids){
//		System.out.println(ids);
//		service.deleteItem(ids);
//		Map<String , Object> map = new HashMap<>();
//		map.put("status", 200);
//		return map;
//	}
    //导出商品
    @RequestMapping("/rest/item/outToExcel")
    @ResponseBody
    public Map<String, Object> outToExcel() {
        Map<String, Object> map = new HashMap<> ();
        outToExcel = service.outToExcel ();
        list = new ArrayList<> ();
        dateFormat = new SimpleDateFormat ( "yyyy-MM-dd" );
//		for (TbItem tbItem : outToExcel) {
//			list.add ( tbItem );
//		}
        wb = new HSSFWorkbook ();
        sheet = wb.createSheet ( "学生表一" );
        row = sheet.createRow ( 0 );
        style = wb.createCellStyle ();

        //  style.setAlignment ( XSSFCellStyle.ALIGN_CENTER );
        cell = row.createCell ( 0 );
        cell.setCellValue ( "商品ID" );
        cell.setCellStyle ( style );
        cell = row.createCell ( 1 );
        cell.setCellValue ( "商品标题" );
        cell.setCellStyle ( style );
		cell = row.createCell ( 2 );
		cell.setCellValue ( "叶子类目" );
		cell.setCellStyle ( style );
        cell = row.createCell ( 3 );
        cell.setCellValue ( "卖点" );
        cell.setCellStyle ( style );
        cell = row.createCell ( 4 );
        cell.setCellValue ( "价格" );
        cell.setCellStyle ( style );
        cell = row.createCell ( 5 );
        cell.setCellValue ( "库存数量" );
        cell.setCellStyle ( style );
        cell = row.createCell ( 6 );
        cell.setCellValue ( "条形码" );
        cell.setCellStyle ( style );
        cell = row.createCell ( 7 );
		cell.setCellValue ( "状态" );
		cell.setCellStyle ( style );
		cell = row.createCell ( 8 );
        cell.setCellValue ( "创建日期" );
        cell.setCellStyle ( style );
        cell = row.createCell ( 9 );
        cell.setCellValue ( "更新日期" );
        cell.setCellStyle ( style );

//写入数据库得到的实体数据
        for (int i = 0; i < outToExcel.size (); i++) {
            row = sheet.createRow ( i + 1 );
            tbItem = outToExcel.get ( i );
            row.createCell ( 0 ).setCellValue ( tbItem.getId () );
            row.createCell ( 1 ).setCellValue ( tbItem.getTitle () );
            row.createCell ( 2 ).setCellValue (tbItem.getCid () );
            row.createCell ( 3 ).setCellValue ( tbItem.getSellPoint () );
            row.createCell ( 4 ).setCellValue ( tbItem.getPrice () );
            row.createCell ( 5 ).setCellValue ( tbItem.getNum () );
            row.createCell ( 6 ).setCellValue ( tbItem.getBarcode () );
            row.createCell ( 7 ).setCellValue (  tbItem.getStatus ());
            row.createCell ( 8 ).setCellValue ( dateFormat.format ( tbItem.getCreated () ) );
            row.createCell ( 9 ).setCellValue ( dateFormat.format ( tbItem.getUpdated () ) );

        }
        try {
            fout = new FileOutputStream ( "/Volumes/D/Text/a.xls" );

            wb.write ( fout );
            fout.close ();
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        map.put ( "status", 500 );
        return map;
    }


}
