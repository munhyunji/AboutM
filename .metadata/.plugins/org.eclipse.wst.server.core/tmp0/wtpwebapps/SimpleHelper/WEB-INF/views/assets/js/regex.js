
var regex = {

    value:function(selector,msg) {
        var target= $(selector);
        if(!target.val()){
            alert(msg);
            target.focus();
            return false;
        }
        return true;
        
    },
    max_length :function(selector,len,msg) {
        var target=$(selector);
        if(target.val().length>len){
            alert(msg);
            target.val("");
            target.focus();
            return false;
        }
        return true;
    },
    min_length:function(selector,len,msg){
        var target=$(selector);
        if(target.val().length<len){
            alert(msg);
            target.val("");
            target.focus();
            return false;
        }
        return true;
    },
    check: function(selector,msg){
        var checked =$(selector +':checked');
        if(checked.length<1){
            alert(msg);
            $(selector).eq(0).focus();
            return false;
        }
        return true;
    },
    check_min: function(selector,min,msg){
        
        if($(selector +':checked').length < min){
            alert(msg);
            $(selector).eq(0).focus();
            return false;
        }
        return true;
    },
    check_max: function(selector,max,msg){
        
        if($(selector +':checked').length > max){
            alert(msg);
            $(selector).eq(0).focus();
            return false;
        }
        return true;
    },
    compare_to:function(origin,compare,msg){
        var src=$(origin);
        var dsc=$(compare);

        if(src.val()!=dsc.val()){
            alert(msg);
            src.val("");
            dsc.val("");
            src.focus();
            return false;
        }
        return true;
    },
    field :function (selector,msg,regex_expr) {
        var target=$(selector);
        if(!target.val() || !regex_expr.test(target.val())){
            alert(msg);
            target.val("");
            target.focus();
            return false;
        }
        return true;
        
    },
    num:function (selector,msg) {
        return this.field(selector,msg,/^[0-9]*$/);
        
    },
    eng:function (selector,msg) {
        return this.field(selector,msg,/^[a-zA-Z]*$/);
        
    },
    kor:function (selector,msg) {
        return this.field(selector,msg,/^[ㄱ-ㅎ가-힣]*$/);
        
    },
    eng_num:function (selector,msg) {
        return this.field(selector,msg,/^[a-zA-Z0-9]*$/);
        
    },
    email:function (selector,msg) {
        return this.field(selector,msg,/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
        );
        
    },
    cellphone:function (selector,msg) {
        return this.field(selector,msg,/^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/);
        
    },
    telphone:function (selector,msg) {
        return this.field(selector,msg,/^\d{2,3}\d{3,4}\d{4}$/);
        
    },
    jumin:function (selector,msg) {
        return this.field(selector,msg,/^\d{6}[1-4]\d{6}/);
        
    },
    phone:function (selector,msg) {
        var check1 = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
        var check2 = /^\d{2,3}\d{3,4}\d{4}$/;
        var target =$(selector);

        if(!target.val()|| (!check1.test(target.val()) && !check2.test(target.val()))  ){
            alert(msg);
            target.val("");
            target.focus();
            return false;
        }
        return true;
        
    },

};