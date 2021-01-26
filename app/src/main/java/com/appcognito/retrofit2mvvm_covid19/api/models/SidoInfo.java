package com.appcognito.retrofit2mvvm_covid19.api.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="response", strict = false)
public class SidoInfo {
    @Element(name="header")
    public Header header;
    @Element(name="body")
    public SidoBody body;

    @Root(name="header", strict = false)
    public static class Header {
        @Element(name = "resultCode")
        public String resultCode;
        @Element(name = "resultMsg")
        public String resultMsg;
    }

    @Root(name="body", strict = false)
    public static class SidoBody {
        @ElementList(entry = "items")
        public List<SidoInfo.SidoItem> items;
        //    public SidoList items;
        @Element(name="numOfRows")
        public String numOfRows;
        @Element(name="pageNo")
        public String pageNo;
        @Element(name="totalCount")
        public String totalCount;

    }

    @Root(name="item", strict = false)
    public static class SidoItem {
        @Element(name = "createDt", required = false)
        public String createDt;
        @Element(name = "deathCnt", required = false)
        public String deathCnt;
        @Element(name = "defCnt", required = false)
        public String defCnt;
        @Element(name = "gubun", required = false)
        public String gubun;
        @Element(name = "gubunCn", required = false)
        public String gubunCn;
        @Element(name = "gubunEn", required = false)
        public String gubunEn;
        @Element(name = "incDec", required = false)
        public String incDec;
        @Element(name = "isolClearCnt", required = false)
        public String isolClearCnt;
        @Element(name = "isolIngCnt", required = false)
        public String isolIngCnt;
        @Element(name = "localOccCnt", required = false)
        public String localOccCnt;
        @Element(name = "overFlowCnt", required = false)
        public String overFlowCnt;
        @Element(name = "qurRate", required = false)
        public String qurRate;
        @Element(name = "seq", required = false)
        public String seq;
        @Element(name = "stdDay", required = false)
        public String stdDay;
        @Element(name = "updateDt", required = false)
        public String updateDt;

    }
}


