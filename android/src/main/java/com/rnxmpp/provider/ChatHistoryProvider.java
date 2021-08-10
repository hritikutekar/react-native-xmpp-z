package com.rnxmpp.provider;

import com.rnxmpp.packet.ChatHistoryIQ;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class ChatHistoryProvider extends IQProvider<ChatHistoryIQ> {

	public static final String ELEMENT = "query";// 元素
	public static final String NAMESPACE = "fd:im:singleChat";// 命名空间

	@Override
	public ChatHistoryIQ parse(XmlPullParser parser, int initialDepth) throws XmlPullParserException, IOException, SmackException {
		boolean done = false;
		ChatHistoryIQ iq = new ChatHistoryIQ();
		while (!done) {
			int eventType = parser.next();
			if (eventType == XmlPullParser.START_TAG) {
				String name = parser.getName();
				if ("result".equals(name)) {
					iq.setResult(Boolean.parseBoolean(parser.nextText()));
				}else if ("size".equals(name)) {
					iq.setSize(Integer.parseInt(parser.nextText()));
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				if (parser.getName().equals("query")) {
					done = true;
				}
			}
		}
		return iq;
	}
}
