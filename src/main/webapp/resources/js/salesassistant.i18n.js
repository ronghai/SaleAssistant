(function($) {
    var sales_assistant = window.sales_assistant = window.sales_assistant || {};
    sales_assistant.zh = sales_assistant.zh || {};

    $.extend(true, sales_assistant.zh, {
        "ID": "编号",
        "Client Name" : "客户姓名",
        "Birthday" : "出生日期",
        "Client":"客户姓名",
        "Zipcode": "邮编",
        "Phone": "联系电话",
        "Default Address": "默认地址",
        "Unknown": "未知",
        "Male": "男",
        "Female": "女",
        "Address": "地址",
        "Code" : "编码",
        "Name" : "名称",
        "Note" : "备注",
        "note" : "备注",
        "Chinese" : "中文"
    });
    
    
    sales_assistant.client = sales_assistant.client || {};
    sales_assistant.client.zh = sales_assistant.client.zh || {};

    $.extend(true, sales_assistant.client.zh, {
        "Name" : "客户姓名",
        "Birthday" : "出生日期",
        "Wangwang":"淘宝旺旺",
        "QQ": "QQ",
        "QQ Name": "QQ 名称",
        "Gender": "性别",
        "Phone": "联系电话",
        "Address": "地址"
    });
    
    
    
    
    
    
    
    
    
    
    
    
    
    sales_assistant.l = function(string, module, locale) {
        locale = locale || "zh";
        codes = sales_assistant[locale];
        commonCodes = sales_assistant[locale];
        if (module) {
            if (sales_assistant[module] && sales_assistant[module][locale]) {
                codes = sales_assistant[module][locale];
            }
        } 
        if (codes && codes[string]) {
            return codes[string];
        }
        if (commonCodes && commonCodes[string]) {
            return commonCodes[string];
        }
        return string;
    };
    
})(jQuery);