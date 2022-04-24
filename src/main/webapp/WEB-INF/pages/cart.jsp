<%--
  Created by IntelliJ IDEA.
  User: yanxuanwu
  Date: 4/22/22
  Time: 2:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/commons/common.jsp"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
    <%@include file="/WEB-INF/commons/queryCondition.jsp"%>
    <script type="text/javascript">

        $(function(){

            $(".delete").click(function (){
                var $tr=$(this).parent().parent();
                var title=$.trim($tr.find("td:first").text());
                var flag=confirm("Confirm to delete "+title);

                if(flag){
                    return true;
                }
                return false;
            });

            //ajax modify quantity
            //get all text and add onchange function, alert: confirm to change
            $(":text").change(function(){
                var quantityVal=$.trim(this.value);

                var flag=false;
                var reg=/^\d+$/g;
                var quantity=-1;
                if(reg.test(quantityVal)){
                    var quantity=parseInt(quantityVal);

                    if (quantity>=0){
                        flag=true;
                    }
                }

                if(!flag){
                    alert("illegal input");
                    $(this).val($(this).attr("class"));
                    return;
                }
                var $tr=$(this).parent().parent();
                var title=$.trim($tr.find("td:first").text());

                if(quantity==0){
                    var flag2=confirm("Do you want to delete " + title +"?")
                    if(flag2){
                        var $a=$tr.find("td:last").find("a");
                        //alert($a[0].onclick);
                        $a[0].onclick();

                        return;
                    }
                }




                var flag=confirm("confirm to change the quantity of "+title);

                if(!flag){
                    $(this).val($(this).attr("class"));
                    return;
                }


                var url="bookServlet";

                //add request
                var idVal=$.trim(this.name);

                var args={"method":"updateItemQuantity","id":idVal, "quantity":quantityVal, "time":new Date()}


                //update html
                $.post(url,args,function (data){
                    var bookNumber=data.bookNumber;
                    var totalMoney=data.totalMoney;


                    //$("#totalMoney").text("Total Money: $"+ totalMoney);
                    $("#bookNumber").text("Your shopping cart has " +bookNumber+ " books.");
                    $("#totalMoney").text("Total Money : $"+ totalMoney);

                },"JSON");


            });

        })
    </script>
</head>
<body>
    <center>
        <br><br>
        <div id="bookNumber">Your shopping cart has ${sessionScope.ShoppingCart.bookNumber} books.</div>

        <table>
            <tr>
                <td>Title</td>
                <td>Quantity</td>
                <td>Price</td>
                <td>&nbsp;</td>
            </tr>
            <c:forEach items="${sessionScope.ShoppingCart.items}" var="item">
                <tr>
                    <td>${item.book.title}</td>
                    <td>
                        <input class="${item.quantity}" type="text" size="1" name="${item.book.id}" value="${item.quantity}"/>
                    </td>
                    <td>${item.book.price}</td>
                    <td><a href="bookServlet?method=remove&pageNo=${param.pageNo}&id=${item.book.id}" class="delete">delete</a></td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="4" id="totalMoney">Total Money: ${sessionScope.ShoppingCart.totalMoney}</td>
            </tr>

            <tr>
                <td colspan="4">
                    <br>
                    <a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">continue shopping</a>
                    &nbsp;&nbsp;

                    <a href="bookServlet?method=clear">clear shopping cart</a>
                    &nbsp;&nbsp;

                    <a href="bookServlet?method=forwardPage&page=cash">check out</a>
                    &nbsp;&nbsp;

                </td>
            </tr>

        </table>
    </center>

    <br><br>





</body>
</html>
