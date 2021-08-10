package com.rnxmpp.provider;

import com.rnxmpp.packet.DelayExtensionElement;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static org.jxmpp.util.XmppDateTime.parseDate;

public class DelayExtensionProvider extends ExtensionElementProvider<DelayExtensionElement> {

	public static final String ELEMENT = "query";// 元素
	public static final String NAMESPACE = "fd:im:singleChat";// 命名空间

	@Override
	public DelayExtensionElement parse(XmlPullParser parser, int initialDepth)  throws XmlPullParserException,
			IOException, SmackException {
		String stampString = (parser.getAttributeValue("", "stamp"));
		String from = parser.getAttributeValue("", "from");
		String timeStamp = parser.getAttributeValue("", "timeStamp");
		String messageID = parser.getAttributeValue("", "messageID");
		String history = parser.getAttributeValue("", "history");
//		<delay xmlns="urn:xmpp:delay" stamp="2021-03-02T18:15:47.000Z" from="u000414@openfire/phone" timeStamp="1614737747000" messageID="5ff84813-b8a7-403b-a385-bbb3c097bce6" history="1"/>

		String reason = null;
		if (!parser.isEmptyElementTag()) {
			int event = parser.next();
			switch (event) {
				case XmlPullParser.TEXT:
					reason = parser.getText();
					parser.next();
					break;
				case XmlPullParser.END_TAG:
					reason = "";
					break;
				default:
					throw new IllegalStateException("Unexpected event: " + event);
			}
		} else {
			parser.next();
		}
		Date stamp;
		try {
			stamp = parseDate(stampString);
		} catch (ParseException e) {
			throw new SmackException(e);
		}
		return new DelayExtensionElement(stamp, from, reason,timeStamp,messageID,history);
	}
}
