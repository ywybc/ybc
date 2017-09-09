<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/28
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript">
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
    </script>
    <title>请设置参数</title>
  </head>
  <body>
  <center>
    <img src="first.jpg"  />
    <h1>拓扑网络生成系统</h1>
    <h2>
       请设置参数
    </h2>

    <form action="fileOutput" method="post" name="myform">
    <table width="694" border="0" align="center" cellpadding="0" cellspacing="0" style="font-size: 20px">
      <tr>
        <td width="17%" height="29" align="center">nodeNum：</td>
        <td colspan="4"><input name="nodeNum" type="text" id="nodeNum" maxlength="60"></td>
      </tr>
      <tr>
        <td height="28" align="center">topyType：</td>
        <td colspan="4"><input name="topyType" type="radio" class="noborder" value="Chain" checked>
          Chain&nbsp;
          <input name="topyType" type="radio" class="noborder" value="Ring">
          Ring&nbsp;
          <input name="topyType" type="radio" class="noborder" value="Mesh">
          Mesh&nbsp;
        </td>
      </tr>
      <tr>
        <td height="28" align="center">outputType：</td>
        <td colspan="4"><input name="outputType" type="radio" class="noborder" value="XML" checked>
          XML&nbsp;
          <input name="outputType" type="radio" class="noborder" value="JSON">
          JSON</td>
      </tr>
      <tr>
        <td width="17%" height="29" align="center">traversalStart:</td>
        <td colspan="4"><input name="traversalStart" type="text" id="traversalStart" maxlength="60" ></td>
      </tr>
      <tr>
        <td height="34">&nbsp;</td>
        <td width="30%" class="word_grey"><input name="Submit" type="button" class="btn_grey" value="save" style="font-size: 20px" onClick="check()">
          <input name="Reset" type="reset" class="btn_grey" id="Reset" value="reset" style="font-size: 20px"></td>
      </tr>
    </table>
    </form>
    <img src="second.jpg"  />
  </center>
  </body>
</html>
