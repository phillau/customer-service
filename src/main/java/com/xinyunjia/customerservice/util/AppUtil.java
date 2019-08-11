package com.xinyunjia.customerservice.util;

import cn.wildfirechat.common.ErrorCode;
import cn.wildfirechat.pojos.Conversation;
import cn.wildfirechat.pojos.MessagePayload;
import cn.wildfirechat.pojos.SendMessageResult;
import cn.wildfirechat.proto.ProtoConstants;
import cn.wildfirechat.sdk.MessageAdmin;
import cn.wildfirechat.sdk.model.IMResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUtil {
    private static final Logger LOG = LoggerFactory.getLogger(AppUtil.class);

    public static void sendTextMessage(String fromUser, String toUser, String text) {
        Conversation conversation = new Conversation();
        conversation.setTarget(toUser);
        conversation.setType(ProtoConstants.ConversationType.ConversationType_Private);
        MessagePayload payload = new MessagePayload();
        payload.setType(1);
        payload.setSearchableContent(text);

        try {
            IMResult<SendMessageResult> resultSendMessage = MessageAdmin.sendMessage(fromUser, conversation, payload);
            if (resultSendMessage != null && resultSendMessage.getErrorCode() == ErrorCode.ERROR_CODE_SUCCESS) {
                LOG.info("send message success");
            } else {
                LOG.error("send message error {}", resultSendMessage != null ? resultSendMessage.getErrorCode().code : "unknown");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("send message error {}", e.getLocalizedMessage());
        }
    }
}
