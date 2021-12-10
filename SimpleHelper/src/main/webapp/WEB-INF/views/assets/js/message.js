/**
 * 
 */
 


$('.message_content').click(function(){
	var jb = $(this).children( '.message_username' ).text();
	
	$('.message_title').text("'" + jb + "'" + "님과의 대화방") ;

});


const Chat = (function(){
		

	    const myName = "나";
	 
	    // init 함수
	    function init() {
	        // enter 키 이벤트
	        $(document).on('click', 'div.input-div button', function(e){

	                e.preventDefault();
	                const message = $('div.input-div input').val();
	                if(message != ""){
	 
	                // 메시지 전송
	                sendMessage(message);
	                // 입력창 clear
	                clearTextarea();
	                }
	        });
	       
	        
	        $(document).on('keydown', 'div.input-div input', function(e){
	            if(e.keyCode == 13 && !e.shiftKey) {
	                e.preventDefault();
	                
	                const message = $(this).val();
	                if (message != ""){
	                	// 메시지 전송
	                	sendMessage(message);
	                	// 입력창 clear
	                	clearTextarea();}
	            
	            }
	        });
	    }
	 
	    // 메세지 태그 생성
	    function createMessageTag(LR_className, senderName, message, time) {
	        // 형식 가져오기
	        let chatLi = $('div.chat.format ul li').clone();
	        
	 		
	        // 값 채우기
	        chatLi.addClass(LR_className);
	        chatLi.find('.sender span').text(senderName);
	        chatLi.find('.message span').text(message);
	 		chatLi.find('.time span').text(time);
	 		
	        return chatLi;
	    }
	 
	    // 메세지 태그 append
	    function appendMessageTag(LR_className, senderName, message, time) {
	        const chatLi = createMessageTag(LR_className, senderName, message,time);
	 
	        $('div.chat:not(.format) ul').append(chatLi);
	 		 $('.chat_wrap').scrollTop($('.chat_wrap')[0].scrollHeight);
	    }
	 
	    // 메세지 전송
	    function sendMessage(message) {
	    	var mydate = new Date();
	    	var hh = mydate.getHours();
	    	var mi = mydate.getMinutes();
	    	if(mi < 10){mi = "0" + mi;}
	    	var tt = hh + ":" + mi;
	        // 서버에 전송하는 코드로 후에 대체
	        const data = {
	            "senderName"    : "나",
	            "message"        : message,
	            "time" 	 : tt
	        };
	 
	        // 통신하는 기능이 없으므로 여기서 receive
	        resive(data);
	    }
	 
	    // 메세지 입력박스 내용 지우기
	    function clearTextarea() {
	        $('div.input-div input').val('');
	    }
	 
	    // 메세지 수신
	    function resive(data) {
	        const LR = (data.senderName != myName)? "left" : "right";
	        appendMessageTag("right", data.senderName, data.message,data.time);
	        
	    }
	 
	    return {
	        'init': init
	    };
	})();
	 
	$(function(){
	    Chat.init();
	});
	
	