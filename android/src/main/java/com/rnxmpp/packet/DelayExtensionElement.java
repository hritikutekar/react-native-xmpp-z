package com.rnxmpp.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.delay.packet.DelayInformation;
import org.jxmpp.util.XmppDateTime;

import java.util.Date;

public class DelayExtensionElement extends DelayInformation {
    private final String timeStamp;
    private final String messageID;
    private final String history;

    public DelayExtensionElement(Date stamp, String from, String reason, String timeStamp, String messageID, String history) {
        super(stamp, from, reason);
        this.timeStamp = timeStamp;
        this.messageID = messageID;
        this.history = history;
    }

    @Override
    public XmlStringBuilder toXML() {
        XmlStringBuilder xml = new XmlStringBuilder(this);
        xml.attribute("stamp", XmppDateTime.formatXEP0082Date(getStamp()));
        xml.optAttribute("from", getFrom());
        xml.optAttribute("timeStamp", timeStamp);
        xml.optAttribute("messageID", messageID);
        xml.optAttribute("history", history);
        xml.rightAngleBracket();
        xml.optAppend(getReason());
        xml.closeElement(this);
        return xml;
    }
}
