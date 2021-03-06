<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="sm" uri="/WEB-INF/sm.tld"%>

<c:set var="javascript">
    <script type="text/javascript">
        sales_assistant._merchants_ = [];
        $(function() {
            $(document).ready(function() {            
                sales_assistant.merchants = function(force){
                    return sales_assistant.find_data(force, "_merchants_", "listMerchants", "");
                };
            });
        });
    </script>
</c:set>

<%@include file="OnRecord.jsp" %>



<script type="text/javascript">
    var sales_assistant = window.sales_assistant = window.sales_assistant || {};
    sales_assistant.render_merchant = function(record, index, col_index){
        return sales_assistant.render_cell(this, record, index, col_index,  sales_assistant.merchants());
    };
</script>