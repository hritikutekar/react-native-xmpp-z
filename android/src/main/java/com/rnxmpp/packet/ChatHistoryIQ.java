package com.rnxmpp.packet;

import org.jivesoftware.smack.packet.IQ;

public class ChatHistoryIQ extends IQ {

    public static final String NAMESPACE = "fd:im:singleChat";
    public static final String ELEMENT = "query";
    public static final String ACTION_TYPE = "getChat";
    //    String action;
    String startTime;
    String limit;
    String otherUser;
    String strResult;
    String strSize;
    boolean result;
    private int size;

    public ChatHistoryIQ() {
        super(ELEMENT,NAMESPACE);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(String otherUser) {
        this.otherUser = otherUser;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
        this.strResult = String.valueOf(result);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        this.strSize = String.valueOf(size);
    }

    @Override
    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder xml) {
        xml.append(">");
        xml.append("<action>").append(ACTION_TYPE).append("</action>");
        if (startTime != null)
            xml.append("<startTime>").append(startTime).append("</startTime>");
        if (otherUser != null)
            xml.append("<otherUser>").append(otherUser).append("</otherUser>");
        if (limit != null)
            xml.append("<limit>").append(limit).append("</limit>");
        if (strResult != null)
            xml.append("<result>").append(strResult).append("</result>");
        if (strSize != null)
            xml.append("<size>").append(strSize).append("</size>");
        return xml;
    }
}
