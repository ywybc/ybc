<%--
  Created by IntelliJ IDEA.
  User: YXT
  Date: 2017/8/31
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript">
        window.onload=refix;
        function check(){
            var StartStr =myform.traversalStart.value;
            if(StartStr ==null && StartStr == ""){
                StartStr = "1"
            }
            if( parseInt(myform.nodeNum.value) < parseInt(StartStr)){
                alert("起点序号不能大于总点数");
                myform.traversalStart.value = "";
                return;
            }
            //判断用户名是否为空
            if(myform.nodeNum.value==""){
                alert("请输入节点数目！");
                myform.nodeNum.focus();
                return;
            } else if(myform.nodeNum.value != null){
                //提交表单
                myform.submit();
            }
        }
        function refix(){
            var topyType="<%=session.getAttribute("topyType")%>";
            var outputType="<%=session.getAttribute("outputType")%>";
            var topy = document.getElementById(topyType);
            // 设置复选框为选中状态
            topy.checked = true;
            var output = document.getElementById(outputType);
            // 设置复选框为选中状态
            output.checked = true;
        }
    </script>
    <title>请修改参数</title>
</head>
<body>
<center>
    <img src="first.jpg"  />
    <h2>
        请修改参数
    </h2>

    <form action="fileOutput" method="post" name="myform">
        <table width="694" border="0" align="center" cellpadding="0" cellspacing="0" style="font-size: 20px">
            <tr>
                <td width="17%" height="29" align="center">nodeNum：</td>
                <td colspan="2"><input name="nodeNum" type="text" id="nodeNum" maxlength="20" value="${nodeNum}"></td>
            </tr>
            <tr>
                <td height="28" align="center">topyType：</td>
                <td colspan="2"><input name="topyType" type="radio" class="noborder" value="Chain"  id ="Chain"checked>
                    Chain&nbsp;
                    <input name="topyType" type="radio" class="noborder" value="Ring" id ="Ring">
                    Ring&nbsp;
                    <input name="topyType" type="radio" class="noborder" value="Mesh" id = "Mesh">
                    Mesh&nbsp;
                </td>
            </tr>
            <tr>
                <td height="28" align="center">outputType：</td>
                <td colspan="2"><input name="outputType" type="radio" class="noborder" value="XML" id="XML" checked>
                    XML&nbsp;
                    <input name="outputType" type="radio" class="noborder" value="JSON" id="JSON">
                    JSON</td>
            </tr>
            <tr>
                <td width="17%" height="29" align="center">traversalStart:</td>
                <td colspan="2"><input name="traversalStart" type="text" id="traversalStart" maxlength="20"  value="${traversalStart}"></td>
            </tr>
            <tr>
                <td height="34">&nbsp;</td>
                <td width="30%" class="word_grey"><input name="Submit" type="button" class="btn_grey" value="save again" onClick="check()" style="font-size: 20px">
                    <input name="Reset" type="reset" class="btn_grey" id="Reset" value="reset again" style="font-size: 20px"></td>
            </tr>
        </table>
    </form>
    <img src="second.jpg" />
</center>
</body>
</html>
