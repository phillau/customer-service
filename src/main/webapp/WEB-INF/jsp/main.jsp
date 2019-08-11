<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/mq.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/ptloginout.js"></script>
    <script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/mq.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/chat.css">
</head>
<body>
<p>当前用户：${user.name }</p>
<div class="panel main-panel" id="panel-0"
     style="position: absolute;float: right;margin-left: 71em;margin-right: 3%;display: none;transition: -webkit-transform 0.4s cubic-bezier(0, 1, 0, 1);transform: translate3d(-100%, 0px, 0px);">
    <div id="panelBodyWrapper-0" class="panel_body_container" style=" bottom: 50px;">
        <div id="panelBody-0" class="panel_body ">
            <div class="panel selected" id="panel-1">
                <header id="panelHeader-1" class="panel_header">
                    <h1 id="panelTitle-1" class="text_ellipsis padding_20">客户列表</h1>
                    <input type="hidden" id="userName" value="${user.name }"/>
                </header>

                <div id="panelBodyWrapper-1" class="panel_body_container" style="top: 45px; ">
                    <div id="panelBody-1" class="panel_body ">
                        <div id="current_chat_scroll_area" class="scrollWrapper" style="overflow: auto;" cmd="void">
                            <ul id="current_chat_list" class="list list_white"
                                style="transition-property: -webkit-transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) translateZ(0px);">
                                <!--初始化客户列表-->
                                <c:forEach items="${usersMap }" var="map">
                                    <li id="${map.value.id }-item" class="list_item" _uin="78636695"
                                        _type="friend" cmd="clickMemberItem">
                                        <a class="avatar" cmd="clickMemberAvatar" _uin="78636695" _type="friend">
                                            <img src="${pageContext.request.contextPath }/img/girl.jpeg">
                                        </a>
                                        <p class="member_nick" id="userNick-78636695">${map.value.id }</p>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="panel chat-panel flot" id="panel-5" cmd="void"
     style="transition: -moz-Transform 0.4s cubic-bezier(0, 1, 0, 1) 0s; transform: translate3d(0px, 0px, 0px); display: block;">
    <header style="background-color: #1a1919;" id="panelHeader-5" class="panel_header">
        <h1 id="toName" class="text_ellipsis padding_20" style="color: white;padding-left: 100px;">${user.name }</h1>
    </header>

    <div id="panelBodyWrapper-5" class="panel_body_container" style="top: 45px; bottom: 50px; overflow: auto;">
        <div id="panelBody-5" class="panel_body chat_container ${user.name }"
             style="transition-property: -webkit-transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px);">
        </div>
    </div>
    <!--初始化聊天窗口，先全部设置为不可见-->
    <c:forEach items="${usersMap }" var="map">
        <div class="panel_body chat_container ${map.value.id }" style="transition-property: transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px); display: none;"></div>
    </c:forEach>
    <footer id="panelFooter-5" class="chat_toolbar_footer">
        <div class="chat_toolbar">
            <div id="add_face_btn" class="btn btn_face">
                <span class="btn_img"></span>
            </div>
            <textarea id="info" class="input input_white chat_textarea"></textarea>
            <button onclick="send1()" id="send_chat_btn" class="btn btn_small btn_blue" cmd="sendMsg">
                <span class="btn_text">发送</span>
            </button>
            <textarea id="" class="input input_white chat_textarea hidden_textarea"
                      style="height: 32px; width: 564px;"></textarea></div>
        <!--<iframe id="panel_uploadFilIframe" name="panel_uploadFilIframe" style="display:none"></iframe>-->
    </footer>

</div>
</body>
<script type="text/javascript">
    $(function () {
        $(".list_white").find("li").each(function (i, dt) {
            $(this).click(function () {
                $(".chat_container").css("display", "none");
                if ($("div").is("." + $(this).children("p").html())) {
                    $("." + $(this).children("p").html()).css("display", "");
                } else {
                    $("#panelBodyWrapper-5").after(
                        "<div class='panel_body chat_container " + $(this).children("p").html() +
                        "' style='transition-property: -webkit-transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px);'></div>");
                }
                $(this).children("span").remove();
                $("#toName").html($(this).children("p").html());
            });
        });
    });
    var websocket = null;

    function initChat() {
        //var username = sessionStorage.getItem("name");
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://127.0.0.1:8088/chat");
        } else {
            alert('当前浏览器不支持websocket，请更换浏览器')
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
            alert("WebSocket连接发生错误");
        }

        //连接成功建立的回调方法
        websocket.onopen = function () {
            websocket.send("{\"action\":1,\"chatMsg\":{\"senderId\":\"${user.name }\"}}");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            var data = JSON.parse(event.data)
            var message = data.chatMsg.msg;
            var senderId = data.chatMsg.senderId;
            var receiverId = data.chatMsg.receiverId;
            var newUser = data.newUser;
            /**
             * 如果是第一次咨询的新客户，则在客户快照列表中添加此客户，并绑定点击事件，并新建一个会话窗口
             */
            if(newUser){
                $("#current_chat_list").prepend('<li id="' + senderId + '-item" class="list_item" _uin="78636695"' +
                    '_type="friend" cmd="clickMemberItem">' +
                    '<a class="avatar" cmd="clickMemberAvatar" _uin="78636695" _type="friend">' +
                    '<img src="${pageContext.request.contextPath }/img/girl.jpeg">' +
                    '</a><span class="red-point"></span>' +
                    '<p class="member_nick" id="userNick-78636695">' + senderId + '</p>' +
                    '</li>')
                $('#' + senderId + '-item').click(function () {
                    $(".chat_container").css("display", "none");
                    if ($("div").is("." + $(this).children("p").html())) {
                        $("." + $(this).children("p").html()).css("display", "");
                    } else {
                        $("#panelBodyWrapper-5").after(
                            "<div class='panel_body chat_container " + $(this).children("p").html() +
                            "' style='transition-property: -webkit-transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px);'></div>");
                    }
                    $(this).children("span").remove();
                    $("#toName").html($(this).children("p").html());
                });
                $("#panelBodyWrapper-5").after("<div class='panel_body chat_container " + senderId + "' style='display:none; transition-property: -webkit-transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px);'>" +
                    "<div class='chat_content_group buddy  ' _sender_uin='1564776288'>" +
                    "<img class='chat_content_avatar' src='${pageContext.request.contextPath }/img/girl.jpeg' height='40px' width='40px'>" +
                    "<p class='chat_nick'>" + senderId + "</p><p class='chat_content '>" + message + "</p></div></div>");
            }else {
                /**
                 * 否则就在存在的客户会话窗口新增消息就行
                 */
                $('#' + senderId + '-item').children("p").before("<span class='red-point'></span>");
                $("." + senderId).append("<div class='chat_content_group buddy ' _sender_uin='1564776288'>" +
                "<img class='chat_content_avatar' src='${pageContext.request.contextPath }/img/girl.jpeg' height='40px' width='40px'>" +
                "<p class='chat_nick'>" + senderId + "</p><p class='chat_content '>" + message + "</p></div>");
            }
        }

        //连接关闭的回调方法
        websocket.onclose = function () {
            // alert("WebSocket连接关闭");
            document.location.reload();
        }
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        //closeWebSocket();
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    /**
     * 初始化websocket连接
     */
    initChat();

    /**
     * 设置定时发送心跳
     */
    setInterval(function () {
        websocket.send("{\"action\":4}")
    }, 60000)
</script>

<script type="text/javascript">
    function send1() {
        var message = {
            "action": 2,
            "chatMsg": {"senderId": $("#userName").val(), "receiverId": $("#toName").html(), "msg": $("#info").val()}
        }
        $("." + $("#toName").html()).append("<div class='myself' _sender_uin='1564776288'><img class='myimg' src='${pageContext.request.contextPath }/img/girl.jpeg' height='40px' width='40px'>" +
            "<p class='chat_nick' style='margin-right: 61px;position: relative;'>" + $("#userName").val() + "</p>" +
            "<p class='chat_content ' style='margin-right: 9px;'>" + $("#info").val() + "</p></div>");
        $("#info").val("");
        //检查当前连接是否断开，断开需要重连
        if (websocket == null || websocket == undefined || websocket.readyState != WebSocket.OPEN) {
            initChat();
            setTimeout(function () {
                websocket.send(JSON.stringify(message));
            }, 1000)
        } else {
            websocket.send(JSON.stringify(message));
        }
    }
</script>
</html>