package com.sun.mall.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.mall.entity.InExcel;
import com.sun.mall.utils.ExcelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sunql
 * @description controller层
 * @date 2020/12/29 15:30
 */
@Controller
@RequestMapping(value = "/excel")
public class TestController {

    @RequestMapping(value = "/index")
    public String test() {
        return "index";
    }

    @PostMapping("/importExcel")
    public void importUser(@RequestPart("file") MultipartFile file, HttpServletResponse response) throws Exception {
        List<InExcel> inExcelList = ExcelUtils.readMultipartFile(file, InExcel.class);
        List<Object> head = Arrays.asList("機能","手順","条件","テスト項目・要求ID（内容は要求仕様書参照）","組み合わせNO","合否","実施日");

        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(head);
        for (InExcel inExcel : inExcelList) {
            List<Object> excelList = new ArrayList<>();
            excelList.add("Mattermost Matterpoll 投票を削除する");
            excelList.add(inExcel.getStep());
            excelList.add(inExcel.getConditions());
            excelList.add(inExcel.getAim());
            excelList.add(inExcel.getNo());
            excelList.add("○");
            excelList.add("2022/10/13");
            sheetDataList.add(excelList);
        }
        ExcelUtils.export(response,"导出表",sheetDataList);
    }
    @PostMapping("/combination")
    public void combination(@RequestPart("file") MultipartFile file, HttpServletResponse response) throws Exception {
        List<List<String>> fList = new ArrayList<>();
        List<List<Object>> resultList;
        List<String> baseList = new ArrayList<>();
        Map<Integer, List<String>> map = ExcelUtils.getExcelMap(file);
        for(Map.Entry<Integer, List<String>> entry:map.entrySet()){
            if(entry.getKey() == 0){
                baseList = entry.getValue();
            }else{
                fList.add(entry.getValue());
            }
        }
        List<String> finalBaseList = baseList;
        Stream<String> tempStream = finalBaseList.stream();
        for (int i = 0; i < fList.size(); i++) {
            List<String> each = fList.get(i);
            tempStream =tempStream.flatMap(rEach -> each.stream().map(secStr->{
                return rEach + "/" + secStr;
            }));
        }
        baseList =  tempStream.collect(Collectors.toList());
        resultList= baseList.stream().map(s->{
            List<Object> temp=convert(Arrays.stream(s.split("/")).toList()) ;
            return   temp;
        }).collect(Collectors.toList());
        ExcelUtils.export(response,"导出表",resultList);
    }
    private static List<Object> convert(List list) {
        return list;
    }
}